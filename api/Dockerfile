FROM ubuntu:latest

RUN apt-get update -y && apt-get upgrade -y

RUN apt-get install -y openjdk-11-jdk

# for download files
RUN apt-get install -y vim wget
# for async-profiler
#RUN wget https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.9/async-profiler-2.9-linux-arm64.tar.gz

RUN mkdir -p /wov/app /wov/app-log /wov/gc-log /wov/files

ADD build/libs/*.jar /wov/app/app.jar

VOLUME ["/wov/app-log", "/wov/gc-log", "/wov/files"]

EXPOSE 10001 11619

ADD start.sh /wov/app/

RUN chmod a+x /wov/app/start.sh

ENTRYPOINT ["/bin/bash", "/wov/app/start.sh"]