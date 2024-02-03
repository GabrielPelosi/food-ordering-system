# Food ordering application

## Set up workspace

### 1. Run root project docker-compose file
* This will set up the postgres database and pgadmin
* ```> docker-compose up```

### 2. Run kafka cluster

* Make sure you have an .env file with env variables settled

* open the /insfrastructure/docker-compose folder in your terminal
* Run these docker-compose in the displayed order
* ```.> docket-compose -f common.yml -f zookeeper.yml up```
* ```.> docket-compose -f common.yml -f kafka_cluster.yml up```
* ```.> docket-compose -f common.yml -f init_kafka.yml up```
* This will run the kafka cluster, zookeeper and will create all the topics and configure partitions
* Make sure to pass common.yml in all runs to create and set the container to the food-ordering docker network

### 3. Run maven compile goal to the root project (food-ordering-system)
* Click in the top right maven icon on IntelliJ, select lifecycle and run compile
* If you get errors in test goal, run without tests

### 4. Reload All maven project
* Again, go to the maven in top right icon
* click in the reload icon to reload all projects

### 5. If you are using IntelliJ ultimate, run all spring boot services, otherwise run one by one.
