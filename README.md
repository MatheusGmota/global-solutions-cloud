
## Construindo a imagem do banco de dados a partir do dockerfile
```shell
docker build -t my-oracle-xe-db .
```

# Rodando o conteiner em background  
```shell
docker run -d \
  --name oracle-xe-custom \
  -p 1521:1521 \
  -v oracle-xe-data:/opt/oracle/oradata \
  my-oracle-xe-db
```
## Construindo a imagem do api a partir do dockerfile
```shell
cd ecoalert
docker build -t ecoalert .
``` 
# Rodando o conteiner em background 
```shell
docker run -d \
  --name ecoalert-api \
  -p 8080:8080 \
  -e HOST_URL=jdbc:oracle:thin:@host.docker.internal:1521/XEPDB1 \
  -e USERNAME=system \
  -e PASSWORD=051205 \
  ecoalert
```
## JSON's usados nos testes
* `POST /api/dados-climaticos`
  Recebe e processa os dados enviados pelos sensores IoT.
  Corpo da requisição:
  ```java
  {
    "temperatura": 30.5,
    "umidade": 70,
    "nivelAguaCm": 15,
    "porcentagemNivel": 60,
    "localizacao": "São Paulo",
    "estado": "São Paulo",
    "cidade": "Osasco",
    "latitude": "-23.55",
    "longitude": "-46.63"
  }
  ```
* `POST /api/limiar`
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
