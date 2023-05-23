#!/bin/bash

folders=("productcatalog" "user" "notification")

build=false
docker=false

for arg in "$@"
do
    if [ "$arg" == "-build" ]; then
        build=true
    elif [ "$arg" == "-docker" ]; then
        docker=true
    fi
done

if $docker && ! $build; then
    docker-compose up -d # ejecuta docker-compose up
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
  pushd $folder

  if $build; then
    mvn clean package
  else
    pushd target

    jar_file=$(ls $folder-*.jar)
    java -jar $jar_file &

    popd
  fi
  
  popd

done

# espera hasta que se reciba una señal INT o EXIT
wait
