FROM openjdk:8
ADD target/dockers-item-sale.jar dockers-item-sale.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "dockers-item-sale.jar"]