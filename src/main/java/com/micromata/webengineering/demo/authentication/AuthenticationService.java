package com.micromata.webengineering.demo.authentication;

import com.micromata.webengineering.demo.user.User;
import com.micromata.webengineering.demo.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Luan Hajzeraj on 27.05.2017.
 */
@Service
public class AuthenticationService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private UserService userService;

    //private String secret = "Severus Snape was a good guy!";

    @Value("${authenticationService.jwt.secret}")
    private String JWTSecret;


    //@Value greift, je nach Umgebung, also lokal oder heroku, auf den Wert in der jeweilligen properties-Datei zu und setzt ihn
    @Value("${authenticationService.salt}")
    private String salt;

    public static class UserToken{
        public User user;
        public String token;
    }


    public UserToken login(String email, String password){
        //User user = userService.getUser(email,password);
        String hashedPassword = hashPassword(password);
        User user = userService.getUser(email, hashedPassword);

        if(user == null){
            LOG.info("User unable to login. user={}", email);
            return null;
        }
        LOG.info("User successfully logged in. user={}", email);


        String token = Jwts.builder()
                .setSubject(email)
                //Füge den User zum JWT-Token hinzu
                .setId(user.getId().toString())
                .signWith(SignatureAlgorithm.HS512, JWTSecret)
                .compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;

        return userToken;
    }


    public Object parseToken(String jwtToken) {
        LOG.debug("Parsing JWT token. JWTtoken={}", jwtToken);
        return Jwts.parser()
                .setSigningKey(JWTSecret)
                .parse(jwtToken)
                .getBody();
    }

    /**
     * Return (salt + password) hashed with SHA-512.
     *
     * The salt is configured in the property authenticationService.salt.
     *
     * @param password plain text password
     * @return hashed password
     */

    //MERKE: Methode liefert wirklich "salt + password" zurueck, also salt, dann leerzeichen, dann '+', dann leerzeichen
    //und dann das Passwort!!!
    private String hashPassword(String password) {
        return DigestUtils.sha512Hex(salt + password);
    }
}
