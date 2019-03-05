FROM openjdk:11.0-oracle
ADD ./target/dashboard-service-v1.0.5.jar /usr/src/dashboard-service-v1.0.5.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","dashboard-service-v1.0.5.jar"]
