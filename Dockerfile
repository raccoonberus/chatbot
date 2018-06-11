FROM jboss/wildfly:12.0.0.Final

MAINTAINER Sergey Cherkesov <sergey.cherkesov.1006@gmail.com>

COPY target/chatbot-1.0.0.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080 9990

#RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]