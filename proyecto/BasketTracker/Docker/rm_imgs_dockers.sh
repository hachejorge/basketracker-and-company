#!/bin/bash

# Paso 5: Detener los contenedores
echo "Deteniendo los contenedores..."

sudo docker stop sisinf-database
sudo docker stop sisinf-tomcat

# Paso 6: Eliminar los contenedores
echo "Eliminando los contenedores..."

sudo docker rm sisinf-database
sudo docker rm sisinf-tomcat

# Paso 1: Eliminar las imágenes existentes
echo "Eliminando las imágenes existentes de Docker..."

sudo docker rmi sisinf/postgresql:latest
sudo docker rmi sisinf/tomcat:latest

