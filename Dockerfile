From openjdk:17-jdk-slim
COPY ./build/libs/EmployeeAPI-0.0.1-SNAPSHOT.jar EmployeeAPI-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","EmployeeAPI-0.0.1-SNAPSHOT.jar"]