FROM openjdk:8
ADD target/kube-demo-0.0.1-SNAPSHOT.jar kube-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","kube-demo-0.0.1-SNAPSHOT.jar"]