#!/bin/bash

max=${1:="10"}
counter=0;

while true 
do 
   echo "***** $((++counter)) ****" 

   mvn test 
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
   
done
