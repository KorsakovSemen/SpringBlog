FROM openjdk:8
EXPOSE 8080
ADD target/blog.jar blog.jar
ENTRYPOINT ["java","-jar","/blog.jar"]