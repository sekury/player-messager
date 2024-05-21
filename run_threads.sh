#!/bin/sh

chmod +x mvnw
./mvnw compile
./mvnw exec:java -Dexec.mainClass="fx360t.Main" -Dexec.args="threads $1"