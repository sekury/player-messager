#!/bin/sh

chmod +x mvnw
./mvnw exec:java -Dexec.mainClass="fx360t.Main" -Dexec.args="threads hi"