package com.softwareag.entirex.metrics;

import io.prometheus.client.Gauge;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.softwareag.entirex.aci.Broker;
import com.softwareag.entirex.cis.IServiceResponse;
import com.softwareag.entirex.cis.InfoServiceMessage;
import com.softwareag.entirex.cis.ServiceRequest;
import com.softwareag.entirex.cis.objects.ServiceObject;
import com.softwareag.entirex.cis.params.BlockLength;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;

@Service
@EnableScheduling
public class BrokerDataCollector {

	private static final Logger logger = LoggerFactory.getLogger( BrokerDataCollector.class );

	private Gauge nServiceRequests;
	private Gauge nServiceServer;

	@PostConstruct
	private void init() {
		try {
			String labelPrefix = "sag_etb_";
			nServiceRequests = Gauge.build().name( labelPrefix + "service_requests" ).help( "Current number of service requests" ) .labelNames( "BrokerID", "Service" ).register();
			nServiceServer   = Gauge.build().name( labelPrefix + "active_servers"   ).help( "Current number of servers"          ) .labelNames( "BrokerID", "Service" ).register();
		}
		catch ( Throwable e ) {
			logger.error( "On DataCollector init: ", e );
		}
	}

	@Value( "${brokerID}" )
	private String brokerID;

	@Value( "${user}" )
	private String user;

	private Broker broker;

	@Scheduled(fixedRateString = "${refreshInterval}", initialDelayString = "${refreshInterval}")
	private void doPull() {
		try {
			if ( broker == null ) {
				broker = new Broker( brokerID, user );
			broker.logon();
			pullMetrics_Services( broker );
			}
		}
		catch ( Throwable e ) {
			logger.error( "on doPoll(): " + e.getLocalizedMessage() );
		}
	}

	private void pullMetrics_Services( Broker broker ) throws Throwable {
		InfoServiceMessage info = new InfoServiceMessage();
		info.setInterfaceVersion( InterfaceVersion.VERSION_2 );
		info.setBlockLength     ( new BlockLength( 7200 ) );
		info.setObjectType      ( ObjectType.SERVICE );
		ServiceRequest      req = new ServiceRequest( broker, info );
		IServiceResponse    res = req.sendReceive();
		for ( int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++ ) {
			ServiceObject so = (ServiceObject) res.getServiceResponseObject( i );
			nServiceRequests.labels( broker.getBrokerID(), printServiceName( so.getServerClass(), so.getService(), so.getServerName() ) ).set( so.getRequests() );
			nServiceServer.labels  ( broker.getBrokerID(), printServiceName( so.getServerClass(), so.getService(), so.getServerName() ) ).set( so.getServerAct() );
		}
	}

	private String printServiceName( String className, String serviceName, String serverName ) {
		return serviceName;
	}
}
