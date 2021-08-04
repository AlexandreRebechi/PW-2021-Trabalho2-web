#!/bin/sh
mvn clean package && docker build -t br.edu.ifsul/PW-2021-1-Trabalho2-Web .
docker rm -f PW-2021-1-Trabalho2-Web || true && docker run -d -p 9080:9080 -p 9443:9443 --name PW-2021-1-Trabalho2-Web br.edu.ifsul/PW-2021-1-Trabalho2-Web