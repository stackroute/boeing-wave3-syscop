FROM openjdk:11.0-oracle
ADD ./target/processor-service-v1.0.5.jar /usr/src/processor-service-v1.0.5.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","processor-service-v1.0.5.jar"]
