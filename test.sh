#!/usr/bin/env bash

echo "hello"
echo $0
echo $1
echo $*
echo $#
echo $$

if [ $# != 1 ]; then
	echo "usage: $0 [commit id]"
	exit 1
fi