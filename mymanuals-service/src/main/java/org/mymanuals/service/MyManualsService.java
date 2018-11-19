package org.mymanuals.service;

import org.company.service.CompanyService;
import org.consumer.service.ConsumerService;
import org.domain.Domain;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MyManualsService {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.CONSOLE)
                .sources(Domain.class,
                        CompanyService.class,
                        ConsumerService.class,
                        MyManualsService.class
                )
                .run(args);
    }
}
