# Tutorial para rodar a aplicação

## Requisitos
### Ter o docker e docker compose instalado
### Caso queira subir localmente, aconselho subir pelo menos o banco de dados em um container docker
### Realizar um fork do repositório GIT

___
___
___

### O docker esta ajustado para realizar o build.
#### Acessar a pasta onde se encontra o docker compose e executar o comando:
```docker compose up -d --build```

#### Para parar os containers e excluir todas as imagens criadas executar o comando:
```docker compose down --rmi all```

#### Para parar os containers e excluir uma imagem específica executar o comando:
```docker compose down && docker rmi <ID da imagem>```