# Dockerfile para o módulo salaService
###Para Produção
# --- Estágio 1: Build da Aplicação ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

# Copia o parent pom.xml e o módulo salaService
COPY ../pom.xml pom.xml
COPY salaService/pom.xml salaService/pom.xml
COPY salaService/src salaService/src

RUN mvn -f salaService/pom.xml clean package -DskipTests

# --- Estágio 2: Imagem Final de Produção ---
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/salaService/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]