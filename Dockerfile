FROM openjdk:17

WORKDIR /app

COPY . /app 

RUN chown -R root:root /app

RUN chmod -R 777 /app

EXPOSE 8080

CMD ["java", "-jar", "/app/target/openaiApi-0.0.1-SNAPSHOT.jar"]