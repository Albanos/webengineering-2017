package com.micromata.webengineering.demo.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Luan Hajzeraj on 03.06.2017.
 */

//Config-Klasse für Spring Authentifizierung. Wird diese Weggelassen, wird jedwede Anfrage an den Server gebloggt!!!
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    //Wir erlauben generell jeden Zugriff, Spring soll nicht eingreifen, wir wollen nur den Spring Context Holder nutzen!
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("*").anonymous()
                .and()
                .csrf().disable();
    }

}
