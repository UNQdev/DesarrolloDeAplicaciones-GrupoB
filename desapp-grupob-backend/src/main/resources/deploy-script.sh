#!/bin/bash
#You must define the global variables $TOPCAT_PATH and $BACKEND_ROOT_PATH
TOMCAT_PATH=/opt/apache-tomcat-7.0.56/
BACKEND_ROOT_PATH=/home/damian/Documentos/Repos/DesarrolloDeAplicaciones-GrupoB/desapp-grupob-backend/


# stop tomcat
cd $TOMCAT_PATH
cd bin
./catalina.sh stop
cd ../webapps

# remove old files
rm -f -r backend_api/
rm -f backend_api.war

# make the .war and copy in tomcat
cd $BACKEND_ROOT_PATH
mvn clean install
cd target
mv desapp-grupob-backend-1.0-SNAPSHOT.war backend_api.war
cp backend_api.war $TOMCAT_PATH/webapps

# start tomcat
cd $TOMCAT_PATH
cd bin
./startup.sh

echo "### DEPLOY FINISHED ###"
