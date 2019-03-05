FROM openjdk:11.0-oracle
ADD ./target/alert-service-v1.0.4.jar /usr/src/alert-service-v1.0.4.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","alert-service-v1.0.4.jar"]
