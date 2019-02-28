FROM openjdk:11

ADD ./target/eureka-server-v1.0.2.jar /event/src/eureka-server-v1.0.2.jar
EXPOSE 8010
WORKDIR event/src
ENTRYPOINT ["java","-jar","eureka-server-v1.0.2.jar"]