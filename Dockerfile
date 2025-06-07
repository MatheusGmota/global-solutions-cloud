# Usa a imagem oficial do Oracle XE como base.
FROM gvenzl/oracle-xe

# Define a senha padrão para o banco de dados.
ENV ORACLE_PASSWORD="051205"

# Expõe a porta 1521.
EXPOSE 1521

# Cria um volume para persistência de dados.
VOLUME /opt/oracle/oradata