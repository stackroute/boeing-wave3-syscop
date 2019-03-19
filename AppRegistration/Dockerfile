FROM openjdk:11.0-oracle
ADD ./target/AppRegistration-v3.0.1.jar /usr/src/AppRegistration-v3.0.1.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","AppRegistration-v3.0.1.jar"]
