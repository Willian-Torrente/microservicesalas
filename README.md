# Reservas de Salas - Microserviços

Este projeto é um sistema de **reservas de salas** desenvolvido com arquitetura de **microserviços** utilizando **Spring Boot**, **Spring Cloud Gateway**, **Docker** e **Docker Compose**.

---

## 📦 Estrutura dos Microserviços

- **gatewayService**: API Gateway (roteamento e CORS)
- **usuarioService**: Gerenciamento de usuários
- **salaService**: Gerenciamento de salas
- **reservaService**: Gerenciamento de reservas
- **Banco de Dados**: Um PostgreSQL para cada serviço
- **RabbitMQ**: Mensageria entre serviços
- **Adminer**: Interface web para bancos de dados

---

## 🚀 Como rodar o projeto

### Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- (Opcional) [Java 17+](https://adoptium.net/) e [Maven](https://maven.apache.org/) para builds locais

### Passos

1. **Clone o repositório**
    ```sh
    git clone <git@github.com:Willian-Torrente/microservicesalas.git>
    cd reservas-microservicos
    ```

2. **(Opcional) Compile os serviços localmente**
    ```sh
    ./mvnw clean package -DskipTests
    ```

3. **Suba os containers**
    ```sh
    docker compose up --build
    ```

4. **Acesse os serviços**
    - **Gateway:** [http://localhost:8080](http://localhost:8080)
    - **Adminer:** [http://localhost:8090](http://localhost:8090)  
      (user: `postgres`, senha: `postgres`)

---

## 🗺️ Endpoints principais

Todos os acessos passam pelo **API Gateway**:

| Serviço         | Endpoint Gateway           | Porta interna |
|-----------------|---------------------------|--------------|
| Usuários        | `/users/**`               | 8081         |
| Salas           | `/salas/**`               | 8082         |
| Reservas        | `/reservas/**`            | 8083         |

Exemplo de requisição:
```sh
curl http://localhost:8080/users
```

---

## ⚙️ Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Spring Cloud Gateway**
- **Spring Data JPA**
- **PostgreSQL**
- **RabbitMQ**
- **Docker & Docker Compose**

---

## 📝 Estrutura de Pastas

```
reservas-microservicos/
├── docker-compose.yml
├── gatewayService/
├── usuarioService/
├── salaService/
├── reservaService/
├── ...
```

---

## 🛠️ Dicas de uso

- Para ver logs de um serviço:
  ```sh
  docker logs <nome-do-container>
  ```
- Para acessar o banco via Adminer:
  - Sistema: PostgreSQL
  - Servidor: `userdb`, `saladb` ou `reservadb`
  - Usuário: `postgres`
  - Senha: `postgres`

---

## 🧑‍💻 Contribuição

1. Fork este repositório
2. Crie uma branch (`git checkout -b feature/sua-feature`)
3. Commit suas alterações (`git commit -am 'feat: minha feature'`)
4. Push para a branch (`git push origin feature/sua-feature`)
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está sob a licença
