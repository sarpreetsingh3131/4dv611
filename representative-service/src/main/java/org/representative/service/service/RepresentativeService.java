package org.representative.service.service;

import org.domain.converter.ProductConverter;
import org.domain.dto.*;
import org.domain.model.Category;
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
    private ProductConverter productConverter;

    public String logIn(CredentialDto credentialDto) throws Exception {
        return userService.logInAsRepresentative(credentialDto);
    }

    public String logOut(String token) throws Exception {
        return userService.logOutAsRepresentative(token);
    }

    public ProductWithoutBadgeDto saveProduct(CreateProductDto createProductDto, String token) throws Exception {
        Product product = productRepository.save((new Product(
                createProductDto.getName(), createProductDto.getModel(), findCategoryById(createProductDto),
                userService.findRepresentative(token).getCompany(), 0)));
        try {
            product.setPrimaryImage(imageService.save(createProductDto.getPrimaryImage(), product));
            product.setSecondaryImages(imageService.saveAll(createProductDto.getSecondaryImages(), product));
            product.setManuals(manualService.saveAll(createProductDto.getManuals(), product));
        } catch (Exception e) {
            productRepository.delete(product);
            throw new Exception(e);
        }
        return productConverter.toProductWithoutBadgeDto(product);
    }

    public ProductWithoutBadgeDto deleteImageById(Long id, String token) throws Exception {
        return productConverter.toProductWithoutBadgeDto(
                imageService.deleteById(id, userService.findRepresentative(token))
                        .getProduct());
    }

    public ProductWithoutBadgeDto deleteManualById(Long id, String token) throws Exception {
        return productConverter.toProductWithoutBadgeDto(
                manualService.deleteById(id, userService.findRepresentative(token))
                        .getProduct());
    }

    public List<ProductWithSelectionDto> findProductsWithSelection(String token) throws Exception {
        return productConverter.toProductWithSelectionDto(
                productRepository.findByCompanyId(userService.findRepresentative(token).getCompany().getId()),
                consumerRepository.findAll());
    }

    public ServiceProvider updateServiceProviderAuthorization(ServiceProviderAuthorizationDto
                                                                      serviceProviderAuthorizationDto,
                                                              String token) throws Exception {
        ServiceProvider serviceProvider = findServiceProviderByIdAndCompanyId(
                serviceProviderAuthorizationDto, userService.findRepresentative(token));
        serviceProvider.setAuthorization(serviceProviderAuthorizationDto.getAuthorization());
        return serviceProviderRepository.save(serviceProvider);
    }

    public String sendEmail(EmailDto emailDto, String token) throws Exception {
        userService.findRepresentative(token);
        return emailService.send(consumerRepository.findBySubscription(true), emailDto);
    }

    public List<ServiceProvider> findAllServiceProviders(String token) throws Exception {
        return serviceProviderRepository.findByCompanyId(userService.findRepresentative(token).getCompany().getId());
    }

    private ServiceProvider findServiceProviderByIdAndCompanyId(ServiceProviderAuthorizationDto
                                                                        serviceProviderAuthorizationDto,
                                                                Representative representative) throws Exception {
        return serviceProviderRepository.findByIdAndCompanyId(
                serviceProviderAuthorizationDto.getServiceProviderId(), representative.getCompany().getId())
                .orElseThrow(() -> new Exception(
                        "no service provider with id = " + serviceProviderAuthorizationDto.getServiceProviderId()));
    }

    private Category findCategoryById(CreateProductDto createProductDto) throws Exception {
        return categoryRepository.findById(createProductDto.getCategoryId())
                .orElseThrow(() -> new Exception("no category with id = " + createProductDto.getCategoryId()));
    }
}
