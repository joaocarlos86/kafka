package com.github.joaocarlos86.protobufkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ericsson.kafka.model.AddressBookProtos;
import com.ericsson.kafka.model.AddressBookProtos.Person;

@SpringBootApplication
public class Application implements CommandLineRunner {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ProtoSender sender;

    @Override
    public void run(String... strings) throws Exception {
        AddressBookProtos.Person.Builder personBuilder = AddressBookProtos.Person.newBuilder();
        personBuilder
            .setName("Darth Vader")
            .setEmail("darth.vader@deathstar.com")
            .setId(1);
        Person build = personBuilder.build();
        sender.send(build.toByteArray());
    }
}