FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    docker-compose \
    postgresql-client

COPY . /app

WORKDIR /app

RUN docker-compose -f stack.yaml up -d && \
    sleep 3 && \
    docker cp data/ddl.sql postgressql:/var/lib/postgresql/data && \
    docker exec postgressql psql -h localhost -U admin -d postgres -a -f /var/lib/postgresql/data/ddl.sql

CMD ["docker-compose", "-f", "stack.yaml", "down"]