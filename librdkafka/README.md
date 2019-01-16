Download lib
./configure
make
make install
ldconfig

LD_LIBRARY_PATH=/usr/local/lib

g++ main.cpp -o main.o -lrdkafka++ -I/usr/local/include/librdkafka -lrdkafka -I/usr/local/include -lpthread -lz -lrt -fPIC
LD_LIBRARY_PATH=/usr/local/lib
./zookeeper-server-start -daemon ../config/zookeeper.properties
./kafka-server-start ../config/server.properties
./kafka-topics.sh --create --topic this_is_a_topic --partitions 2 --replication-factor 1 --zookeeper localhost:2181

./kafka-run-class.sh kafka.tools.GetOffsetShell \
  --broker-list localhost:9092 \
  --topic this_is_a_topic --time -1 --offsets 1 \ 
  | awk -F  ":" '{sum += $3} END {print sum}'