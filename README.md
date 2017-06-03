# General

## Prerequisites

* Docker installed
* Docker Compose installed
* HTTPie installed (used to register the SpringBoot services used in step 8)

### Build the required Spring Boot Docker image

This image is used in `./steps/7-adding-application-metrics`

* Execute `./build-docker-images`

### Integration with Slack

* Please add your own Slack API webhook in `./steps/6-alerting/alert-manager.yml`

## Step 1: Running Prometheus native

* Explain that Prometheus is a Go application which can just be extracted and run
* Start Prometheus via commandline ./prometheus and show that is running
* Prometheus
  * Navigate to <http://localhost:9090/metrics]> > show the metrics that Prometheus itself is generating
  * Navigate to <http://localhost:9090> > show prometheus webinterface
  * Show Config file (prometheus.yml) -> show Targets
  * Explain that scraping can be done by various ways (Consul, DNS, or dropping in new config file)

## Step 2: Run Prometheus using Docker

* Start Prometheus using docker-compose > dc up
* Show docker-compose.yml > including portmapping

## Step 3: Add host metrics

* Start Prometheus using docker-compose > dc up
* Node exporter
  * Explain Node Exporter
  * Show Node Exporter config inside prometheus.yml > explain scraping
  * Navigate to <http://localhost:9100/metrics>
  * Explain metrics
* Prometheus
  * Navigate to http://localhost:9090
  * Show to to query for metrics using the autocomplete
  * Show how to play using the query language (console & graph)
    * `node_network_receive_bytes`
    * `node_network_receive_bytes{device="eth0"}`
    * `node_network_receive_bytes{device="eth0"}[5m]`
    * `rate(node_network_receive_bytes{device="eth0"}[5m])`

## Step 4: Grafana

* Start Prometheus using docker-compose > dc up
* Grafana
  * Explain Grafana
  * Navigate to http://localhost:3000
  * Login using > admin/admin
  * Add Datasource
  * Create new Dashboard with widget containing graph from memory
    * `node_network_receive_bytes`
    * `node_network_receive_bytes{device="eth0"}`
  * Import dashboard to show the graphs coming from default monitoring of Prometheus
  * Import single server dashboard

## Step 5: Monitor Docker Containers

* Start Prometheus using docker-compose > dc up
* cAdvisor
  * Explain cAdvisor
  * Navigate to http://localhost:8090
  * Show gauges and meters
  * Navigate to http://localhost:8090/metrics
  * Show metrics
* Grafana
  * Navigate to http://localhost:3000
  * Login using > admin/admin
  * Add Datasource
  * Import cAdvisor Dashboard

## Step 6: Alerting

* Start Prometheus using docker-compose > dc up
* Show alert.rules
* Show alert-manager.yml
* Alert Manager
  * Explain the Alert Manager
  * Show alerts http://localhost:9093
* Prometheus
  * Show alerts in http://localhost:9090
* Introduce the Ping container
  * Stop/Start the Ping container -> `docker-compose stop ping`
* View the results in:
  * Slack 
  * Mailslurper: http://localhost:9000

## Step 7: Instrumenting applications

* Start Prometheus using docker-compose > dc up
* SpringBoot App
  * Navigate to http://localhost:8081/metrics
    * Explain that these are the wrong metrics (Spring Boot Actuator)
  * Navigate to http://localhost:8081/prometheus
    * Show the ‘helloworld_requests_total’ counter being 0
  * Navigate to http://localhost:8081/helloworld
    * This increases the counter with 1
  * Navigate to http://localhost:8081/prometheus
    * Show the ‘helloworld_requests_total’ counter being incremented
* Prometheus
  * Show ‘prometheus.yml’ > explain custom metrics path
  * Navigate to http://localhost:9090
  * Show the metric  ‘helloworld_requests_total’ in the web ui


## Step 8: Consul demo

* Start Prometheus using docker-compose > dc up
* Prometheus
  * Show prometheus.yml > explain consul config
  * Navigate to  http://localhost:9090
  * Show targets and see that we are missing the services
* Consul
  * Navigate to http://localhost:8500
  * Show missing services
  * Register the services using ‘register-services-with-consul.sh`
  * Show the services appearing in Consul
* Prometheus
  * Navigate to  http://localhost:9090
  * Show targets and see that the services have appeared

---

# Demo Links

* [Prometheus webinterface][prometheus-ui]
* [Prometheus metrics][prometheus-metrics]
* [Node Exporter][node-exporter]
* [Node Exporter metrics][node-exporter-metrics]
* [AlertManager][alertmanager]
* [cAdvisor webinterface][cadvisor-ui]
* [cAdvisor metrics][cadvisor-metrics]
* [Grafana][grafana]
* [Mailslurper][mailslurper]
* [SpringBoot Demo App * metrics][springboot metrics]
* [SpringBoot Demo App * helloworld][springboot helloworld]

[prometheus-ui]: http://localhost:9090
[prometheus-metrics]: http://localhost:9090/metrics
[node-exporter]: http://localhost:9100
[node-exporter-metrics]: http://localhost:9100/metrics
[alertmanager]: http://localhost:9093
[cadvisor-ui]: http://localhost:8090
[cadvisor-metrics]: http://localhost:8090/metrics
[grafana]: http://localhost:3000
[alert-manager]: http://localhost:9093
[mailslurper]: http://localhost:8085
[springboot metrics]: http://localhost:8080/metrics
[springboot helloworld]: http://localhost:8080/helloworld