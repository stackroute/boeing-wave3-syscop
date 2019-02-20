FROM openjdk:11.0-oracle
ADD ./target/dataCollector-0.0.1-SNAPSHOT.jar /usr/src/dataCollector-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/
EXPOSE 8001
ENTRYPOINT ["java","-jar","dataCollector-0.0.1-SNAPSHOT.jar"]
