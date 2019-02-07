FROM openjdk:11

ADD ./target/eureka-server-0.0.1-SNAPSHOT.jar /event/src/eureka-server-0.0.1-SNAPSHOT.jar
EXPOSE 9091
WORKDIR event/src
ENTRYPOINT ["java","-jar","eureka-server-0.0.1-SNAPSHOT.jar"]