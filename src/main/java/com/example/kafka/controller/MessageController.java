package com.example.kafka.controller;

import com.example.kafka.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final Producer producer;

    @Autowired
    public MessageController(Producer producer) {
        this.producer = producer;
    }

    @RequestMapping(value = "/kafka/publish", method = RequestMethod.POST)
    public void sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
    }
}
