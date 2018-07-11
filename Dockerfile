#FROM ubuntu:14.04
FROM debian:jessie

RUN apt-get update
RUN apt-get install -y wget

RUN wget https://download.java.net/java/GA/jdk10/10.0.1/fb4372174a714e6b8c52526dc134031e/10//openjdk-10.0.1_linux-x64_bin.tar.gz
RUN tar xvzf openjdk-10.0.1_linux-x64_bin.tar.gz -C /opt

ENV JAVA_HOME /opt/jdk-10.0.1
ENV PATH $PATH:$JAVA_HOME/bin


#RUN apt-get install -y apt-get install openjdk-10-jdk
RUN java --version && javac --version

#RUN apt-get install -y maven
RUN wget http://apache-mirror.rbc.ru/pub/apache/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz
RUN tar xzvf apache-maven-3.5.4-bin.tar.gz -C /opt

ENV PATH $PATH:/opt/apache-maven-3.5.4/bin

COPY ./ /code

RUN cd /code && mvn clean package

CMD java -jar /code/target/chatbot-1.0.0.jar