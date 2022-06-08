#!/usr/bin/env bash
mvn -U clean package -Dmaven.test.skip=true -T 4
