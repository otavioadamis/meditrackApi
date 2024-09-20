# Meditrack

## Descrição
Projeto Meditrack

## Frameworks Utilizados
- **Spring Boot**: Framework para criação de aplicações Java.
- **Spring Data JPA**: Abstração de persistência de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Flyway**: Migração de banco de dados.
- **Spring Security**: Segurança da aplicação.
- **Lombok**: Redução de código boilerplate.

## Instruções de Inicialização

### Pré-requisitos
- **Java 21**: Certifique-se de que o JDK 21 está instalado.
- **Maven**: Certifique-se de que o Maven está instalado.
- **PostgreSQL**: Certifique-se de que o PostgreSQL está instalado e em execução.

### Passos para Inicialização
1. **Clone o repositório**:
   ```sh
   git clone https://github.com/otavioadamis/meditrackApi.git
   cd seu-diretorio

2. **Criação do banco de dados**
- Crie um banco de dados PostgreSQL chamado meditrack
- Atualize as credenciais do banco de dados no arquivo src/main/resources/application.properties
faça isso de acordo com a sua maquina

3. **Compile o projeto**
    ```sh
    mvn clean install

4. **Execute a aplicação**
    ```sh
    mvn spring-boot:run

5. **Acesse a aplicação**
- No momento está disponivel apenas os testes de api, que podem ser testados acessando:
http://localhost:8080/swagger-ui/index.html

## Endpoints da API
**Cadastro de Usuário**
- URL: /api/usuario/cadastro
- Método: POST
- Corpo da Requisição
  ```sh
    {
  "nomeCompleto": "Seu Nome",
  "cpf": "98765432101",
  "email": "seu.email@example.com",
  "senha": "sua_senha",
  "dataNascimento": "2000-01-03T00:00:00.000Z"
    }

**Login de Usuário**
- URL: /api/usuario/login
- Método: POST
- Corpo da Requisição
  ```sh
    {
  "email": "seu.email@example.com",
  "senha": "sua_senha"
    }

**Listar todos os Usuários**
- URL: /api/usuario/listar
- Método: GET
- Resposta:
  ```sh
  [
    {
      "id": "f4f1608b-b56b-4a95-b4c7-cd6199157abf",
      "nomeCompleto": "Felipe Rêgo de Paula Castro",
      "cpf": "98765432101",
      "email": "felipe@email.com",
      "senha": "$2a$10$kJ7b4dkozhP/VfiOnxYBeumw2sFXR1.NzBNDVOePzcDDNee5FIh9q",
      "fotoPerfil": "dev-teste",
      "dataNascimento": "1996-06-03T03:00:00.000+00:00",
      "criadoEm": "2024-09-20T14:05:04.919+00:00",
      "enabled": true,
      "password": "$2a$10$kJ7b4dkozhP/VfiOnxYBeumw2sFXR1.NzBNDVOePzcDDNee5FIh9q",
      "authorities": [
        {
          "authority": "ROLE_USER"
        }
      ],
      "username": "felipe@email.com",
      "accountNonLocked": true,
      "accountNonExpired": true,
      "credentialsNonExpired": true
    },
    {
      "id": "ebd80539-f2a2-4946-afe9-a01de60ff112",
      "nomeCompleto": "teste",
      "cpf": "teste",
      "email": "teste@teste.com",
      "senha": "$2a$10$WRk52VUHdI4LGcBLuJ6x0OzOOi2FJsdUVo9luM9OoPNbC9lM68JCi",
      "fotoPerfil": "dev-teste",
      "dataNascimento": "1996-06-03T03:00:00.000+00:00",
      "criadoEm": "2024-09-20T19:11:09.284+00:00",
      "enabled": true,
      "password": "$2a$10$WRk52VUHdI4LGcBLuJ6x0OzOOi2FJsdUVo9luM9OoPNbC9lM68JCi",
      "authorities": [
        {
          "authority": "ROLE_USER"
        }
      ],
      "username": "teste@teste.com",
      "accountNonLocked": true,
      "accountNonExpired": true,
      "credentialsNonExpired": true
    }
  ]
