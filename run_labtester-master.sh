#!/usr/bin/env bash

rm log.txt
rm -rf build

gradle build
java  -Xmx200M -jar build/libs/labtester-master-0.1.0.jar >> log.txt &