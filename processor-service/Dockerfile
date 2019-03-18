FROM openjdk:11.0-oracle
ADD ./target/processor-service-v3.0.0.jar /usr/src/processor-service-v3.0.0.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","processor-service-v3.0.0.jar"]
