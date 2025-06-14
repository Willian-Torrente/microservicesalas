
# --- Estágio 1: Build da Aplicação ---
# Usamos uma imagem específica do Maven com OpenJDK 17
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# 1. Copia o pom.xml e baixa as dependências (isso cria uma camada de cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copia o restante do código-fonte
COPY src ./src

# 3. Compila e empacota a aplicação, pulando os testes
RUN mvn clean package -DskipTests

# --- Estágio 2: Imagem Final de Produção ---
# Usamos uma imagem Java "slim" para um resultado final leve
FROM openjdk:17-jdk-slim
WORKDIR /app

# O nome do JAR é definido pelo pom.xml (reserva-sala-....jar)
ARG JAR_FILE_PATH=target/reserva-sala-*.jar

# Copia apenas o JAR construído do estágio anterior
COPY --from=build /app/${JAR_FILE_PATH} /app/application.jar

# Expõe a porta em que a aplicação vai rodar
EXPOSE 8082

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/application.jar"]