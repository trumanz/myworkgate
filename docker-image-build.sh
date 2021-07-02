#!/usr/bin/env bash

set -xeuo pipefail

cd survey-project  
mvn clean package 
docker build -t myworkgate:latest ./
cd ..



