FROM java:openjdk-8
WORKDIR /
ADD target/chatbot-1.0.0.jar chatbot-1.0.0.jar
CMD java -jar chatbot-1.0.0.jar