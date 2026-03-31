#!/bin/bash

url='http://localhost:8080/api/v1/libros'

curl -v $url --json @rutina_1.json && echo
curl -v $url --json @rutina_2.json && echo
curl -v $url --json @rutina_3.json && echo
curl -v $url --json @rutina_4.json && echo
curl -v $url --json @rutina_5.json && echo
