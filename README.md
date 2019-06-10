
# API compra de produto 
Este microserviço expõe um endpoint para que possamos simular a compra de um produto, retornando uma lista de parcelas, acrescidas de juros com base na taxa SELIC consultada online no serviço do Banco Central do Brasil, somente quando o número de parcelas for superior a 06 (seis) parcelas.
  
## Stack:  
  
**Java 8**  
**Spring-Boot**  
**Spring-Cloud-Feign**  
**Micrometer-Prometheus**  
**Lombok**
**Swagger**  
**Maven**  
**Junit**  
  
Projeto pode ser executado com Maven com o comando abaixo:  
```  
mvn clean spring-boot:run  
```  
O projeto também pode ser executado através de sua classe *Main* no pacote *'br.com.tardelli.buyproduct'*

> BuyProductApplication.java

Ao rodar o projeto, a documentação da API estará disponível no via Swagger pelo endereço [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

O projeto também expõe o endpoint [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus) para monitoramento e coleta de metricas do microserviço via integração Prometheus/Micrometer/Grafana - [micrometer-spring-boot](https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector)

 - Saúde JVM
 - Quantidade de requisições aos endpoints
 - Média de tempo da requisição
 - Quantidade de erros na aplicação
 - Entre outras métricas

## Testes
E para rodar os testes unitários e testes de integração com Maven é só rodar o comando abaixo:
```  
mvn clean test  
``` 

## Sugestão de melhorias para um ambiente cloud integrado:

 1. Integrar o microserviço com **Service Discovery** *Eureka* da stack Spring Cloud Netflix
 2. Integrar o microserviço com **Gateway/proxy** dinâmico *Zull* da stack Spring Cloud Netflix
 3. Adicionar um **Circuit Breaker** para manter a resiliência e proteção a falhas do microserviço com *Hystrix* da stack Spring Cloud Netflix
