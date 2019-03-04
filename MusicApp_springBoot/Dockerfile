FROM openjdk:11.0-oracle
ADD ./target/MusicApp-0.0.1-SNAPSHOT.jar /usr/src/MusicApp-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","MusicApp-0.0.1-SNAPSHOT.jar"]