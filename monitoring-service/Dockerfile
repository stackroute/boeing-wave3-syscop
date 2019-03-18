FROM openjdk:11.0-oracle
ADD ./target/monitoring-service-v3.0.0.jar /usr/src/monitoring-service-v3.0.0.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","monitoring-service-v3.0.0.jar"]
