FROM maven:3.6.1-jdk-11-slim as mvn
WORKDIR /app
COPY . /app
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
ARG version=0.4.0
ENV TOPICS_MANAGEMENT_APP_JAR=topics-management-${version}.jar
COPY --from=mvn /app/topics-management/target/${TOPICS_MANAGEMENT_APP_JAR}  /app/lib/
RUN groupadd -r javaApp && \
    useradd -r -g javaApp javaApp
USER javaApp
EXPOSE 8080
CMD java -Dspring.profiles.active=docker -jar /app/lib/${TOPICS_MANAGEMENT_APP_JAR}
