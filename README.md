## Curso Especialista Spring Rest - Algaworks

Um curso que vai desde o desenvolvimento de apis até o deploy em um ambiente real. Nesse curso podemos trabalhar com todos os pilares no desenvolvimento de apis, conta com a parte de boas práticas, testes end-to-end, segurança oauth2 (entendendo todos os fluxos de autenticação e implementando um authorization server a parte), e aplicação em containers docker.

**Repositórido do Authorization Server com Spring Security**

*  https://github.com/alkxyly/algafood-auth

**Execução utilizando Docker**

1. Gerando o build do projeto Spring
```maven
  mvnw clean package
```

2. Gerando a imagem do Projeto algafood-api
```maven
  docker image build -t algafood-api .
```
3. Opcionalmente pode ser gerado os passos 1 e 2 com um único comando, foi implementado usado a lib Dockerfile Maven, disponível em: https://github.com/spotify/dockerfile-maven
```maven
  mvnw package -Pdocker
```
4. Subindo as imagens com o docker-compose escalando com dois container do algafood-api para testar o balanceamento de carga implementado com nginx
```maven
  docker-compose up --scale algafood-api=2
```

## **Diagrama implementado no Curso:**

![WhatsApp Image 2021-08-30 at 09 51 28](https://user-images.githubusercontent.com/4734174/131342016-b45a48fb-20a7-4587-9cc9-90fb1a69676a.jpeg)

