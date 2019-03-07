FROM openjdk:11.0-oracle
ADD ./target/alert-service-v1.0.6.jar /usr/src/alert-service-v1.0.6.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","alert-service-v1.0.6.jar"]
