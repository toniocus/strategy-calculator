#!/bin/bash

max=${1:-"10"}
counter=0;

echo "Building executable jar...."
mvn package
if [ $? -ne 0 ]
then
     echo "FALLO EL BUILD"
     exit 1
fi

while true 
do 
   echo
   echo
   echo
   echo "========================="
   echo "***** $((++counter)) ****" 
   echo "========================="

   echo "Starting docker..."
   dockerId=$(docker run -di -p 9080:8080 openjdk:8-jre-alpine)

   # Kill running docker when ended
   trap "docker kill $dockerId" EXIT

   // copy to container de application
   docker cp target/strategy-calculator-0.1.jar $dockerId:/app.jar

   // start de application
   docker exec -d -it $dockerId java -jar app.jar

   echo "Waiting 4\" so container starts up..."
   sleep 4 

   echo "Starting tests..."
   mvn -Dtest=DockerClient test 
   if [ $? -ne 0 ]
   then
      echo "***** FALLLLLLLO **********"
      exit 1
   fi


   if [ $counter -ge $max ]
   then
      echo "******* STOPPING **********"
      exit 0;
   fi
   
   echo "Stopping docker..."
   docker kill $dockerId
done
