<h1> Crear imagenes docker </h1>

```
cd epcsd-spring-2023-notification || docker build -t notification:0.0.1-SNAPSHOT . || cd ..
cd epcsd-spring-2023-productcatalog || docker build -t productcatalog:0.0.1-SNAPSHOT . || cd ..
cd epcsd-spring-2023-user || docker build -t user:0.0.1-SNAPSHOT . || cd ..
```




productcatalog:

container_name: productcatalog

image: productcatalog:0.0.1-SNAPSHOT

platform: linux/amd64

restart: always

depends_on:

    - productdb

    - kafka

ports:

    - 18081:18081

environment:

DATABASE_HOST: productdb

DATABASE_PORT: 5432

KAFKA_HOST: kafka

JAVA_OPTS: "-Dfile.encoding=UTF-8"

user:

container_name: user

image: user:0.0.1-SNAPSHOT

platform: linux/amd64

restart: always

depends_on:

    - userdb

    - productcatalog

ports:

    - 18082:18082

environment:

DATABASE_HOST: userdb

DATABASE_PORT: 5432

PRODUCT_HOST: productcatalog

JAVA_OPTS: "-Dfile.encoding=UTF-8"

notification:

container_name: notification

image: notification:0.0.1-SNAPSHOT

platform: linux/amd64

restart: always

depends_on:

    - user

    - kafka

ports:

    - 18083:18083

environment:

KAFKA_HOST: kafka

USER_HOST: user
