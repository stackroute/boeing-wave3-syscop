FROM openjdk:11

ADD ./target/zuul-gatewayservice-v3.0.0.jar /event/src/zuul-gatewayservice-v3.0.0.jar
EXPOSE 8095
WORKDIR event/src
ENTRYPOINT ["java","-jar","zuul-gatewayservice-v3.0.0.jar"]
