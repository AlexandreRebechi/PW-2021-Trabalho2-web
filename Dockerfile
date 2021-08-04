FROM open-liberty:kernel-java11
COPY --chown=1001:0  target/PW-2021-1-Trabalho2-Web.war /config/dropins/
COPY --chown=1001:0  server.xml /config