FROM centos

RUN  sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-*
RUN  sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-*
RUN yum update -y
RUN yum install -y java-11-openjdk-devel

WORKDIR /app

ARG JAR_FILE=/target/raffle-api-1.0.jar
ADD ${JAR_FILE} /app/myapp.jar
RUN sh -c 'touch /app/myapp.jar'

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/app/myapp.jar", "-DskipTests=true"]
