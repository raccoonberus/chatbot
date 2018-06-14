all:
	mvn clean package

run:
	java -jar target/chatbot-1.0.0.jar