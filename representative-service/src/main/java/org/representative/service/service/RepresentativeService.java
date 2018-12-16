package org.representative.service.service;

import org.domain.converter.ProductConverter;
import org.domain.dto.*;
import org.domain.model.Product;
import org.domain.model.Representative;
import org.domain.model.ServiceProvider;
import org.domain.repository.CategoryRepository;
import org.domain.repository.ConsumerRepository;
import org.domain.repository.ProductRepository;
import org.domain.repository.ServiceProviderRepository;
import org.domain.service.EmailService;
import org.domain.service.ImageService;
import org.domain.service.ManualService;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProductConverter converter;

    public String logIn(CredentialDto credentialDto) throws Exception {
        System.out.println(emailService.getEmailApiKey());
        return userService.logInAsRepresentative(credentialDto);
    }

    public ProductWithoutBadgeDto saveProduct(CreateProductDto createProductDto, String token) throws Exception {
        Product product = productRepository.save((new Product(
                createProductDto.getName(), createProductDto.getModel(),
                categoryRepository.findById(createProductDto.getCategoryId())
                        .orElseThrow(() -> new Exception("No category with id = " + createProductDto.getCategoryId())),
                userService.findRepresentative(token).getCompany())));
        try {
            product.setPrimaryImage(imageService.save(createProductDto.getPrimaryImage(), product));
            product.setSecondaryImages(imageService.saveAll(createProductDto.getSecondaryImages(), product));
            product.setManuals(manualService.saveAll(createProductDto.getManuals(), product));
        } catch (Exception e) {
            productRepository.delete(product);
            throw new Exception(e);
        }
        return converter.toProductWithoutBadgeDto(product);
    }

    public ProductWithoutBadgeDto deleteImageById(Long id, String token) throws Exception {
        return converter.toProductWithoutBadgeDto(
                imageService.deleteById(id, userService.findRepresentative(token))
                        .getProduct());
    }

    public ProductWithoutBadgeDto deleteManualById(Long id, String token) throws Exception {
        return converter.toProductWithoutBadgeDto(
                manualService.deleteById(id, userService.findRepresentative(token))
                        .getProduct());
    }

    public List<ProductWithSelectionDto> findProductsWithSelection(String token) throws Exception {
        return converter.toProductWithSelectionDto(
                productRepository.findByCompanyId(userService.findRepresentative(token).getCompany().getId()),
                consumerRepository.findAll());
    }

    public ServiceProvider updateServiceProviderAuthorization(ServiceProviderAuthorizationDto serviceProviderAuthorizationDto,
                                                              String token) throws Exception {
        Representative representative = userService.findRepresentative(token);
        ServiceProvider serviceProvider = serviceProviderRepository.findByIdAndCompanyId(
                serviceProviderAuthorizationDto.getServiceProviderId(), representative.getCompany().getId())
                .orElseThrow(() -> new Exception("No service provider with id = " +
                        serviceProviderAuthorizationDto.getServiceProviderId()));
        serviceProvider.setAuthorization(serviceProviderAuthorizationDto.getAuthorization());
        return serviceProviderRepository.save(serviceProvider);
    }

    public String sendEmail(EmailDto emailDto, String token) throws Exception {
        Representative representative = userService.findRepresentative(token);
        return emailService.send(consumerRepository.findBySubscription(true), emailDto.getSubject(), emailDto.getBody());
    }
}
