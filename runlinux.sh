#!/bin/bash

# export DYLD_LIBRARY_PATH=./native
export LD_LIBRARY_PATH=./native
java -jar ./target/swell-0.1-standalone.jar
