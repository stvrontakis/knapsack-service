# knapsack-service
A REST based client-server knapsack implementation with docker support

To run both client-server you will need: jdk1.8, maven and docker working in your environment

On project root do mvn clean install to build it.

On knapsack-server folder do: 

docker build -t knapsack-server . to build the server docker image

On knapsack-client folder do: 

docker build -t knapsack0client . to build the client docker image

To run the server on a local container do: 

docker run --rm -it -p 127.0.0.1:9000:9000 -p 127.0.0.1:9001:9001 --name knapsack-server1 knapsack-server

To run the client on a local container do:

docker run --rm --link knapsack-server1  -it -p 127.0.0.1:9005:9005 -p 127.0.0.1:9006:9006 knapsack-client

To run them as dropwizard jars do:

On project root do mvn clean install to build it.
On knapsack-server folder do: 

java -jar -Xms4096m -Xmx4096m /target/knapsack-server-1.0-SNAPSHOT.jar server server-configuration.yaml
On knapsack-client folder do: 

java -jar -Xms4096m -Xmx4096m /target/knapsack-client-1.0-SNAPSHOT.jar server client-configuration.yaml
