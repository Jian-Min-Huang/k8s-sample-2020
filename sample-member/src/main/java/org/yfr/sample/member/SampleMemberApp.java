package org.yfr.sample.member;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("org.yfr.sample.common.entity")
public class SampleMemberApp {

    @Bean
    public Queue memberQueue() {
        return new Queue("member", false);
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleMemberApp.class, args);
    }

}
