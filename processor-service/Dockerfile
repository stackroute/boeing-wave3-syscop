FROM openjdk:11.0-oracle
ADD ./target/processor-service-v3.0.1.jar /usr/src/processor-service-v3.0.1.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","processor-service-v3.0.1.jar"]
