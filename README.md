
## Construindo a imagem do banco de dados a partir do dockerfile
```shell
docker build -t my-oracle-xe-db .
```

## Rodando o conteiner em background  
```shell
docker run -d \
  --name oracle-xe-custom \
  -p 1521:1521 \
  -v oracle-xe-data:/opt/oracle/oradata \
  my-oracle-xe-db
```

```shell
cd ecoalert
docker build -t ecoalert .
``` 

```shell
docker run -d `
  --name ecoalert-api `
  -p 8080:8080 `
  -e HOST_URL=jdbc:oracle:thin:@host.docker.internal:1521/XEPDB1 `
  -e USERNAME=system `
  -e PASSWORD=051205 `
  ecoalert
``` 



docker ps

docker logs oracle-db

docker logs ecoalert-app