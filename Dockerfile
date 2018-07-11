FROM debian:jessie

#RUN apt-get update
#RUN apt-get install -y software-properties-common

RUN echo "deb http://http.debian.net/debian jessie-backports main" >> /etc/apt/sources.list
RUN apt-get update

RUN apt-get install -t jessie-backports -y openjdk-8-jdk && \
    javac -version && \
    java -version
RUN apt-get install -y maven

COPY ./ /code

RUN cd /code && mvn clean package

CMD java -jar /code/target/chatbot-1.0.0.jar