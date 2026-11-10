# BFF Agendador de Tarefas

Backend for Frontend (BFF) do sistema de agendamento de tarefas. Centraliza o
acesso aos serviços de usuário, tarefas e notificação e expõe uma API única
para o front-end.

> Projeto desenvolvido durante o curso **JAVANAUTA**, o mesmo
> em que aprendi Spring Boot.

## Arquitetura
Front-end → BFF → usuario (PostgreSQL)
                → agendador_de_tarefas (MongoDB)
                → notificacao

## Por que OpenFeign
O BFF chama os outros micro serviços por interfaces Java anotadas, em vez de montar
requisições HTTP à mão. Sendo um alternativa util de juntar todas as requisições, apenas elas, em um unico microserviço sem precisar abrir cada API separadamente.

## Tecnologias
Java, Spring Boot, Spring Cloud OpenFeign, Maven, Docker, Swagger

## Repositórios relacionados
- usuario | agendador_de_tarefas | notificacao  (links)

## Como rodar
[docker compose up ... ou comandos Maven/Gradle]

## Documentação da API
Swagger: http://localhost:[porta]/swagger-ui/index.html
