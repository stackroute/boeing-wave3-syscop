FROM openjdk:11

ADD ./target/zuul-gatewayservice-v1.0.2.jar /event/src/zuul-gatewayservice-v1.0.2.jar
EXPOSE 8095
WORKDIR event/src
ENTRYPOINT ["java","-jar","zuul-gatewayservice-v1.0.2.jar"]