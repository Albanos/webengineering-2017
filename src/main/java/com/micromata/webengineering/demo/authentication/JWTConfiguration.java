package com.micromata.webengineering.demo.authentication;

import com.micromata.webengineering.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Luan Hajzeraj on 03.06.2017.
 */
@Configuration
public class JWTConfiguration {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean jwtFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new JWTFilter(authenticationService, userService));

        //Alles, was über den Request "/api/irgendwas" reinkommt, soll ueber den JWTFilter laufen (Definiert als Klasse)
        bean.addUrlPatterns("/api/*");

        return bean;
    }
}
