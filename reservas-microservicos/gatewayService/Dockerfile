# Estágio de Build Otimizado
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o parent pom.xml e o pom.xml do módulo
COPY ../pom.xml ../
COPY gatewayService/pom.xml pom.xml

# Copia o código-fonte do módulo
COPY gatewayService/src ./src

RUN mvn clean package -DskipTests

# Estágio Final (imagem leve) - Permanece igual
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]