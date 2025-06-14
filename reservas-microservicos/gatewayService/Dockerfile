# Estágio de Build Otimizado
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# 1. Copia o pom.xml primeiro
COPY pom.xml .

# 2. Baixa as dependências
# Esta camada só será refeita se o pom.xml mudar
RUN mvn dependency:go-offline

# 3. Agora copia o resto do código fonte
# Esta camada só será refeita se os arquivos de código mudarem
COPY src ./src

# 4. Executa o build (agora muito mais rápido, pois as dependências já estão em cache)
RUN mvn clean package -DskipTests

# Estágio Final (imagem leve) - Permanece igual
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia apenas o artefato construído do estágio anterior
COPY --from=build /app/target/*.jar /app/service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "service.jar"]