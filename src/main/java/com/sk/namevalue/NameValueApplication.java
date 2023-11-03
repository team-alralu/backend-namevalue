package com.sk.namevalue;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * title        :
 * author       : sim
 * date         : 2023-08-31
 * description  :
 */

@SpringBootApplication
@EnableJpaAuditing
@EnableEncryptableProperties
public class NameValueApplication {
    public static void main(String[] args){
        SpringApplication.run(NameValueApplication.class, args);
    }
}