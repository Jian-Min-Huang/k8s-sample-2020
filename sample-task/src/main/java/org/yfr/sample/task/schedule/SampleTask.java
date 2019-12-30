package org.yfr.sample.task.schedule;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.yfr.sample.common.entity.Item;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Component
public class SampleTask {

    @Value("${host.sample.item}")
    private String itemHost;

    @Resource
    private RabbitTemplate rabbitTemplate;

    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "*/5 * * * * *")
    @SchedulerLock(name = "addMember", lockAtMostForString = "PT2M")
    public void addMember() {
        try {
            rabbitTemplate.convertAndSend("member", UUID.randomUUID().toString().replace("-", ""));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Scheduled(cron = "*/5 * * * * *")
    @SchedulerLock(name = "parseItem", lockAtMostForString = "PT2M")
    public void parseItem() {
        try {
            log.info("parse {}", restTemplate.exchange(itemHost + "/item", HttpMethod.POST, new HttpEntity(new HttpHeaders()), Item.class).getBody().toString());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}
