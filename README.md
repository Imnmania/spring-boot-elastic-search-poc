## ElasticSearch Implementation in Spring Boot  

## `Added Dependencies`

1. spring-boot-starter-data-elasticsearch
2. elasticsearch-java
3. jackson-databind
4. spring-boot-starter-web
5. modelmapper
6. lombok
---

## `Work with ElasticSearch local install`

* **Change the default password** 
```
~/development/elasticsearch-8.15.1/bin/elasticsearch-reset-password -u elastic --interactive
```

* **Create a keystore for using https using the provided certificate of ElasticSearch**
```
keytool -import -file "~/development/elasticsearch-8.15.1/config/certs/http_ca.crt" -keystore "~/development/elasticsearch-8.15.1/config/certs/truststore.p12" -storepass <your-elastic-user-password> -noprompt -storetype pkcs12
```

* **Check if the index has been created in ElasticSearch**
```
https://localhost:9200/_cat/indices?v
```
---

## `Work with ElasticSearch on Docker Container`

* **Create a Docker container for ElasticSearch**
```
docker run --name es01 --net elastic -p 9200:9200 -it -m 1GB docker.elastic.co/elasticsearch/elasticsearch:8.15.1
```

* **Reset the password for user `elastic` from the container**
```
/usr/share/elasticsearch/bin/elasticsearch-reset-password -u elastic --interactive
```

* **Check which keys are already present within the container**
```
docker exec -it es01 ls /usr/share/elasticsearch/config/certs
```

* **Create a truststore for the ElasticSearch in Docker (preferably with the same password)**
```
docker exec -it es01 /usr/share/elasticsearch/jdk/bin/keytool -import -file /usr/share/elasticsearch/config/certs/http_ca.crt -keystore "/usr/share/elasticsearch/config/certs/truststore.p12" -storepass <your-elastic-user-password> -noprompt -storetype pkcs12
```

* **Copy the newly created truststore and keep it in a safe place**
```
docker cp es01:/usr/share/elasticsearch/config/certs/truststore.p12 <your-location>
```