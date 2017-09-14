#!/usr/bin/env bash

rm log.txt
rm -rf build

gradle build
java -jar build/libs/labtester-worker-0.1.0.jar >> log.txt &