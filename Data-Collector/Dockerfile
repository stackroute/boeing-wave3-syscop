FROM openjdk:11.0-oracle
ADD ./target/Data-Collector-v3.0.1.jar /usr/src/Data-Collector-v3.0.1.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","Data-Collector-v3.0.1.jar"]
