package com.micromata.webengineering.demo.authentication;

import com.micromata.webengineering.demo.user.User;
import com.micromata.webengineering.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Luan Hajzeraj on 26.05.2017.
 */

//Neuer Endpunkt "/user"
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    public static class UserLogin{
        public String email;
        public String password;
    }


    @Autowired
    private AuthenticationService service;

    @Autowired
    private UserRepository userRepository;

    /*
    @RequestMapping(value="login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationService.UserToken> login(@RequestBody UserLogin userLogin){
        AuthenticationService.UserToken token = service.login(userLogin.email,userLogin.password);

        if(token == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(token,HttpStatus.OK);
    }
    */

    //Erweiterung des Endpunktes, also: /user/login
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationService.UserToken> login(@RequestBody UserLogin userLogin){
        //Frage USer über das Repository ab
        //User user = userRepository.login(userLogin.email,userLogin.password);
        AuthenticationService.UserToken token = service.login(userLogin.email,userLogin.password);


        //Wenn kein USer existiert, gib ein NICHT AUTHORISIERT zurück
        if(token == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //Sonst gebe nach wie for das String als token zurück, aber auch mit einem OK als response
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

}
