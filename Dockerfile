FROM java:8 
EXPOSE 8090
ADD target target
ENTRYPOINT ["java","-jar","target/timesheet.war"]
