FROM mysql:8.0
MAINTAINER dicarlo1983emiliano@gmail.com

ENV MYSQL_ROOT_PASSWORD=Jacopo2012
ENV MYSQL_DATABASE=diver

ADD diver.sql /docker-entrypoint-initdb.d

EXPOSE 3306
