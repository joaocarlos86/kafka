package com.github.protobufkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProtoSender {

    private static final Logger LOG = LoggerFactory.getLogger(ProtoSender.class);
    
    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Value("${app.topic.gpb}")
    private String topic;

    public void send(final byte[] message){
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
