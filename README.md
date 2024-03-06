# README - Cadastro de Pessoas Java Web Application

Este é um aplicativo Java Web para cadastro de pessoas, desenvolvido utilizando Java 8, JSF, PrimeFaces 8 e EJB. O banco de dados utilizado é o PostgreSQL.

## Pré-requisitos

Para executar este projeto, você precisará ter instalado:

- Java Development Kit (JDK) 8 ou superior
- Docker Compose
- NetBeans IDE (preferencialmente versão 14)
- Servidor GlassFish 4.1.1

Certifique-se de que o Docker Compose está instalado e em execução para criar o banco de dados PostgreSQL.

## Configuração do Banco de Dados

Dentro da pasta `db` em `src/main/resources`, existe um script que precisa ser executado para criar as tabelas necessárias no banco de dados. 

Para isso, execute o seguinte comando na raiz do projeto:

docker-compose up


Isso iniciará um container Docker com o banco de dados PostgreSQL.

Após o banco de dados estar em execução, execute o script SQL `create_tables.sql` localizado em `src/main/resources/db` para criar as tabelas necessárias.

## Configuração do Ambiente de Desenvolvimento

1. Abra o projeto no NetBeans IDE.
2. Configure o servidor GlassFish 4.1.1 no NetBeans.
3. Compile e implante o projeto no servidor GlassFish.

Certifique-se de que o servidor GlassFish está configurado corretamente e em execução antes de implantar o projeto.

## Executando o Projeto

Após seguir as etapas acima, você pode acessar o aplicativo em um navegador da web.


