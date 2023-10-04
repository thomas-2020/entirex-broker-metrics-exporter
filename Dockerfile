FROM maven

RUN git clone https://github.com/thomas-2020/entirex-broker-metrics-exporter.git


RUN mvn install:install-file -Dfile=./entirex-broker-metrics-exporter/libs/exx107/entirex.jar -DgroupId=com.softwareag.entirex -DartifactId=java-client -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true
RUN mvn install:install-file -Dfile=./entirex-broker-metrics-exporter/libs/exx107/exxutil.jar -DgroupId=com.softwareag.entirex -DartifactId=java-util -Dversion=10.7 -Dpackaging=jar -DgeneratePom=true

RUN cd entirex-broker-metrics-exporter ; mvn install

RUN mv /entirex-broker-metrics-exporter/application.properties /

ENTRYPOINT ["java", "-jar", "/entirex-broker-metrics-exporter/target/entirex-broker-metrics-exporter-0.0.1-SNAPSHOT.jar"]