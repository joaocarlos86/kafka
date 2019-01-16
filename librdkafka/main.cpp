#include "producer.cpp"

int main(){
    CustomProducer custom_producer = CustomProducer();
    std::string message = "This message should go.";

    size_t message_size = message.length() * sizeof(unsigned char);
    const char * original_message = message.c_str();
    unsigned char * message_to_be_sent = ( unsigned char *) malloc(message_size);
    
    memcpy(message_to_be_sent, original_message, message_size);

    custom_producer.send_message(message_to_be_sent, message_size);

    free(message_to_be_sent);
}