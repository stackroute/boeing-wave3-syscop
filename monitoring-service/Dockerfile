FROM openjdk:11.0-oracle
ADD ./target/monitoring-service-v1.0.4.jar /usr/src/monitoring-service-v1.0.4.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","monitoring-service-v1.0.4.jar"]
