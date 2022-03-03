FROM openjdk:17
ADD target/issuanceofdeposits-0.0.1-SNAPSHOT.jar deposits.jar
#COPY init.sql /docker-entrypoint-initdb.d/
#ENV POSTGRES_DB deposits
#EXPOSE 8080
ENTRYPOINT ["java","-jar","deposits.jar"]