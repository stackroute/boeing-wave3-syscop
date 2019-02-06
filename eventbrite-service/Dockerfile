FROM openjdk:11

ADD ./target/nextevent-0.0.1-SNAPSHOT.jar /event/src/nextevent-0.0.1-SNAPSHOT.jar
EXPOSE 8082
WORKDIR event/src
ENTRYPOINT ["java","-jar","nextevent-0.0.1-SNAPSHOT.jar"]
