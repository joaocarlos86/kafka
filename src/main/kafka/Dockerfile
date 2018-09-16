FROM ubuntu:16.04

ENV KAFKA_HOME /usr/local/kafka
ADD ./start-kafka.sh /scripts/

# install java + others
RUN apt-get update && apt-get install -y \
  wget \
  openjdk-8-jdk

# install kafka
RUN wget http://www-eu.apache.org/dist/kafka/2.0.0/kafka_2.11-2.0.0.tgz && \
  tar -xzf kafka_2.11-2.0.0.tgz && \
  mv kafka_2.11-2.0.0 $KAFKA_HOME

CMD ["/scripts/start-kafka.sh"]
