FROM eclipse-temurin:21-jdk
COPY target/FlowGrid.jar FlowGridMono.jar
ENTRYPOINT ["java","-jar","FlowGridMono.jar"]