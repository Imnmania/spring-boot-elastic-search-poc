## Elastic Search Implementation in Spring Boot  

* **Change the default password** 
```
~/development/elasticsearch-8.15.1/bin/elasticsearch-reset-password -u elastic --interactive
```
* **Create a keystore for using https using the provided certificate of elasticsearch**
```
keytool -import -file "~/development/elasticsearch-8.15.1/config/certs/http_ca.crt" -keystore "~/development/elasticsearch-8.15.1/config/certs/truststore.p12" -storepass <your-elasticsearch-password> -noprompt -storetype pkcs12
```
* **Check if the index has been created in elasticSearch**
```
https://localhost:9200/_cat/indices
```

* **[Documentation](https://www.javainuse.com/boot3/elast/1)**
