package com.micromata.webengineering.demo.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Luan Hajzeraj on 11.06.2017.
 */

//Nutze diese Klasse, wenn das Proful "test" aktiv ist
@Profile("test")
@Service
public class AddressServiceTestImpl implements AddressService{
    @Override
    public String getServerURL() {
        return "foo";
    }
}
