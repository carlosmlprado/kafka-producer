FROM openjdk:11
ADD target/kafka-producer-0.0.1-SNAPSHOT.jar kafka-producer
ENTRYPOINT ["java", "-jar", "kafka-producer"]
EXPOSE  9091