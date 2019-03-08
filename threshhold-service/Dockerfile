FROM openjdk:11.0-oracle
ADD ./target/threshhold-service-v1.0.5.jar /usr/src/threshhold-service-v1.0.5.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","threshhold-service-v1.0.5.jar"]
