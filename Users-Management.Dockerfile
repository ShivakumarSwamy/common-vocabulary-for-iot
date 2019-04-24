FROM maven:3.6.1-jdk-11-slim as mvn
WORKDIR /app
COPY . /app
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
ARG version=0.1.2
ENV USERS_MANAGEMENT_APP_JAR=users-management-${version}.jar
COPY --from=mvn /app/users-management/target/${USERS_MANAGEMENT_APP_JAR}  /app/lib/
RUN groupadd -r javaApp && \
    useradd -r -g javaApp javaApp
USER javaApp
EXPOSE 8080
CMD java -Dspring.profiles.active=docker -jar /app/lib/${USERS_MANAGEMENT_APP_JAR}
