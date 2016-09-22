FROM ubuntu:14.04

MAINTAINER Stelios Vrontakis <stelios.vrontakis@comeon.com>

ENV DEBIAN_FRONTEND=noninteractive

RUN echo "Updating Ubuntu"
RUN apt-get clean
RUN apt-get update
RUN apt-get -y upgrade
RUN apt-get install -y software-properties-common

RUN echo "Installing Java 8"
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update
RUN dpkg-reconfigure debconf
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections
RUN apt-get install -y --force-yes oracle-java8-installer

ADD target/knapsack-client-1.0-SNAPSHOT.jar /data/knapsack-client.jar
ADD client-configuration.yaml /data/configuration.yaml

#Install & configure Supervisor
RUN echo "Installing Supervisor for Docker container"
RUN apt-get -y install supervisor
RUN mkdir -p /var/log/supervisor
ADD supervisord.conf /etc/supervisor/conf.d/supervisord.conf

EXPOSE 9005
EXPOSE 9006

#CMD ["java","-jar","/data/knapsack-client.jar server /data/configuration.yaml"]

#Run Supervisor
CMD ["/usr/bin/supervisord"]