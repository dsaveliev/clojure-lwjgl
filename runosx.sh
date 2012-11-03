#!/bin/bash

export DYLD_LIBRARY_PATH=./native
# export LD_LIBRARY_PATH=./native
java -jar ./game-1.0.0-SNAPSHOT-standalone.jar
