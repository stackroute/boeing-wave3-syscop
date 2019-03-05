FROM openjdk:11

ADD ./target/zuul-gatewayservice-v1.0.4.jar /event/src/zuul-gatewayservice-v1.0.4.jar
EXPOSE 8095
WORKDIR event/src
ENTRYPOINT ["java","-jar","zuul-gatewayservice-v1.0.4.jar"]
