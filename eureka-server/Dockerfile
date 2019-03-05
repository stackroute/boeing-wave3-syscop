FROM openjdk:11

ADD ./target/eureka-server-v1.0.4.jar /event/src/eureka-server-v1.0.4.jar
EXPOSE 8010
WORKDIR event/src
ENTRYPOINT ["java","-jar","eureka-server-v1.0.4.jar"]
