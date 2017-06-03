#!/bin/bash
set -e
exec java ${JAVA_OPTS} -jar application.jar $@