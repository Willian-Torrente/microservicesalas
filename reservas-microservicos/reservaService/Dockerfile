
# --- Estágio 1: Build da Aplicação ---
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# 1. Copia o pom.xml e baixa as dependências (camada de cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copia o código-fonte
COPY src ./src

# 3. Compila e empacota a aplicação
# -DskipTests pula a execução de testes durante o build da imagem
RUN mvn clean package -DskipTests

# --- Estágio 2: Imagem Final de Produção ---
FROM openjdk:17-jdk-slim
WORKDIR /app

# Argumento para definir o nome do JAR
# <<< AJUSTE CRÍTICO: O nome do JAR agora corresponde ao salaService
ARG JAR_FILE_PATH=target/sala-service.jar

# Copia o JAR construído do estágio de build para a imagem final
COPY --from=build /app/${JAR_FILE_PATH} /app/application.jar

# Expõe a porta que a aplicação irá usar
EXPOSE 8082

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/application.jar"]