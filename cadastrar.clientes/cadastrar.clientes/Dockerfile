# Define a imagem base
FROM openjdk:17
EXPOSE 8080
LABEL maintainer="spring-boot-docker.jar"
# Configurações do MySQL
ENV MYSQL_HOST mysql
ENV MYSQL_PORT 3306
ENV MYSQL_DATABASE jazztech
ENV MYSQL_PASSWORD root

# Copia os arquivos necessários para o container
ADD target/spring-boot-docker.jar spring-boot-docker.jar

# Define o comando de execução
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]
