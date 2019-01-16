package org.mymanuals.service;

import org.ad.agent.service.AdAgentServiceMain;
import org.company.service.CompanyServiceMain;
import org.consumer.service.ConsumerServiceMain;
import org.domain.DomainMain;
import org.representative.service.RepresentativeServiceMain;
import org.service.provider.service.ServiceProviderServiceMain;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.system.admin.service.SystemAdminServiceMain;

@SpringBootApplication
public class MyManualsServiceMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.CONSOLE)
                .sources(AdAgentServiceMain.class,
                        CompanyServiceMain.class,
                        ConsumerServiceMain.class,
                        DomainMain.class,
                        MyManualsServiceMain.class,
                        RepresentativeServiceMain.class,
                        ServiceProviderServiceMain.class,
                        SystemAdminServiceMain.class)
                .run(args);
    }
}
