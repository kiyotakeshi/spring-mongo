#!/bin/bash

mongoimport --db "spring-mongo" --collection customer --drop \
--file /docker-entrypoint-initdb.d/01_import_init_data.json \
--jsonArray
