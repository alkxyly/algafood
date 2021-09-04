## Curso Especialista Spring Rest - Algaworks

### **Descrição**

Um curso que vai desde o desenvolvimento de apis até o deploy em um ambiente real. Nesse curso podemos trabalhar com todos os pilares no desenvolvimento de apis, conta com a parte de boas práticas, testes end-to-end, segurança oauth2 (entendendo todos os fluxos de autenticação e implementando um authorization server a parte), e aplicação em containers docker.

**Repositórido do Authorization Server com Spring Security**

*  https://github.com/alkxyly/algafood-auth

### **Execução  e testando a aplicação utilizando Docker**

1. Gerando o build do projeto Spring
```maven
  mvnw clean package
```

2. Gerando a imagem do Projeto algafood-api
```maven
  docker image build -t algafood-api .
```
3. Opcionalmente pode ser gerado os passos 1 e 2 com um único comando, foi implementado usando a lib Dockerfile Maven, disponível em: https://github.com/spotify/dockerfile-maven
```maven
  mvnw package -Pdocker
```
4. Subindo as imagens com o docker-compose escalando com dois container do algafood-api para testar o balanceamento de carga implementado com nginx
* Foi utilizado a lib wait-for-it, usada para esperar o processo do mysql terminar de subir, disponível em: https://github.com/vishnubob/wait-for-it
```maven
  docker-compose up --scale algafood-api=2
```
## Testando o fluxo com OAuth2 (authorization_code)

* http://localhost:8080/oauth/authorize?client_id=foodanalytics&redirect_uri=http://www.foodanalytics.local:8082&response_type=code

## Requerimentos
* Java 11
* Spring Tool Suite 4 (IDE utilizada)
* Docker
* Client Mysql - consultar os dados (Mysql Workbench, utilizado)
* Postman

## Em Produção
São todas  ferramentas utilizadas para fazer o deploy no ambiente de produção;

* Amazon RDS (Relational Database Service gerenciado) - Banco de dados Mysql
* Redis - Regis Lab https://redis.com/
* Amazon S3 - Storage, armazenamento de imagens
* AWS Fargate - Containers Docker
* Amazon Elastic Container Registry (ECR) - Serviço para hospedar as imagens docker

## **Diagrama de Classes:**

![WhatsApp Image 2021-08-30 at 09 51 28](https://user-images.githubusercontent.com/4734174/131342016-b45a48fb-20a7-4587-9cc9-90fb1a69676a.jpeg)

