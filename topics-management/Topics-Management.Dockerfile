FROM openjdk:11-jre

WORKDIR /app

ARG version=0.1.0

ENV TOPICS_MANAGEMENT_APP_JAR=topics-management-${version}.jar

COPY ${TOPICS_MANAGEMENT_APP_JAR} /app/lib/

RUN groupadd -r javaApp && \
    useradd -r -g javaApp javaApp

USER javaApp

EXPOSE 8080

CMD java -Dspring.profiles.active=docker -jar /app/lib/${TOPICS_MANAGEMENT_APP_JAR}


