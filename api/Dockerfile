FROM openjdk:11

RUN mkdir -p /wov/app /wov/app-log /wov/gc-log /wov/uploaded

ADD build/libs/*.jar /wov/app/app.jar

VOLUME ["/wov/app-log", "/wov/gc-log", "/wov/files"]

EXPOSE 10001 11619

ADD start.sh /wov/app/

RUN chmod a+x /wov/app/start.sh

ENTRYPOINT ["/bin/bash", "/wov/app/start.sh"]