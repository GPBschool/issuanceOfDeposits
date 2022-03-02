FROM openjdk:17
ADD target/issuanceofdeposits-0.0.1-SNAPSHOT.jar deposit.jar
ENTRYPOINT ["java","-jar","deposit.jar"]
EXPOSE 8080
