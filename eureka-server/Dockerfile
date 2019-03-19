FROM openjdk:11

ADD ./target/eureka-server-v3.0.1.jar /event/src/eureka-server-v3.0.1.jar
EXPOSE 8010
WORKDIR event/src
ENTRYPOINT ["java","-jar","eureka-server-v3.0.1.jar"]
