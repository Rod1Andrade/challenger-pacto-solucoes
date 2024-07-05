# README: Pacto Soluções RH

Este repositório contém uma aplicação de RH Dockerizada, composta por um backend RESTful e um frontend Angular.

## Pré-requisitos

Certifique-se de ter o Docker e o Docker Compose instalados na sua máquina.

- Docker: [Instalação do Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Instalação do Docker Compose](https://docs.docker.com/compose/install/)

## Rodando a aplicação

1. Clone este repositório:

   ```bash
   git clone https://github.com/Rod1Andrade/challenger-pacto-solucoes
   cd challenger-pacto-solucoes
    ```

2. Execute o Docker Compose para iniciar os serviços:

   ```bash
   docker-compose up --build
    ```
3. Acesse a aplicação frontend:

    Abra seu navegador e vá para http://localhost:4200. Você deverá ver a interface do usuário da aplicação.

4. API RESTful:

    A API RESTful estará disponível em http://localhost:8080. Você pode acessar e testar os endpoints conforme necessário.

## Caso tenha problemas com o docker.

Versões:

- Java 17
- Spring 3.3.1
- Angular 17
- Node 22.0.0
- Postgres 14