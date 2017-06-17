package com.micromata.webengineering.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Luan Hajzeraj on 22.05.2017.
 */

//@Profile bedeutet: nutze diese klasse, wenn das Profil "default" aktiv ist, also kein anderes.
//Wir setzen also damit quasi Nutzungs-marken: Immer diese Klasse benutzen, bei Tests eine andere.
//Damit können wir eben die Umgebungen unterscheiden: Sind wir lokal, auf einem Test-Server,...
@Profile("default")
@Service
public class AddressServiceImpl implements AddressService {

    //@Value Annotation holt Wert aus einer properties-Datei (appl.properties oder appl-heroku.properties) und befüllt Var damit
    @Value("${addressService.address}")
    private String serverAdress;

    //Gibt einfach die jeweillige URL zurück, entweder die lokale (aus application.prop) oder auf heroku
    @Override
    public String getServerURL(){
        return serverAdress;
    }
}
