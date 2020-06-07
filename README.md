# hello-spring-boot

### Database

- run `docker-compose up -d`
- exec to postgres `docker exec -it postgres-spring psql -U postgres`
- create table name `demodb`
- select table `demodb`
- add extension `CREATE EXTENSION "uuid-ossp";`
- insert starter data `INSERT INTO person (id, name) VALUES (uuid_generate_v4(), 'Jack');`

### Swagger

- http://localhost:9090/swagger-ui.html 