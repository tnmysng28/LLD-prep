Redis or Kafka

What to use for a chat application??

Redis is a distributed data store(in memory). Its fast. Primarily used for caching. 

-- Also used as a pub sub, transactions, etc(not just caching)


Kafka is a streaming service(distributed). Kafka is not in memory. 


Text based communtcation
Low Latency
Frequent messaging


Why would you want to use Redis??

For kafka - start zookeeper, start kafka, create topics for publishing and subscribing
