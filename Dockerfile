FROM openjdk:13-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/app.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dsun.jnu.encoding=UTF-8","-Dfile.encoding=UTF8","-jar","/app.jar"]