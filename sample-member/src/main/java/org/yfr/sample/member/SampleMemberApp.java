package org.yfr.sample.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.yfr.sample.common.entity")
public class SampleMemberApp {

    public static void main(String[] args) {
        SpringApplication.run(SampleMemberApp.class, args);
    }

}
