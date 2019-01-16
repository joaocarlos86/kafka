# Simple C++ Kafka Producer Implementation

This project presents a way to create a C++ Kafka Producer using the librdkafka. [librdkafka](https://github.com/edenhill/librdkafka) is a C library implementation of the Apache Kafka protocol, containing both Producer and Consumer support. It was designed with message delivery reliability and high performance in mind, current figures exceed 1 million msgs/second for the producer and 3 million msgs/second for the consumer.

## What this application does?

The project is simple, it is composed by a class that provides the producer implementation and a main file that shows how to use the producer.

## How do I run it?

First, you will need the librdkafka, please refer to [librdkafka building](https://github.com/edenhill/librdkafka#building) instructions.

After building and installing the librdkafka you can compile the code using:

g++ main.cpp -o main.o -lrdkafka++ -I/usr/local/include/librdkafka -lrdkafka -I/usr/local/include -lpthread -lz -lrt -fPIC

After compiling (make sure to have a Kafka broker running and you created the topic this_is_a_topic), create a console consumer to consume the message that the application will send:

./kafka-console-consumer.sh --topic this_is_a_topic --bootstrap-server localhost:9092

Then, just execute the generated main.o file. The message should appear in the console consumer.

## How do I change the producer properties?

See the producer.cpp file and use the [librdkafka configuration reference](https://github.com/edenhill/librdkafka/blob/master/CONFIGURATION.md)

## But... why?

By using the librdkafka to implement the Kafka Protocol and enabling C/C++ applications to produce and consume messages from a Kafka Cluster we are ensuring to enable direct connectivity between devices that can't run a JVM without the need to have a middle guy between the device and the message broker, this may have the potential to lower the implementation costs in projects and leverage the throughput and robustness of the solution. The librdkafka only provides an asynchronous producer, all the messages are first inserted into an internal buffer and then sent after some conditions are met (refer to [librdkafka configuration reference](https://github.com/edenhill/librdkafka/blob/master/CONFIGURATION.md)).