#!/bin/bash

# lista de carpetas
folders=("productcatalog" "user" "notification")

# inicializa las variables para verificar si se pasaron los argumentos '-build' o '-docker'
build=false
docker=false

# itera sobre todos los argumentos
for arg in "$@"
do
    if [ "$arg" == "-build" ]; then
        build=true
    elif [ "$arg" == "-docker" ]; then
        docker=true
    fi
done

if $docker && ! $build; then
    # ejecuta docker-compose up
    docker-compose up -d
fi

# define una función para matar todos los procesos de Java que se están ejecutando
function cleanup {
    echo "Caught Interrupt signal. Shutting down..."
    pkill -f 'java -jar'
    exit
}

# establece la función 'cleanup' para que se ejecute cuando se reciban las señales INT (Ctrl+C) y EXIT
trap cleanup INT EXIT

for folder in "${folders[@]}"
do

  # navega a la carpeta
  pushd $folder

  if $build; then
    # si se pasó el argumento '-build', ejecuta 'mvn clean package'
    mvn clean package
  else
    # navega al directorio 'target' dentro de la carpeta
    pushd target

    # encuentra y ejecuta el archivo .jar correspondiente
    jar_file=$(ls $folder-*.jar)
    java -jar $jar_file &

    # regresa al directorio original
    popd
    popd
  fi

done

# espera hasta que se reciba una señal INT o EXIT
wait
