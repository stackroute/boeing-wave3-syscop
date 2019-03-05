FROM openjdk:11.0-oracle
ADD ./target/Data-Collector-v1.0.5.jar /usr/src/Data-Collector-v1.0.5.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","Data-Collector-v1.0.5.jar"]
