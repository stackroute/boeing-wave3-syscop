
FROM openjdk:11.0-oracle
ADD ./target/userservice-v1.0.5.jar /usr/src/userservice-v1.0.5.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","userservice-v1.0.5.jar"]
