FROM openjdk:11-jre-slim

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh


EXPOSE 8080
	
ENTRYPOINT ["java","-jar","app.jar"]