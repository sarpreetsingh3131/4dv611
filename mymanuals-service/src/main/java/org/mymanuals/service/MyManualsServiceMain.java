package org.mymanuals.service;

import org.category.service.CategoryServiceMain;
import org.company.service.CompanyServiceMain;
import org.consumer.service.ConsumerServiceMain;
import org.domain.DomainMain;
import org.material.service.MaterialServiceMain;
import org.product.service.ProductServiceMain;
import org.representative.service.RepresentativeServiceMain;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MyManualsServiceMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.CONSOLE)
                .sources(DomainMain.class, CompanyServiceMain.class, ConsumerServiceMain.class,
                        RepresentativeServiceMain.class, ProductServiceMain.class, CategoryServiceMain.class,
                        MyManualsServiceMain.class, MaterialServiceMain.class)
                .run(args);
    }
}
