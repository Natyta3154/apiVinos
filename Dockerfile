# Imagen base de Java 21
FROM openjdk:21-jdk

# Copiamos el jar al contenedor
ARG JAR_FILE=target/AppVinos-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} AppVinos.jar

# Exponemos el puerto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "AppVinos.jar"]
