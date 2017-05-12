#!/usr/bin/env bash
http POST http://localhost:8500/v1/agent/service/register < register-service1.json
http POST http://localhost:8500/v1/agent/service/register < register-service2.json