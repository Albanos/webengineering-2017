package com.micromata.webengineering.demo.authentication;

import com.micromata.webengineering.demo.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Luan Hajzeraj on 03.06.2017.
 */
public class JWTFilter extends GenericFilterBean {
    private static final Logger LOG = LoggerFactory.getLogger(JWTFilter.class);

    private AuthenticationService authenticationService;
    private UserService userService;

    public JWTFilter(AuthenticationService authenticationService, UserService userService){
        this.authenticationService = authenticationService;
        this.userService = userService;
    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //Gib mir auf der Konsole eine Log-Nachricht über response aus. Bei Abfrage von allen Posts beispielsweise "/api/post"
        //Beim Login eines Users: "/api/login"
        LOG.info("Request:  "+httpServletRequest.getRequestURI());


        //Wir extrahieren den Authorization-Teil aus dem Header des Request
        String auth = httpServletRequest.getHeader("Authorization");

        //Über den Bearer-Teil bekommen wir das JWT-Token und vergleichen es
        if(!StringUtils.startsWith(auth,"Bearer")){
            //Wenn Nutzer nicht authentifiziert ist, kann er zumindest alle Posts sehen

            // Allow requests without a token.
            LOG.debug("No token provided, setting to anonymous user");
            userService.setAnonymous();
            filterChain.doFilter(request, response);
            return;
        }

        //Im positiven Fall: packe das Token des Request aus
        String token = auth.substring(7);
        try{
            Claims body = (Claims) authenticationService.parseToken(token);
            LOG.debug("Successful authentication from id={}, user={}", body.getId(), body.getSubject());

            //Setze im positiven Fall den User
            userService.setCurrentUser(Long.parseLong(body.getId()), body.getSubject());
            filterChain.doFilter(request,response);
        }
        catch (SignatureException | NullPointerException e) {
            LOG.warn("Token is invalid. token={}", token);

            //Antwort mit Unauthorisiert
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }




    }
}
