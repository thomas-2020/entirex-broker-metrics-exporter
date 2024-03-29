# EntireX Broker Metrics Exporter

Server to provide/export Prometheuse metrics from EntireX Broker.

## Instroduction

To provide and export Prometheuse metrics from EntireX Broker, a HTTP Server (Tomcat) process is needed. The Java server process connects via ACI to EntireX Broker, retrieves the Broker metrics and provides a REST interface on `/metrics` for Prometheus protocol.

## Build Server

Build the Java server with Maven `mvn install`.

## Application Properties

The server requires properties and these are reading from `application.properties`. If you want to add or overwrite Tomcat default settings, add here the properties. The server process needs additional properties:

* `refreshInterval`: Interval of seconds to retrieves metrics from EntireX Broker
* `brokerID`: Connect to this EntireX Broker
* `user`: Use the username to login to EntireX Broker
* `excludeServerClass`: Exclude metrics of services which has the server class name
* `formatServiceName`: Format of service name l(ong)=CLASS/SERVICE/SERVER s(hort)=SERVICE

## Using Environment Variable

The Springboot Framework can pass environment variable to application properties. This feature is used for Broker ID. The `application.property` file contains ...

```
brokerID=${BROKER}
```
and therefore set the environment variable `BROKER` with value.
  
## Start Server

Use `java -jar target/entirex-broker-metrics-exporter-0.0.1-SNAPSHOT.jar`.

## Build Container

To build a container for EntireX Broker Exporter, you can use the `Dockerfile`.

```
docker build -t entirex-broker-exporter:latest .
```

## Run Container

To run the build, start following command to export metrics from `myBroker` ...

```
docker run -it -e BROKER=myBroker -p 8080:8080 entirex-broker-exporter:latest
```

Prometheus can poll now the metrics from `localhost:8080/metrics`.

## Deploy Container to Kubernetes using Helm Chart

To deploy the container to Kubernetes, it is possible to use the Helm Chart (in sub-folder `helm`).

```
helm upgrade --install my-exporter helm -n my-namespace --set image.repository=pswminnocontainerref.azurecr.io/exx/entirex-broker-exporter --set broker=myBroker
```
