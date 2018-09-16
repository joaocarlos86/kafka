# Google Protocol Buffers and Kafka Hello World

This project shows how to use the Google Protocol Buffers to send messages to a Kafka Broker running inside a docker container.

This project is based on:

* Google Protocol Buffer - https://developers.google.com/protocol-buffers/
* Spring Boot - https://spring.io/projects/spring-boot
* Spring Kafka - https://spring.io/projects/spring-kafka
* Kafka (and Zookeeper) - https://kafka.apache.org/


## Google Protocol Buffers (from https://developers.google.com/protocol-buffers/)

Protocol buffers are Google's language-neutral, platform-neutral, extensible mechanism for serializing structured data – think XML, but smaller, faster, and simpler. You define how you want your data to be structured once, then you can use special generated source code to easily write and read your structured data to and from a variety of data streams and using a variety of languages. The definition of the data is made into .proto files, this file is interpreted by a compiler (C++) and it generates models for representing the proto file into several languages, this PoC uses Java as language.

## Spring Boot (from https://spring.io/projects/spring-boot)

Spring Boot makes it easy to create Spring-powered, production-grade applications and services with absolute minimum fuss. 

## Spring Kafka (from https://spring.io/projects/spring-kafka)

The Spring for Apache Kafka (spring-kafka) project applies core Spring concepts to the development of Kafka-based messaging solutions. It provides a "template" as a high-level abstraction for sending messages. It also provides support for Message-driven POJOs with @KafkaListener annotations and a "listener container". These libraries promote the use of dependency injection and declarative. In all of these cases, you will see similarities to the JMS support in the Spring Framework and RabbitMQ support in Spring AMQP.

## Kafka (from https://kafka.apache.org/intro.html)

Kafka® is used for building real-time data pipelines and streaming apps. It is horizontally scalable, fault-tolerant, wicked fast, and runs in production in thousands of companies.

## What this application does?

The objective of this application is to use Google Protocol Buffers to create an optimized message, send it to a Kafka Broker and then read the message back.

The application uses Spring Boot to provide the basic infrastructure of the application, Spring Kafka is used for providing  the foundations of message producers and consumers and Google Protocol Buffers is being used to generate a model of the message.

The Kafka Broker is running into a Docker container (but this is not a requirement).

## How to run?

You will need the GPB to run this application, you will need to set the location of the binary of GPB into the pom.xml file. The GPB will interpret the proto file and will generate the Java classes that are being referred into the project. You can download the binaries from https://github.com/protocolbuffers/protobuf/releases/tag/v3.6.1 (download the protoc-3.6.1 for you runtime).

1) Create a new Docker network
```bash
	docker network create kafka
```
2) Start a new Zookeeper container
```bash
	run --name zookeeper --network kafka -d wurstmeister/zookeeper
```
3) Start a new Kafka container
```bash
	docker run --name kafka --network kafka -e KAFKA_ADVERTISED_HOST_NAME=127.0.0.1 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -p 9092:9092 -d wurstmeister/kafka
```
4) Create a new topic
```bash
	docker exec -it kafka /opt/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic gpb.t
```
5) Run the application
```bash
	mvn clean spring-boot:run
```
	
Magic.