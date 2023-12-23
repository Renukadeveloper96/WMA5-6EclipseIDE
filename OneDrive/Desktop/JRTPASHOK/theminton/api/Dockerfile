FROM openjdk:17
EXPOSE 8087

COPY target/campaign-management.jar  campaign-management.jar

#Start service
ENTRYPOINT ["java","-jar","/campaign-management.jar"]


