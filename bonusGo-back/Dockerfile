# Para compilar el .jar que necesitamos despues
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Para la preparación del despliegue del contenedor del backend
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Necesitamos retrasar el arranque de spring para que se inicie primero mysql
RUN apk update && apk add --no-cache bash netcat-openbsd
RUN wget -O /app/wait-for-it.sh https://github.com/vishnubob/wait-for-it/raw/master/wait-for-it.sh \
    && chmod +x /app/wait-for-it.sh

# Copiar el JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Abre el puerto para la conexión con el back
EXPOSE 8080

# Espera a que MySQL esté disponible y luego arrancar el backend
ENTRYPOINT ["/app/wait-for-it.sh", "mysql_bonusgo:3306", "--", "java", "-jar", "app.jar"]
