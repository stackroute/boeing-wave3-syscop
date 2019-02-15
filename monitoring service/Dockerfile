FROM openjdk:11.0-oracle
ADD ./target/monitoring-service-0.0.1-SNAPSHOT.jar /usr/src/monitoring-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","monitoring-service-0.0.1-SNAPSHOT.jar"]
