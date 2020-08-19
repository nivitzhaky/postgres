FROM openjdk:11
COPY target/postgres*.war /usr/src/postgres.war
COPY src/main/resources/application-docker.properties /opt/conf/application.properties
CMD ["java", "-jar", "/usr/src/postgres.war", "--spring.config.location=file:/opt/conf/application.properties"]

