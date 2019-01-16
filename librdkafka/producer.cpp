#include "producer.h"

CustomProducer::CustomProducer(){
    std::string errstr;
    producer_configuration = RdKafka::Conf::create(RdKafka::Conf::CONF_GLOBAL);
    producer_configuration->set("bootstrap.servers", "127.0.0.1", errstr);
    producer_configuration->set("queue.buffering.max.messages", "1000000", errstr);
    producer_configuration->set("queue.buffering.max.kbytes", "10485760", errstr);

    producer = RdKafka::Producer::create(producer_configuration, errstr);
    topic_configuration = RdKafka::Conf::create(RdKafka::Conf::CONF_TOPIC);
    topic = RdKafka::Topic::create(producer, "this_is_a_topic", topic_configuration, errstr);

    if (!producer) {
        std::cerr << "Failed to create producer: " << errstr << std::endl;
        exit(1);
    }
}

CustomProducer::~CustomProducer(){
    if(producer->outq_len() > 0){
        producer->poll(100);
    }

    delete producer_configuration;
    delete producer;
    delete topic_configuration;
    delete topic;
}

void CustomProducer::send_message(unsigned char* message, const size_t message_size){
    const int32_t partition = 0;

    RdKafka::ErrorCode resp = producer->produce(topic, partition, RdKafka::Producer::RK_MSG_COPY, const_cast<unsigned char *>(message), message_size, NULL, NULL);

    if (resp != RdKafka::ERR_NO_ERROR)
        std::cout << "% Produce failed: " << producer->name() << " - " << RdKafka::err2str(resp) << std::endl;

    producer->poll(0);
}
