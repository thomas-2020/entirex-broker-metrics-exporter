# EntireX Broker Metrics Exporter

Server to provide/export Prometheuse metrics from EntireX Broker.

## Introduction

To provide and export Prometheuse metrics from EntireX Broker, a HTTP Server (Tomcat) process is needed. The Java server process connects via ACI to EntireX Broker, retrieves the Broker metrics and provides a REST interface on `/metrics` for Prometheus protocol.

## Prerequisites Installed Libs

Install JARs into Maven repository ...

```
mvn install:install-file -Dfile=libs/exx107/entirex.jar -DgroupId=com.softwareag.entirex -DartifactId=java-client -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=libs/exx107/exxutil.jar -DgroupId=com.softwareag.entirex -DartifactId=java-util -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
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
* `customLabelName4Services`: Set a custom label for services. Default is `package`.
* `mapServiceToLabelValueList`: Map service to label value of custom label, e.g. "service1=labelvalue1;service2=labelvalue2"

## Extra or Custom Label for Services

To align services to other metrics, you can set own extra or custom label on services. The default label name is `package` defined in `application.properties`. E.g. if your service has the name `RPCMAT` and in Integration Server is a package `Material` with an adapter services which use the EntireX service, set the values `RPCMAT=Material` in `mapServiceToLabelValueList`. Than all metrics of `RPCMAT` will have the label `package=Material`.

## Using Environment Variable

The Springboot Framework can pass environment variable to application properties. This feature is used for Broker ID. The `application.property` file contains ...

```
brokerID=${BROKER}
```
and therefore set the environment variable `BROKER` with value.
  
## Start Server

Use `java -jar target/entirex-broker-metrics-exporter-0.0.1-SNAPSHOT.jar`

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

## Add Helm Chart Repository

To add this GitHub repo to your Helm Chart repositories, use ...

```
helm repo add entirex https://raw.githubusercontent.com/thomas-2020/entirex-broker-metrics-exporter/gh-pages/chart
```

Check the content with ...

```
helm search repo entirex
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
