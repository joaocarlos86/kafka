#include "rdkafkacpp.h"
#include "iostream"
#include "cstdlib"
#include "string.h"

class CustomProducer {

    RdKafka::Conf *producer_configuration;
    RdKafka::Producer *producer;
    RdKafka::Conf *topic_configuration;
    RdKafka::Topic *topic;

    public:
        CustomProducer();
        ~CustomProducer();
        void send_message(unsigned char*, const size_t);
};