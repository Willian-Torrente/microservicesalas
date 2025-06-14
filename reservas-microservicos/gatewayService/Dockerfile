# Estágio de Build Otimizado
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app/gatewayService

# Copia o parent pom.xml para o diretório do módulo
COPY ../pom.xml ../pom.xml

# Copia o pom.xml e o src do módulo gatewayService
COPY gatewayService/pom.xml ./pom.xml
COPY gatewayService/src ./src

RUN mvn clean package -DskipTests

# Estágio Final (imagem leve) - Permanece igual
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/gatewayService/target/*.jar /app/service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "service.jar"]