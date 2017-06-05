package com.micromata.webengineering.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Luan Hajzeraj on 22.05.2017.
 */

@Service
public class AddressService{

    //@Value Annotation holt Wert aus einer properties-Datei (appl.properties oder appl-heroku.properties) und befüllt Var damit
    @Value("${addressService.address}")
    private String serverAdress;

    //Gibt einfach die jeweillige URL zurück, entweder die lokale (aus application.prop) oder auf heroku
    public String getServerURL(){
        return serverAdress;
    }
}
