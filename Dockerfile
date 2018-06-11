FROM jboss/wildfly:12.0.0.Final

MAINTAINER Sergey Cherkesov <sergey.cherkesov.1006@gmail.com>

COPY target/chatbot-1.0.0.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080 9990

CMD /opt/jboss/wildfly/bin/standalone.sh -bmanagement 0.0.0.0