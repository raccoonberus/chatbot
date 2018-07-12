FROM java:openjdk-8
WORKDIR /
ADD target/chatbot-1.0.0.jar chatbot-1.0.0.jar
ADD chatbot.properties chatbot.properties
CMD java -Dexternal.properties.file='chatbot.properties' -jar chatbot-1.0.0.jar
