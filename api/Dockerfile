FROM ubuntu:latest

RUN apt-get update -y && apt-get upgrade -y

RUN apt-get install -y openjdk-11-jdk

# if necessary
RUN apt-get install -y vim

RUN mkdir -p /wov/app-log /wov/gc-log /wov/files /tools

ADD build/libs/*.jar /wov/app/app.jar

VOLUME ["/wov/app-log", "/wov/gc-log", "/wov/files", "/tools"]

EXPOSE 10001 11619

ADD start.sh /wov/app/

RUN chmod a+x /wov/app/start.sh

ENTRYPOINT ["/bin/bash", "/wov/app/start.sh"]