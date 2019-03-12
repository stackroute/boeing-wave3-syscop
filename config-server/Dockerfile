FROM openjdk:11

ADD ./target/config-server-v1.0.9.jar /event/src/config-server-v1.0.9.jar
EXPOSE 8077
WORKDIR event/src
ENTRYPOINT ["java","-jar","config-server-v1.0.9.jar"]
