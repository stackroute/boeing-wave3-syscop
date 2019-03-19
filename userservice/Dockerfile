
FROM openjdk:11.0-oracle
ADD ./target/userservice-v3.0.1.jar /usr/src/userservice-v3.0.1.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","userservice-v3.0.1.jar"]
