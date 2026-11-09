FROM tomcat:9-jdk8

# Remove default Tomcat sample applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy CMS application into the ROOT webapp (accessible at base URL /)
COPY . /usr/local/tomcat/webapps/ROOT/

# Compile all updated Java servlets directly inside the container
RUN javac -cp "/usr/local/tomcat/lib/*:/usr/local/tomcat/webapps/ROOT/WEB-INF/lib/*" /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/*.java

EXPOSE 8080
CMD ["catalina.sh", "run"]
