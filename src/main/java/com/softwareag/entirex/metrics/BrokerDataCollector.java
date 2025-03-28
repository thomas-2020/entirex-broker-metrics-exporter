package com.softwareag.entirex.metrics;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

import java.util.HashMap;
import java.util.StringTokenizer;

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
import com.softwareag.entirex.cis.objects.BrokerObject;
import com.softwareag.entirex.cis.objects.ResourceUsageObject;
import com.softwareag.entirex.cis.params.BlockLength;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;
import com.softwareag.entirex.cis.params.ConvID;

@Service
@EnableScheduling
public class BrokerDataCollector {

	private static final Logger logger = LoggerFactory.getLogger( BrokerDataCollector.class );

	private Gauge nBrokerStatus;
	private Gauge nBrokerWorkerActive;
	private Gauge nBrokerLongBuffersSize;
	private Gauge nBrokerLongBufferActive;
	private Gauge nBrokerLongBuffersHigh;
	private Gauge nBrokerShortBuffersSize;
	private Gauge nBrokerShortBuffersActive;
	private Gauge nBrokerShortBuffersHigh;
	private Gauge nBrokerServicesSize;
	private Gauge nBrokerServicesActive;
	private Gauge nBrokerServersSize;
	private Gauge nBrokerServersActive;
	private Gauge nBrokerServersHigh;
	private Gauge nBrokerConversationsSize;
	private Gauge nBrokerConversationsHigh;

	private Gauge nServiceRequests;
	private Gauge nServiceServer;
	private Gauge nServiceConvHigh;
	private Gauge nServiceConvPending;
	private Gauge nServiceConvPendingHigh;
	private Gauge nServiceConvActive;
	private Gauge nServiceWaitsOfServers;
	private Gauge nServiceOccupiedServers;
	private Gauge nServiceUOWsActive;
	private Gauge nServiceUOWsSize;

	@PostConstruct
	private void init() {
		logger.info( "Connect to Broker [" + brokerID + "]. Polling metrics in the interval [" + refreshInterval + "]ms." );
		try {
			String labelPrefix          = "sag_etb_"; //Prefix for all labels
			nBrokerStatus               = Gauge.build().name  ( labelPrefix + "node_stats_up"             ).help( "Connection status to Broker"                   ).labelNames( "broker" ).register();
			nBrokerWorkerActive         = Gauge.build().name  ( labelPrefix + "node_workers_active"       ).help( "Number of active Broker workers"               ).labelNames( "broker" ).register();
			nBrokerLongBuffersSize      = Gauge.build().name  ( labelPrefix + "node_long_buffers_size"    ).help( "Size of Broker Long Buffers"            ).labelNames( "broker" ).register();
			nBrokerLongBufferActive     = Gauge.build().name  ( labelPrefix + "node_long_buffers_active"  ).help( "Number of active Broker Long Buffers"          ).labelNames( "broker" ).register();
			nBrokerLongBuffersHigh      = Gauge.build().name  ( labelPrefix + "node_long_buffers_high"    ).help( "Number of highest active Broker Long Buffers"  ).labelNames( "broker" ).register();
			nBrokerShortBuffersSize     = Gauge.build().name  ( labelPrefix + "node_short_buffers_size"   ).help( "Size of Broker Short Buffers"           ).labelNames( "broker" ).register();
			nBrokerShortBuffersActive   = Gauge.build().name  ( labelPrefix + "node_short_buffers_active" ).help( "Number of active Broker Short Buffers"         ).labelNames( "broker" ).register();
			nBrokerShortBuffersHigh     = Gauge.build().name  ( labelPrefix + "node_short_buffers_high"   ).help( "Number of highest active Broker Short Buffers" ).labelNames( "broker" ).register();
			nBrokerServicesSize         = Gauge.build().name  ( labelPrefix + "node_services_size"        ).help( "Size of Broker Services"                ).labelNames( "broker" ).register();
			nBrokerServicesActive       = Gauge.build().name  ( labelPrefix + "node_services_active"      ).help( "Number of active Broker Services"              ).labelNames( "broker" ).register();
			nBrokerServersSize          = Gauge.build().name  ( labelPrefix + "node_servers_size"         ).help( "Size of Broker Servers"                 ).labelNames( "broker" ).register();
			nBrokerServersActive        = Gauge.build().name  ( labelPrefix + "node_servers_active"       ).help( "Number of active Broker Servers"               ).labelNames( "broker" ).register();
			nBrokerServersHigh          = Gauge.build().name  ( labelPrefix + "node_servers_high"         ).help( "Number of highest Broker Servers"              ).labelNames( "broker" ).register();
			nBrokerConversationsSize    = Gauge.build().name  ( labelPrefix + "node_conversations_size"   ).help( "Size of Broker Conversations"           ).labelNames( "broker" ).register();
			nBrokerConversationsHigh    = Gauge.build().name  ( labelPrefix + "node_conversations_high"   ).help( "Number of highest Broker Conversations"              ).labelNames( "broker" ).register();

			if ( isCustomLabelNameValid() ) {
				nServiceRequests        = Gauge.build().name  ( labelPrefix + "service_requests"  ).help( "Current number of service requests" ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceServer          = Gauge.build().name  ( labelPrefix + "active_servers"    ).help( "Current number of servers"          ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceConvHigh        = Gauge.build().name  ( labelPrefix + "conv_high"         ).help( "Conversation high"                  ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceConvPending     = Gauge.build().name  ( labelPrefix + "conv_pending"      ).help( "Conversation pending"               ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceConvPendingHigh = Gauge.build().name  ( labelPrefix + "conv_pending_high" ).help( "Conversation pending high"          ) .labelNames( "broker", "service", customLabelName4Services ).register();			
				nServiceConvActive      = Gauge.build().name  ( labelPrefix + "conv_active"       ).help( "Conversation active"                ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceWaitsOfServers  = Gauge.build().name  ( labelPrefix + "waits_of_servers"  ).help( "Number of waits of servers"         ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceOccupiedServers = Gauge.build().name  ( labelPrefix + "occupied_servers"  ).help( "Number of occupied servers"         ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceUOWsActive      = Gauge.build().name  ( labelPrefix + "uows_active"       ).help( "Number of active UOWs"              ) .labelNames( "broker", "service", customLabelName4Services ).register();
				nServiceUOWsSize        = Gauge.build().name  ( labelPrefix + "uows_size"         ).help( "Size of UOWs"                       ) .labelNames( "broker", "service", customLabelName4Services ).register();
			}
			else {
				nServiceRequests        = Gauge.build().name  ( labelPrefix + "service_requests"  ).help( "Current number of service requests" ) .labelNames( "broker", "service" ).register();
				nServiceServer          = Gauge.build().name  ( labelPrefix + "active_servers"    ).help( "Current number of servers"          ) .labelNames( "broker", "service" ).register();
				nServiceConvHigh        = Gauge.build().name  ( labelPrefix + "conv_high"         ).help( "Conversation high"                  ) .labelNames( "broker", "service" ).register();
				nServiceConvPending     = Gauge.build().name  ( labelPrefix + "conv_pending"      ).help( "Conversation pending"               ) .labelNames( "broker", "service" ).register();
				nServiceConvPendingHigh = Gauge.build().name  ( labelPrefix + "conv_pending_high" ).help( "Conversation pending high"          ) .labelNames( "broker", "service" ).register();			
				nServiceConvActive      = Gauge.build().name  ( labelPrefix + "conv_active"       ).help( "Conversation active"                ) .labelNames( "broker", "service" ).register();
				nServiceWaitsOfServers  = Gauge.build().name  ( labelPrefix + "waits_of_servers"  ).help( "Number of waits of servers"         ) .labelNames( "broker", "service" ).register();
				nServiceOccupiedServers = Gauge.build().name  ( labelPrefix + "occupied_servers"  ).help( "Number of occupied servers"         ) .labelNames( "broker", "service" ).register();				
				nServiceUOWsActive      = Gauge.build().name  ( labelPrefix + "uows_active"       ).help( "Number of active UOWs"              ) .labelNames( "broker", "service" ).register();
				nServiceUOWsSize        = Gauge.build().name  ( labelPrefix + "uows_max"          ).help( "Max number of UOWs"                 ) .labelNames( "broker", "service" ).register();
			}

			StringTokenizer      st = new StringTokenizer( mapServiceToLabelValueList, ";");
			while ( st.hasMoreElements() ) {
				String            s = st.nextToken();
				StringTokenizer st2 = new StringTokenizer( s, "=" );
				String            k = st2.hasMoreElements() ? st2.nextToken() : null;
				String            v = st2.hasMoreElements() ? st2.nextToken() : null;
				if ( k != null && v != null ) {
					mapServiceToLabelValue.put( k, v );
					logger.info( "Put into mapServiceToLabelValue [" + k + "=" + v + "]");
				}
			}
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

	@Value( "${refreshInterval}" )
	private String refreshInterval;

	@Value( "${excludeServerClass}" )
	private String excludeServerClass;

	@Value( "${formatServiceName}" )
	private String formatServiceName;

	@Value( "${customLabelName4Services}" )
	private String customLabelName4Services;

	@Value( "${mapServiceToLabelValueList}" )
	private String mapServiceToLabelValueList;
	private HashMap<String, String> mapServiceToLabelValue = new HashMap<String, String>();

	private static boolean enableResourceUsagePolling = true;

	@Scheduled(fixedRateString = "${refreshInterval}", initialDelayString = "${refreshInterval}")
	private void doPull() {
		try {
			if ( broker == null ) {
				broker = new Broker( brokerID, user );
				broker.logon();
			}
			pollMetrics_Services     ( broker );
			pollMetrics_Broker       ( broker );
			
			try {
				if ( enableResourceUsagePolling )
				pollMetrics_ResourceUsage( broker );
			}
			catch ( Throwable e ) {
				enableResourceUsagePolling = false;
				throw e;
			}

			nBrokerStatus.labels( brokerID ).set( 1 );
		}
		catch ( Throwable e ) {
			logger.error( "on doPoll(): " + e.getLocalizedMessage() );
			nBrokerStatus.labels( broker != null ? broker.getBrokerID() : brokerID ).set( 0 );
			broker = null;
			clearAllMetrics();
		}
	}

	/*
	 * Poll metrics for services
	 */
	private void pollMetrics_Services( Broker broker ) throws Throwable {
		InfoServiceMessage info = new InfoServiceMessage();
		info.setInterfaceVersion( ServiceObject.IV );
		info.setBlockLength     ( new BlockLength( 7200 ) );
		info.setObjectType      ( ServiceObject.OT );
		ServiceRequest      req = new ServiceRequest( broker, info );
		IServiceResponse    res = req.sendReceive();
		for ( int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++ ) {
			ServiceObject so = (ServiceObject) res.getServiceResponseObject( i );
			if ( filterServiceMetrics( so ) ) {
				String serviceName = printServiceName( so );
				String[] customLabel = getCustomLabelValues( serviceName );
				for ( int j = 0; j < customLabel.length; j++ ) {
					if ( isCustomLabelNameValid() ) {
						nServiceRequests.labels         ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getRequests() );
						nServiceServer.labels           ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getServerAct() );
						nServiceConvHigh.labels         ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getConvHigh() );
						nServiceConvPending.labels      ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getConvPending() );
						nServiceConvPendingHigh.labels  ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getConvPendingHigh() );
						nServiceConvActive.labels       ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getConvAct() );
						nServiceWaitsOfServers.labels   ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getNumWaits() );
						nServiceOccupiedServers.labels  ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getNumOccupied() );
						nServiceUOWsActive.labels       ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getUOWSAct() );
						nServiceUOWsSize.labels         ( broker.getBrokerID(), serviceName, customLabel[ j ] ).set( so.getUOWSMax() );
					}
					else {
						nServiceRequests.labels         ( broker.getBrokerID(), serviceName ).set( so.getRequests() );
						nServiceServer.labels           ( broker.getBrokerID(), serviceName ).set( so.getServerAct() );
						nServiceConvHigh.labels         ( broker.getBrokerID(), serviceName ).set( so.getConvHigh() );
						nServiceConvPending.labels      ( broker.getBrokerID(), serviceName ).set( so.getConvPending() );
						nServiceConvPendingHigh.labels  ( broker.getBrokerID(), serviceName ).set( so.getConvPendingHigh() );
						nServiceConvActive.labels       ( broker.getBrokerID(), serviceName ).set( so.getConvAct() );
						nServiceWaitsOfServers.labels   ( broker.getBrokerID(), serviceName ).set( so.getNumWaits() );
						nServiceOccupiedServers.labels  ( broker.getBrokerID(), serviceName ).set( so.getNumOccupied() );						
						nServiceUOWsActive.labels       ( broker.getBrokerID(), serviceName ).set( so.getUOWSAct() );
						nServiceUOWsSize.labels         ( broker.getBrokerID(), serviceName ).set( so.getUOWSMax() );
					}
				}
			}
		}
	}

	private String printServiceName( ServiceObject so ) {
		if ( formatServiceName.startsWith( "l" ) )
			return so.getServerClass() + "/" +  so.getServerName() + "/" + so.getService();
		else
			return so.getServerName();
	}

	private boolean filterServiceMetrics( ServiceObject so ) {
		return ! ( excludeServerClass != null && excludeServerClass.equals( so.getServerClass() ) );
	}

	private String getCustomLabelValue( String serviceName ) {
		String back = mapServiceToLabelValue.get( serviceName );
		if ( back == null || back.length() == 0 )
			back = "unknown";
		return back;
	}

	private String[] getCustomLabelValues( String serviceName ) {
		String        list = getCustomLabelValue( serviceName );
		StringTokenizer st = new StringTokenizer( list, "," );
		String[]      back = new String[ st.countTokens() ];
		int              i = 0;
		while ( st.hasMoreElements() ) {
			back[ i++ ] = st.nextToken();
		}

		return back;
	}

	private void clearAllMetrics() {
		try {
			nServiceRequests.clear();
			nServiceServer.clear();
			nServiceConvHigh.clear();
			nServiceConvPending.clear();
			nServiceConvPendingHigh.clear();
			nServiceConvActive.clear();
			nServiceWaitsOfServers.clear();
			nServiceOccupiedServers.clear();
			nServiceUOWsActive.clear();
			nServiceUOWsSize.clear();
		}
		catch (Throwable e ) {			
		}
	}

	private boolean isCustomLabelNameValid() {
		return customLabelName4Services != null && customLabelName4Services.length() > 0;
	}

	/*
	 * Poll metrics for Broker
	 */
	private void pollMetrics_Broker( Broker broker ) throws Throwable {
		InfoServiceMessage info = new InfoServiceMessage();
		info.setInterfaceVersion( BrokerObject.IV );
		info.setBlockLength     ( new BlockLength( 7200 ) );
		info.setObjectType      ( BrokerObject.OT );
		ServiceRequest      req = new ServiceRequest( broker, info );
		IServiceResponse    res = req.sendReceive();
		for ( int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++ ) {
			BrokerObject bo = (BrokerObject) res.getServiceResponseObject( i );
			
			nBrokerWorkerActive.labels       ( brokerID ).set( bo.getNumWorkerAct() );
			nBrokerLongBuffersSize.labels    ( brokerID ).set( bo.getNumLong() );
			nBrokerLongBufferActive.labels   ( brokerID ).set( bo.getLongAct() );
			nBrokerLongBuffersHigh.labels    ( brokerID ).set( bo.getLongHigh() );
			nBrokerShortBuffersSize.labels   ( brokerID ).set( bo.getNumShort() );
			nBrokerShortBuffersActive.labels ( brokerID ).set( bo.getShortAct() );
			nBrokerShortBuffersHigh.labels   ( brokerID ).set( bo.getShortHigh() );
			nBrokerServicesSize.labels       ( brokerID ).set( bo.getNumService() );
			nBrokerServicesActive.labels     ( brokerID ).set( bo.getServiceAct() );
			nBrokerServersSize.labels        ( brokerID ).set( bo.getNumServer() );
			nBrokerServersActive.labels      ( brokerID ).set( bo.getServerAct() );
			nBrokerServersHigh.labels        ( brokerID ).set( bo.getServerHigh() );
			nBrokerConversationsSize.labels  ( brokerID ).set( bo.getNumConv() );
			nBrokerConversationsHigh.labels  ( brokerID ).set( bo.getConvHigh() );
		}
	}
	
	/*
	 * Poll metrics for Resource Usage
	 */
	private void pollMetrics_ResourceUsage( Broker broker ) throws Throwable {
		InfoServiceMessage info = new InfoServiceMessage();
		info.setInterfaceVersion( ResourceUsageObject.IV );
		info.setBlockLength     ( new BlockLength( 7200 ) );
		info.setObjectType      ( ResourceUsageObject.OT );
		ServiceRequest      req = new ServiceRequest( broker, info );
		IServiceResponse    res = req.sendReceive();
		for ( int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++ ) {
			ResourceUsageObject bo = (ResourceUsageObject) res.getServiceResponseObject( i );
			//logger.info( bo.toString() );
		}
	}
	
}
