# Usa l'immagine di base OpenJDK 17 con Alpine per una build leggera 
FROM openjdk:17-jdk-alpine

ENV DIVER_PATH_IMAGE=/app/resources/

# Imposta una variabile per il percorso del file JAR
ARG JAR_FILE=target/*.jar

# Crea un utente non root per eseguire l'applicazione
RUN addgroup -S spring && adduser -S spring -G spring

# Copia il file JAR costruito nel container e lo rinomina in app.jar
COPY ${JAR_FILE} app.jar

# Crea la directory per il volume
RUN mkdir -p /app/resources



# Imposta l'utente per eseguire l'applicazione come non-root
USER spring:spring

# Espone la porta che verrà utilizzata dall'app Spring Boot (8080 per default)
EXPOSE 6060

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "/app.jar"]
