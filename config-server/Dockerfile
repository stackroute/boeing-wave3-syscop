FROM openjdk:11

ADD ./target/config-server-v1.0.6.jar /event/src/config-server-v1.0.6.jar
EXPOSE 8888
WORKDIR event/src
ENTRYPOINT ["java","-jar","config-server-v1.0.6.jar"]
