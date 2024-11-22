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

# Paso 2: Crear la imagen de Tomcat
echo "Creando la imagen de Tomcat..."
cd tomcat
sudo docker build -t sisinf/tomcat:latest .

# Paso 3: Crear la imagen de PostgreSQL
echo "Creando la imagen de PostgreSQL..."
cd ../postgres
sudo docker build -t sisinf/postgresql:latest .

# Paso 4: Crear los contenedores
echo "Creando los contenedores..."

# Contenedor de PostgreSQL
echo "Creando el contenedor de database"
sudo docker run --name sisinf-database -e ALLOW_EMPTY_PASSWORD=yes -p 5432:5432 -d sisinf/postgresql:latest

# Contenedor de Tomcat, enlazado a la base de datos
echo "Creando el contenedor de tomcat"
sudo docker run -d --name sisinf-tomcat --link sisinf-database -p 8080:8080 sisinf/tomcat:latest

# Imagenes activas
sudo docker images

#Containers activos
sudo docker ps -a
