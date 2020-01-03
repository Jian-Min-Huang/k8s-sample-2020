package org.yfr.sample.lg.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = {
        "org.yfr.sample.common.api"})
public class SampleLgGwApp {

    public static void main(String[] args) {
        SpringApplication.run(SampleLgGwApp.class, args);
    }

}
