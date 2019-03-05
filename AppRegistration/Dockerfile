FROM openjdk:11.0-oracle
ADD ./target/AppRegistration-v1.0.5.jar /usr/src/AppRegistration-v1.0.5.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","AppRegistration-v1.0.5.jar"]
