From ubuntu:20.04

RUN if  [[ -z "$change_hw_mirror" ]]; then echo "ok"; else  sed -i "s@http://.*archive.ubuntu.com@http://repo.huaweicloud.com@g" /etc/apt/sources.list; fi
RUN if  [[ -z "$change_hw_mirror" ]]; then echo "ok"; else sed -i "s@http://.*security.ubuntu.com@http://repo.huaweicloud.com@g" /etc/apt/sources.list; fi

RUN apt update
RUN apt install -y openjdk-11-jdk

COPY target/survey-0.0.1-SNAPSHOT.jar  /
COPY docker-entrypoint.sh  /
ENTRYPOINT ["/docker-entrypoint.sh"]
EXPOSE 8080
