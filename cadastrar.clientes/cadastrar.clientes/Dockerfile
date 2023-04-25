# Define a imagem base
FROM openjdk:17-jre-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos necessários para o container
COPY target/myproject.jar /app

# Define o comando de execução
CMD ["java", "-jar", "myproject.jar"]
