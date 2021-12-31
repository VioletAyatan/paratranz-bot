FROM java:8-jre

WORKDIR /opt/tranzai

ENV JAR_NAME = tranzai.jar

COPY /target/tranzai-0.0.1-SNAPSHOT.jar /opt/tranzai/$JAR_NAME

RUN java -jar $JAR_NAME