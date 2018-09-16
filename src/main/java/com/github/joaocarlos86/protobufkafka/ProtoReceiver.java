package com.github.joaocarlos86.protobufkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.ericsson.kafka.model.AddressBookProtos.Person;
import com.google.protobuf.InvalidProtocolBufferException;

@Service
public class ProtoReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(ProtoReceiver.class);

    @KafkaListener(topics = "${app.topic.gpb}")
    public void listen(@Payload final byte[] message) throws InvalidProtocolBufferException {
        final Person person;
        person = Person.parseFrom(message);
        LOG.info("received e-mail='{}'", person.getEmail());
    }

}