package org.domain.converter;

import org.domain.dto.ServiceProviderDto;
import org.domain.model.ServiceProvider;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ServiceProviderConverter {

    public List<ServiceProviderDto> toServiceProviderDtos(List<ServiceProvider> serviceProviders) {
        List<ServiceProviderDto> serviceProviderDtos = new LinkedList<>();
        serviceProviders.forEach(serviceProvider -> serviceProviderDtos.add(toServiceProviderDto(serviceProvider)));
        return serviceProviderDtos;
    }

    public ServiceProviderDto toServiceProviderDto(ServiceProvider serviceProvider) {
        return new ServiceProviderDto(serviceProvider.getName(), serviceProvider.getEmail(),
                serviceProvider.getPhone(), serviceProvider.getCompany().getName());
    }
}
