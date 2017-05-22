package com.micromata.webengineering.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Luan Hajzeraj on 22.05.2017.
 */

@Service
public class AddressService{
    @Value("${addressService.address}")
    private String serverAdress;

    public String getServerURL(){
        return serverAdress;
    }
}
