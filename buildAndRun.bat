@echo off
call mvn clean package
call docker build -t br.edu.ifsul/PW-2021-1-Trabalho2-Web .
call docker rm -f PW-2021-1-Trabalho2-Web
call docker run -d -p 9080:9080 -p 9443:9443 --name PW-2021-1-Trabalho2-Web br.edu.ifsul/PW-2021-1-Trabalho2-Web