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
