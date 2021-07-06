FROM openjdk:11
COPY pet-clinic-web/target/pet-clinic-web-0.0.1-SNAPSHOT.jar pet-clinic-web-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pet-clinic-web-0.0.1-SNAPSHOT.jar"]