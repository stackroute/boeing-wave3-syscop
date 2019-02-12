
FROM openjdk:11.0-oracle
ADD ./target/userservice-0.0.1-SNAPSHOT.jar /usr/src/userservice-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","userservice-0.0.1-SNAPSHOT.jar"]