package com.example;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.spring.cloud.service.servicebus.consumer.ServiceBusErrorHandler;
import com.azure.spring.cloud.service.servicebus.consumer.ServiceBusRecordMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageDemo implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MessageDemo.class);

    private final ServiceBusSenderClient serviceBusSenderClient;

    public MessageDemo(ServiceBusSenderClient serviceBusSenderClient) {
        this.serviceBusSenderClient = serviceBusSenderClient;
    }

    @Override
    public void run(String... args) {
        serviceBusSenderClient.sendMessage(new ServiceBusMessage("Hello, World!"));
    }

    @Bean
    public ServiceBusRecordMessageListener processMessage() {
        return context -> {
            LOG.info("Message received: {}", context.getMessage().getBody());
            throw new RuntimeException("Simulated error");
        };
    }

    @Bean
    public ServiceBusErrorHandler processError() {
        return context -> {
            LOG.error("Error when processing message: {}", context.getException().getMessage());
        };
    }

}
