FROM openjdk:11

ADD ./target/config-server-v3.0.1.jar /event/src/config-server-v3.0.1.jar
EXPOSE 8077
WORKDIR event/src
ENTRYPOINT ["java","-jar","config-server-v3.0.1.jar"]
