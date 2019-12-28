package org.yfr.sample.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.yfr.sample.common.entity")
public class SampleItemApp {

    public static void main(String[] args) {
        SpringApplication.run(SampleItemApp.class, args);
    }

}
