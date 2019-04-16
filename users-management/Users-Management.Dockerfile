FROM openjdk:11-jre

WORKDIR /app

ARG version=0.1.0

ENV USERS_MANAGEMENT_APP_JAR=users-management-${version}.jar

COPY ${USERS_MANAGEMENT_APP_JAR} /app/lib/

RUN groupadd -r javaApp && \
    useradd -r -g javaApp javaApp

USER javaApp

EXPOSE 8080

CMD java -Dspring.profiles.active=docker -jar /app/lib/${USERS_MANAGEMENT_APP_JAR}


