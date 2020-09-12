package com.kamiski.springbootjwt.component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class HashGenerate {

    public String generateBCrypt(String string){
        return new BCryptPasswordEncoder().encode(string);
    }

    public Integer generateValidationCode(){

        String code = "";
        Random random = new Random();

        for(int i = 0; i < 4; i++){
            code += random.nextInt(10);
        }

        return Integer.parseInt(code);

    }

}
