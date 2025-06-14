# Dockerfile para o módulo salaService
###Para Produção
# Etapa 1: Construção do JAR
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copia o parent pom.xml
COPY ../pom.xml ../pom.xml

# Copia o pom.xml e o src do módulo salaService
COPY salaService/pom.xml ./pom.xml
COPY salaService/src ./src

RUN mvn -f ./pom.xml clean package -DskipTests

# Etapa 2: Imagem final com a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/sala-service*.jar /app/sala-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "sala-service.jar"]