# --- Estágio 1: Build da Aplicação ---
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app/reservaService

# Copia o parent pom.xml para o diretório acima do módulo
COPY ../pom.xml ../pom.xml

# Copia o pom.xml e o src do módulo reservaService
COPY reservaService/pom.xml ./pom.xml
COPY reservaService/src ./src

RUN mvn clean package -DskipTests

# --- Estágio 2: Imagem Final de Produção ---
FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE_PATH=target/reserva-sala-0.0.1-SNAPSHOT.jar
COPY --from=build /app/reservaService/${JAR_FILE_PATH} /app/application.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/application.jar"]