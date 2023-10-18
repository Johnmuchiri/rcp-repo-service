FROM openjdk:17
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./build/libs/redpharma-repository-service-0.0.1-SNAPSHOT.jar ./redpharma-repository-service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
CMD ["java","-jar","redpharma-repository-service-0.0.1-SNAPSHOT.jar"]