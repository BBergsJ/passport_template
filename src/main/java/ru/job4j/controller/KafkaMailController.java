package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMailController {

    @KafkaListener(topics = "${spring.kafka.default-topic}")
    public void emailAnnouncer(String passData) {
        System.out.println("Found expired passport : " + passData);
    }
}
