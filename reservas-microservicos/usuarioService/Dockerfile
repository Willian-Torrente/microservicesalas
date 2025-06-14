# --- Estágio 1: Build da Aplicação ---
# Usamos uma imagem específica do Maven com OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

# Copia o parent pom.xml e o módulo usuarioService
COPY ../pom.xml pom.xml
COPY pom.xml usuarioService/pom.xml
COPY src usuarioService/src

RUN mvn -f usuarioService/pom.xml clean package -DskipTests

# --- Estágio 2: Imagem Final de Produção ---
# Usamos uma imagem Java "slim" para um resultado final leve
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/usuarioService/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]