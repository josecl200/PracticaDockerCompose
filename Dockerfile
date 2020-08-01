FROM openjdk:8-jdk-alpine
LABEL maintainer="José C. Ureña <20160138@cce.pucmm.edu.do>"
ENV NOMBRE_APP = 'encuestacompose'
ENV spring.datasource.url='jdbc:mysql://192.168.77.10:3306/dbencuesta'
ENV spring.datasource.username='root'
ENV spring.datasource.password='root'
VOLUME /tmp
EXPOSE 80
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]