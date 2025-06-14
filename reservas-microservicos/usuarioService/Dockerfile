# --- Estágio 1: Build da Aplicação ---
# Usamos uma imagem específica do Maven com OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app/usuarioService

# Copia o parent pom.xml para o diretório do módulo
COPY ../pom.xml ../pom.xml

# Copia o pom.xml e o src do módulo usuarioService
COPY usuarioService/pom.xml ./pom.xml
COPY usuarioService/src ./src

RUN mvn clean package -DskipTests

# --- Estágio 2: Imagem Final de Produção ---
# Usamos uma imagem Java "slim" para um resultado final leve
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/usuarioService/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]