#!/bin/bash

# Contenedores que deseas eliminar
CONTAINERS=("nginx" "tomcat" "postgres")

# Función para eliminar contenedores
remove_containers() {
    for container in "${CONTAINERS[@]}"; do
        echo "Eliminando contenedor: $container"
        sudo docker rm -f $container || echo "No se pudo eliminar el contenedor $container o ya no existe."
    done
}

# Función para hacer build de los servicios
docker_build() {
    echo "Realizando docker-compose build..."
    sudo docker-compose build
}

# Función para levantar los servicios
docker_up() {
    echo "Levantando los contenedores con docker-compose up..."
    sudo docker-compose up -d
}

# Ejecución del script
remove_containers
docker_build
docker_up

sudo docker-compose ps -a

echo "Proceso completado."

