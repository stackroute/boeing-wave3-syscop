FROM openjdk:11.0-oracle
ADD ./target/dashboard-service-v1.0.3.jar /usr/src/dashboard-service-v1.0.3.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","dashboard-service-v1.0.3.jar"]
