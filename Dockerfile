FROM openjdk:17
EXPOSE 8080
ADD target/gym-management-backend.jar gym-management-backend.jar
ENTRYPOINT ["java","-jar","/gym-management-backend.jar"]