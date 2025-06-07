# API para EcoAlert
Esse projeto é uma API RESTFul criada para a solução **EcoAlert:**, desenvolvida na linguagem Java e com SpringBoot. A ideia do projeto consiste em fornecer status climáticos de diferentes regiões, e alertas de eventos extremos de acordo com os dados recebidos de nossos sensores IoT, que capturam dados de diversos lugares.

## 👩‍👦‍👦 Equipe
- Felipe Seiki Hashiguti - RM98985
- Lucas Corradini Silveira - RM555118
- Matheus Gregorio Mota - RM557254

## ⚙ Configuração do Banco de Dados
No arquivo `application.properties` em `src/main/resources`, configure as credenciais do banco Oracle:
```
spring.datasource.url=${ORCL_URL}
spring.datasource.username=${ORCL_USERNAME}
spring.datasource.password=${ORCL_PASSWORD}
```

## ⚡ Executando Projeto
### Opção 1 - Executando pela classe Main:
> Execute a classe `GlobalSolutionsApplication`
>
> ![image](https://github.com/user-attachments/assets/716f9fb0-1f23-4d80-8404-cb8fe2b58201)

### Opção 2 - Executando pelo terminal:
> Execute os seguintes comandos no terminal:
> ```bash
> cd ecoalert-api
> mvn clean package
> ```
> Em seguida execute:
> ```bash
> java -jar target/ecoalert-0.0.1-SNAPSHOT.jar
> ```
## 📡 Endpoints

### 🌦 EcoAlertaController

- `GET /api/status-climatico?localizacao={localizacao}`
  Obtém o status climático de uma determinada `localizacao`.
- `GET /api/alerta?localizacao={localizacao}`
  Obtém um alerta climático de uma determinada `localizacao`.
- `POST /api/dados-climaticos`
  Recebe e processa os dados enviados pelos sensores IoT.
  Corpo da requisição:
  ```java
  {
    "temperatura": 30.5,
    "umidade": 70,
    "nivelAguaCm": 15,
    "porcentagemNivel": 60,
    "localizacao": "São Paulo",
    "latitude": -23.55,
    "longitude": -46.63
  }
  ```

### 📈 LimiarController

- `GET /api/limiar`
   Retorna todos os limites climáticos cadastrados.
- `GET /api/limiar/{id}`
   Retorna os dados de um limite climático específico pelo seu `id`.
- `POST /api/limiar`
   Cria um novo limite climático.
   Corpo da requisição:
   ```java
   {
    "parametroSensor": "temperatura",
    "valorMin": 10,
    "valorMax": 35,
    "msgMin": "Temperatura muito baixa!",
    "msgMax": "Temperatura muito alta!",
    "recomendacaoAlerta": "Procure abrigo ou fontes de aquecimento/resfriamento"
  }
   ```
- `PUT /api/limiar/{id}`
   Atualiza os dados de um limite climático existente pelo `id`.
- `DELETE /api/limiar/{id}`
   Remove um limite climático existente.

## 🔧 Testando Endpoints
- Rode a aplicação
- Acesse no navegador `http://localhost:8080/swagger-ui/index.html`
- A interface Swagger será exibida para testar os endpoints visualmente.
  ![image](https://github.com/user-attachments/assets/aa3eedae-43a3-4165-91ba-c77e098c610e)
  Nela você pode testar todos os endpoints. 

## 👨‍💻 Tecnologias Utilizadas
- Maven
- Java
- Spring Boot
- Spring Data JPA
- Banco de dados Oracle
