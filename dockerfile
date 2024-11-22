# Temel imaj
FROM openjdk:17-jdk-slim

# Uygulamanın çalışacağı dizin
WORKDIR /aero

# JAR dosyasını container'a kopyala
COPY target/aero-0.0.1-SNAPSHOT.jar app.jar

# Uygulamanın çalıştırılma komutu
ENTRYPOINT ["java", "-jar", "app.jar"]