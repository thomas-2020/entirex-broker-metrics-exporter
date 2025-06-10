FROM maven AS build

# Generate configuration settings file for Maven.
#  Here is a sample to configure Maven behind HTTP Proxy ...   
#RUN mkdir -p                                        /root/.m2              \
# && echo "<settings>"                              >/root/.m2/settings.xml \
# && echo " <proxies>"                             >>/root/.m2/settings.xml \
# && echo "  <proxy>"                              >>/root/.m2/settings.xml \
# && echo "  <active>true</active>"                >>/root/.m2/settings.xml \
# && echo "   <protocol>https</protocol>"          >>/root/.m2/settings.xml \
# && echo "   <host>194.175.160.14</host>"         >>/root/.m2/settings.xml \
# && echo "   <port>8080</port>"                   >>/root/.m2/settings.xml \
# && echo "   <nonProxyHosts></nonProxyHosts>"     >>/root/.m2/settings.xml \
# && echo "  </proxy>"                             >>/root/.m2/settings.xml \
# && echo " </proxies>"                            >>/root/.m2/settings.xml \
# && echo "</settings>"                            >>/root/.m2/settings.xml


RUN git clone -b appmondc https://github.com/thomas-2020/entirex-broker-metrics-exporter.git


RUN mvn install:install-file -Dfile=./entirex-broker-metrics-exporter/libs/exx107/entirex.jar  -DgroupId=com.softwareag.entirex -DartifactId=java-client   -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
RUN mvn install:install-file -Dfile=./entirex-broker-metrics-exporter/libs/exx107/exxutil.jar  -DgroupId=com.softwareag.entirex -DartifactId=java-util     -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
RUN mvn install:install-file -Dfile=./entirex-broker-metrics-exporter/libs/exx107/appmondc.jar -DgroupId=com.softwareag.entirex -DartifactId=java-appmondc -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true

RUN cd entirex-broker-metrics-exporter ; mvn install

# ... build is finished
# ---------------------
# start runtime ...
 
FROM ubuntu:latest

RUN apt-get update \ 
    && apt-get install openjdk-21-jre -y

COPY --from=build /entirex-broker-metrics-exporter/application.properties /
COPY --from=build /entirex-broker-metrics-exporter/entirex.server.properties /
COPY --from=build /entirex-broker-metrics-exporter/target/entirex-broker-metrics-exporter-0.0.1-SNAPSHOT.jar /

# ToDo: Set non-root user for running ...
# USER sagadmin

ENTRYPOINT ["java", "-jar", "/entirex-broker-metrics-exporter-0.0.1-SNAPSHOT.jar"]