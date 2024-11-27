FROM bitnami/postgresql:15.4.0
LABEL maintainer="872838"

# Usamos root para realizar la configuración
USER root

# Actualizar e instalar locales, generarlos y configurarlos correctamente
RUN apt-get update && \
    apt-get install -y locales apt-utils && \
    echo "es_ES.UTF-8 UTF-8" > /etc/locale.gen && \
    locale-gen && \
    update-locale LANG=es_ES.UTF-8 LC_ALL=es_ES.UTF-8 LANGUAGE=es_ES.UTF-8 && \
    apt-get clean

# Establecer las variables de entorno de locales
ENV LANG=es_ES.UTF-8
ENV LANGUAGE=es_ES:es
ENV LC_ALL=es_ES.UTF-8

# Copiar los datos 
ADD --chown=1001:1001 main /bitnami/postgresql/data