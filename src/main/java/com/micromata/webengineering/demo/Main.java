package com.micromata.webengineering.demo;

import com.micromata.webengineering.demo.user.User;
import com.micromata.webengineering.demo.user.UserRepository;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Main entry point and configuration base of the application.
 */
@EnableSwagger2
@SpringBootApplication
public class Main {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * Configuration for Swagger.
     *
     * @return a docket.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    //War nur eine Übergangslösung, jetzt nutzen wir die data-h2.sql-Datei unter ressources, zum befüllen
    /*
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{
        User user = userRepository.findByEmail("l.hajzeraj@gmail.com");

        //Wenn User existiert: Gib Meldung aus und beende
        if(user != null){
            LOG.info("User l.hajzeraj@gmail.com exist");
            return;
        }

        //Andernfalls lege den User an
        user = new User();
        user.setEmail("l.hajzeraj@gmail.com");
        user.setPassword("test");
        userRepository.save(user);
        LOG.info("User l.hajzeraj@gmail.com created");
    }
    */
}
