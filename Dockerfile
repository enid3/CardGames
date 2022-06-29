FROM gradle:jdk11-alpine
RUN mkdir /home/gradle/build
COPY . /home/gradle/build

WORKDIR /home/gradle/build
RUN gradle build --no-daemon
COPY /build/libs/CardGames-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080/TCP
ENTRYPOINT ["java", "-jar", "app.jar"]
