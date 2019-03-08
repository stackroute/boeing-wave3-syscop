FROM openjdk:11.0-oracle
ADD ./target/threshold-service-v1.0.6.jar /usr/src/threshold-service-v1.0.6.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","threshold-service-v1.0.6.jar"]
