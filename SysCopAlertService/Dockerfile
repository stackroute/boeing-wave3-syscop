FROM openjdk:11.0-oracle
ADD ./target/alert-service-v3.0.1.jar /usr/src/alert-service-v3.0.1.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","alert-service-v3.0.1.jar"]
