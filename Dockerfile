FROM jdk1.8:20241127
#FROM debian:stable-20220822
#FROM openjdk:8
#FROM harbor.dscloud.com:8443/library/jdk1.8:20241127

COPY run.sh /root/run.sh
#RUN /bin/bash -c chmod +x /root/run.sh
COPY target/imageWebDemo-0.0.1-SNAPSHOT.jar  /root/imageWebDemo-0.0.1-SNAPSHOT.jar
#ADD /Users/jimmysong/Downloads/jdk1.8.0_202_x64 /root/
#RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone
#RUN timedatectl set-timezone Asia/Shanghai
#COPY run.sh /root/
#RUN chmod +x /root/run.sh
WORKDIR /root
ENV LANG C.UTF-8
EXPOSE 18084
ENTRYPOINT ["/bin/bash", "-c", "/root/run.sh"]
#ENTRYPOINT /root/run.sh