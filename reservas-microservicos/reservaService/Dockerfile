# --- Estágio 1: Build da Aplicação ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

COPY ../pom.xml pom.xml
COPY reservaService/pom.xml reservaService/pom.xml
COPY reservaService/src reservaService/src

RUN mvn -f reservaService/pom.xml clean package -DskipTests

# --- Estágio 2: Imagem Final de Produção ---
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/reservaService/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]