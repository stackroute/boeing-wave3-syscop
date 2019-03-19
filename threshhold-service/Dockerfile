FROM openjdk:11.0-oracle
ADD ./target/threshhold-service-v3.0.1.jar /usr/src/threshhold-service-v3.0.1.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","threshhold-service-v3.0.1.jar"]
