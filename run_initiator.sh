#!/bin/sh

chmod +x mvnw
./mvnw compile
./mvnw exec:java -Dexec.mainClass="fx360t.Main" -Dexec.args="soinit $1 $2"