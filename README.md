# EntireX Broker and Application Monitoring Data Collector Metrics Exporter

Server to provide/export Prometheuse metrics from EntireX Broker **and** Application Monitoring Data Collector.

## Introduction

This exporter is bases on the EntireX Broker Exporter which is provided in the `main` branch and additionly the exporter acts as an Application Monitoring Data Collector with an implemented callback. This callback is used to scrap the metrics and then provides them for Prometheus.

With the build-in HTTP Server the Prometheus metrics for both are available at `/metrics`

## Prerequisites Installed Libs

Install JARs into Maven repository ...

```
mvn install:install-file -Dfile=libs/exx107/entirex.jar -DgroupId=com.softwareag.entirex -DartifactId=java-client -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=libs/exx107/exxutil.jar -DgroupId=com.softwareag.entirex -DartifactId=java-util -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=libs/exx107/appmondc.jar -DgroupId=com.softwareag.entirex -DartifactId=java-appmondc -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
```

## Build Server

Build the Java server with Maven `mvn install`.

## Application Properties

The server requires properties and these are reading from `application.properties`. If you want to add or overwrite Tomcat default settings, add here the properties. The server process needs additional properties:

* `refreshInterval`: Interval of seconds to retrieves metrics from EntireX Broker
* `brokerID`: Connect to this EntireX Broker
* `user`: Use the username to login to EntireX Broker
* `excludeServerClass`: Exclude metrics of services which has the server class name
* `formatServiceName`: Format of service name l(ong)=CLASS/SERVICE/SERVER s(hort)=SERVICE
* `customLabelName4Services`: Set a custom label for services. Let it blank (emtpy string) if you don't need custom labels.
* `mapServiceToLabelValueList`: Map service to label value of custom label, e.g. `service1=labelvalue1;service2=labelvalue2`

## Extra or Custom Label for EntireX Broker Services

To align EntireX Broker services to other metrics in a dashboard, you can set own extra or *custom* label on EntireX Broker services. E.g., if your EntireX Broker service has the name `RPCMAT` and in Integration Server exists a package `Material` with an adapter services, set or add the value `RPCMAT=Material` in `mapServiceToLabelValueList`. Than, all metrics of `RPCMAT` will have the label `package=Material`. Now, you can create a `Material` dashboard panel with Entirex Broker and Integration Server metrics.

**Tip** in order to do this, you should install the [WxPrometheus](https://github.com/IBM/WxPrometheus) and family IS packages.

## Using Environment Variable default Application Properties

The Springboot Framework can pass environment variable to application properties. The default [application.property](/application.property) file contains ...

```
# Connect to Broker ...
brokerID=${BROKER}

# Use user ID ...
user=data-collector

# Poll metrics every ms ...
refreshInterval=15000

# Exclude metrics of services which has the server class name ...
excludeServerClass=SAG

# Format of service name l(ong)=CLASS/SERVICE/SERVER s(hort)=SERVICE ...
formatServiceName=s

# Add this label to services. Let it blank (emtpy string) if you don't need custom labels.
customLabelName4Services=${customLabelName4Services}

# Map service to label value of custom label, e.g. "service1=labelvalue1;service2=labelvalue2"
mapServiceToLabelValueList=${mapServiceToLabelValueList}
```

... and therefore, set the environment variable `BROKER`, `customLabelName4Services`, `mapServiceToLabelValueList` with a value.
  
## Start Server

Set the environment variables mentioned before and start ... 

```
java -jar target/entirex-broker-metrics-exporter-0.0.1-SNAPSHOT.jar
```

**Note**, the `application.properties` is reading from the current folder.

## Build Container

To build a container for EntireX Broker Exporter, you can use the [Dockerfile](./Dockerfile).

```
docker build -t entirex-broker-exporter:latest .
```

**Note for security**: Change [Dockerfile](./Dockerfile) to run the Tomcat Server inside container as non-root.

## Run Container

To run the build, start following command to export metrics from `myBroker:1971` ...

```
docker run -it --rm -e BROKER=myBroker:1971 -e customLabelName4Services= -e mapServiceToLabelValueList= -p 8080:8080 entirex-broker-exporter:latest
```

The Prometheus server can poll now the metrics from `localhost:8080/metrics`.

## Deploy Container to Kubernetes using Helm Chart

To deploy the container to Kubernetes, it is possible to use the Helm Chart (in sub-folder `helm`).

```
helm upgrade --install my-exporter helm -n my-namespace --set image.repository=entirex-broker-exporter --set broker=myBroker:1971
```

## Add Helm Chart Repository

To add this GitHub repo to your Helm Chart repositories, use ...

```
helm repo add entirex https://raw.githubusercontent.com/thomas-2020/entirex-broker-metrics-exporter/gh-pages/chart
```

Check the content with ...

```
helm search repo entirex
```

## Run as Linux Service Daemon

To run the exporter as Linux service daemon, you can configure following script ...

```
[Unit]
Description=EntireX Broker Exporter Service
Wants=network-online.target
After=network-online.target

[Service]
Restart=always
ExecStart=<path-to-java>/java -jar <path-to-exporter>/target/entirex-broker-metrics-exporter-0.0.1-SNAPSHOT.jar --spring.config.location=file:///<path-to-exporter>/application.properties
ExecStop=/bin/kill $MAINPID

[Install]
WantedBy=default.target
```
... which you can use as a template. Copy the template to a file on your Linux host as filename `entirex-broker-metrics-exporter.service` in the folder of your choice (e.g. home of Eporter ZIP file). Replace the place holders in property `ExecStart` ...

* `<path-to-java>` and
* `<path-to-exporter>`

... by **absolute** paths to Java and the Exporter installation folder. After setting up the property `ExecStart`, please test the command on you command line. The Exporter should start online. Please verify that the property file is loaded.

After testing of `ExecStart`, you can setup the service daemon. We want to configure the service daemon with and as your user (and not as root). To *detach* the service deamon from your user, you can use the system ultility [loginctl](https://www.freedesktop.org/software/systemd/man/latest/loginctl.html#enable-linger%20USER%E2%80%A6) ...

```
loginctl enable-linger <your-userid>
```

If you cannot use `loginctl`, you need `sudo` rights. In this case, you must perform all commands with `sudo` and remove the `--user` option ...

```
# Create and enable service script ...
systemctl --user enable <path-to-exporter.service-file>/entirex-broker-metrics-exporter.service
# Reload script into daemon engine ...
systemctl --user daemon-reload
# Check status (if script is loaded) ...
systemctl --user status entirex-broker-metrics-exporter.service
# Start Exporter ...
systemctl --user start entirex-broker-metrics-exporter.service
# Check status (if Exporter is green and running) ...
systemctl --user status entirex-broker-metrics-exporter.service
```

## Metrics

Here are the metrics documented which are available at `/metrics`.

### Broker

The Broker mtrics are starting with `sag_etb` and have the label `broker`. A service metrics have the label `service`.

```
# HELP sag_etb_node_servers_active Number of active Broker Servers
# TYPE sag_etb_node_servers_active gauge
# HELP sag_etb_uows_max Max number of UOWs
# TYPE sag_etb_uows_max gauge
# HELP sag_etb_node_total_storage_allocated Size of allocated storage in bytes
# TYPE sag_etb_node_total_storage_allocated gauge
# HELP sag_etb_node_conversations_size Size of Broker Conversations
# TYPE sag_etb_node_conversations_size gauge
# HELP sag_etb_node_com_buffers_allocated Number of buffers allocated
# TYPE sag_etb_node_com_buffers_allocated gauge
# HELP sag_etb_node_stats_up Connection status to Broker
# TYPE sag_etb_node_stats_up gauge
# HELP sag_etb_node_long_buffers_high Number of highest active Broker Long Buffers
# TYPE sag_etb_node_long_buffers_high gauge
# HELP sag_etb_node_connection_entries_free Number of connection entries free
# TYPE sag_etb_node_connection_entries_free gauge
# HELP sag_etb_service_requests Current number of service requests
# TYPE sag_etb_service_requests gauge
# HELP sag_etb_conv_pending_high Conversation pending high
# TYPE sag_etb_conv_pending_high gauge
# HELP sag_etb_node_long_buffers_size Size of Broker Long Buffers
# TYPE sag_etb_node_long_buffers_size gauge
# HELP sag_etb_conv_pending Conversation pending
# TYPE sag_etb_conv_pending gauge
# HELP sag_etb_node_total_storage_allocated_high Highest size of allocated storage in bytes since Broker started
# TYPE sag_etb_node_total_storage_allocated_high gauge
# TYPE sag_etb_node_workers_active gauge
# HELP sag_etb_node_services_size Size of Broker Services
# TYPE sag_etb_node_services_size gauge
# HELP sag_etb_node_short_buffers_active Number of active Broker Short Buffers
# TYPE sag_etb_node_short_buffers_active gauge
# HELP sag_etb_node_servers_size Size of Broker Servers
# TYPE sag_etb_node_servers_size gauge
# HELP sag_etb_node_heap_bytes_free Number of Heap bytes free
# TYPE sag_etb_node_heap_bytes_free gauge
# HELP sag_etb_node_short_buffers_high Number of highest active Broker Short Buffers
# TYPE sag_etb_node_short_buffers_high gauge
# HELP sag_etb_node_connection_entries_used Number of connection entries used
# TYPE sag_etb_node_connection_entries_used gauge
# HELP sag_etb_node_short_buffers_size Size of Broker Short Buffers
# TYPE sag_etb_node_short_buffers_size gauge
# HELP sag_etb_occupied_servers Number of occupied servers
# TYPE sag_etb_occupied_servers gauge
# HELP sag_etb_conv_high Conversation high
# TYPE sag_etb_conv_high gauge
# HELP sag_etb_node_heap_bytes_used Number of Heap bytes used
# TYPE sag_etb_node_heap_bytes_used gauge
# HELP sag_etb_node_worker_idle_time Sum of idle time per worker since Broker started
# TYPE sag_etb_node_worker_idle_time gauge
# HELP sag_etb_node_servers_high Number of highest Broker Servers
# TYPE sag_etb_node_servers_high gauge
# HELP sag_etb_uows_active Number of active UOWs
# TYPE sag_etb_uows_active gauge
# HELP sag_etb_node_services_active Number of active Broker Services
# TYPE sag_etb_node_services_active gauge
# HELP sag_etb_node_heap_bytes_allocated Number of Heap bytes allocated
# TYPE sag_etb_node_heap_bytes_allocated gauge
# HELP sag_etb_node_work_queue_entries_allocated Number of work queue entries allocated
# TYPE sag_etb_node_work_queue_entries_allocated gauge
# HELP sag_etb_node_long_buffers_active Number of active Broker Long Buffers
# TYPE sag_etb_node_long_buffers_active gauge
# HELP sag_etb_conv_active Conversation active
# TYPE sag_etb_conv_active gauge
# HELP sag_etb_node_total_storage_limit Maximum of storage that can be allocated
# TYPE sag_etb_node_total_storage_limit gauge
# HELP sag_etb_active_servers Current number of servers
# TYPE sag_etb_active_servers gauge
# HELP sag_etb_node_worker_calls Sum of calls per worker since Broker started
# TYPE sag_etb_node_worker_calls gauge
# HELP sag_etb_node_com_buffers_free Number of buffers free
# TYPE sag_etb_node_com_buffers_free gauge
# HELP sag_etb_node_conversations_high Number of highest Broker Conversations
# TYPE sag_etb_node_conversations_high gauge
# HELP sag_etb_node_work_queue_entries_free Number of work queue entries free
# TYPE sag_etb_node_work_queue_entries_free gauge
# HELP sag_etb_node_com_buffers_used Number of buffers used
# TYPE sag_etb_node_com_buffers_used gauge
# HELP sag_etb_node_worker_status Status of worker
# TYPE sag_etb_node_worker_status gauge
# HELP sag_etb_waits_of_servers Number of waits of servers
# TYPE sag_etb_waits_of_servers gauge
# HELP sag_etb_node_connection_entries_allocated Number of connection entries allocated
# TYPE sag_etb_node_connection_entries_allocated gauge
# HELP sag_etb_node_work_queue_entries_used Number of work queue entries used
# TYPE sag_etb_node_work_queue_entries_used gauge
```

### RPC from Application Monitoring DC

All RPC metrics are starting with `sag_rpc`. Each metrics has the labels `broker`, `service` and `program`. The *Application Name* is mapped to the label `service`. This is done because in alignment to the Broker metrics.

```
# HELP sag_rpc_time_broker_wait_for_server The time spent in the broker waiting for an available server in microseconds
# TYPE sag_rpc_time_broker_wait_for_server gauge
# HELP sag_rpc_time_server_transport The transport time from the broker to the server and back in microseconds
# TYPE sag_rpc_time_server_transport gauge
# HELP sag_rpc_time_client_transport The transport time from the client to the broker and back in microseconds
# TYPE sag_rpc_time_client_transport gauge
# HELP sag_rpc_success_calls_total Count RPC success calls
# TYPE sag_rpc_success_calls_total counter
# HELP sag_rpc_time_response The complete response time (roundtrip from client to server and back) in microseconds
# TYPE sag_rpc_time_response gauge
# HELP sag_rpc_time_client_layer The time spent in the client RPC layer in microseconds
# TYPE sag_rpc_time_client_layer gauge
# HELP sag_rpc_time_db_calls The time spent for database calls  microseconds
# TYPE sag_rpc_time_db_calls gauge
# HELP sag_rpc_time_broker The time spent in the broker (active processing) in microseconds
# TYPE sag_rpc_time_broker gauge
# HELP sag_rpc_time_server_program The time spent in the user program in microseconds
# TYPE sag_rpc_time_server_program gauge
# HELP sag_rpc_time_db_transport The transport time from the Natural user program to the Adabas router and back including the client receiving time in microseconds
# TYPE sag_rpc_time_db_transport gauge
# HELP sag_rpc_error_calls_total Count RPC error calls
# TYPE sag_rpc_error_calls_total counter
# HELP sag_rpc_calls_total Count RPC calls
# TYPE sag_rpc_calls_total counter
# HELP sag_rpc_time_server_layer The time spent in the server RPC layer (runtime and stub) in microseconds
# TYPE sag_rpc_time_server_layer gauge
# HELP sag_rpc_calls_created Count RPC calls
# TYPE sag_rpc_calls_created gauge
# HELP sag_rpc_success_calls_created Count RPC success calls
# TYPE sag_rpc_success_calls_created gauge
```


## Actions to Package Helm Chart Repository

```
helm package -u ./helm
mkdir -p ./chart
mv entirex-broker-exporter-1.0.0.tgz ./chart
cd chart
helm repo index . --url https://raw.githubusercontent.com/thomas-2020/entirex-broker-metrics-exporter/gh-pages/chart
# Commit to gh-pages
git add .
git commit -am "<commit-message>"
git branch -M      gh-pages
git push -f origin gh-pages
```
