Redis or Kafka

What to use for a chat application??

Redis is a distributed data store(in memory). Its fast. Primarily used for caching. 

-- Also used as a pub sub, transactions, etc(not just caching)


Kafka is a streaming service(distributed). Kafka is not in memory. Kafka the events are intended to be consumed by multiple consumers. 




Text based communtcation
Low Latency
Frequent messaging


Why would you want to use Redis??

For kafka - start zookeeper, start kafka, create topics for publishing and subscribing


There are 4 reasons 
1. SPEED
Redis is in-memory , so it is faster. Where was Kafka has to make disk operations. 

2. STORAGE REQUIREMENT
Redis does not store messages , they just pass through to all the consumers 
So, you only need to increase the processing and not the storage. Where-as in Kafka you need both compute and storage. 

3. COST OF CREATING A TOPIC
Kafka: Has extra cost because the partition needs to be created in all the Redis:  no cost of creating a new channel.  In fact no channel is created, you just send a message to a channel and you are good. 

4. COST OF DELETING TOPIC 
Deleting a topic in Kafka is not straight forward.  Sometimes, delete topic operation takes long time and then you have to manually delete the topics from server. 