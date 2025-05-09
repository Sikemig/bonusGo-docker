FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Necesitamos retrasar el arranque de spring para que se inicie primero mysql
RUN apk update && apk add --no-cache bash netcat-openbsd
RUN wget -O /app/wait-for-it.sh https://github.com/vishnubob/wait-for-it/raw/master/wait-for-it.sh \
    && chmod +x /app/wait-for-it.sh

# Copiar el archivo JAR de Spring Boot
COPY target/Bonus-Go-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Usar wait-for-it para esperar a que MySQL esté listo y luego iniciar el back
ENTRYPOINT ["/app/wait-for-it.sh", "mysql_bonusgo:3306", "--", "java", "-jar", "app.jar"]
