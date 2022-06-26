FROM java:8
VOLUME /tmp
ADD nocv-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 12345
ENTRYPOINT ["java","-jar","/app.jar"]