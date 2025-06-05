package com.softwareag.entirex.metrics;

import com.softwareag.entirex.appmondc.DataCollectorCallback;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.prometheus.client.Gauge;
import io.prometheus.client.Counter;

public class ApplicationMonitoringCallback implements DataCollectorCallback {

	private static final Logger logger = LoggerFactory.getLogger( ApplicationMonitoringCallback.class );

	private Gauge rpcTimeResponse;
	private Gauge rpcTimeClientLayer;
	private Gauge rpcTimeClientTransport;
	private Gauge rpcTimeBroker;
	private Gauge rpcTimeBrokerWaitForServer;
	private Gauge rpcTimeServerTransport;
	private Gauge rpcTimeServerLayer;
	private Gauge rpcTimeServerProgram;
	private Gauge rpcTimeDBCalls;
	private Gauge rpcTimeDBTransport;
	private Counter rpcCallsSuccess;
	private Counter rpcCallsError;
	private Counter rpcCalls;

    @Override
    public void start( String appmondcDirectory ) {
    	logger.info( "Application Monitoring DC Callback [" + appmondcDirectory + "] is started" );

		try {
			String labelPrefix          = "sag_rpc_"; //Prefix for all labels
			rpcTimeResponse             = Gauge.build().name  ( labelPrefix + "time_response"               ).help( "The complete response time (roundtrip from client to server and back) in microseconds"                                              ).labelNames( "broker", "service", "program" ).register();
			rpcTimeClientLayer          = Gauge.build().name  ( labelPrefix + "time_client_layer"           ).help( "The time spent in the client RPC layer in microseconds"                                                                             ).labelNames( "broker", "service", "program" ).register();
			rpcTimeClientTransport      = Gauge.build().name  ( labelPrefix + "time_client_transport"       ).help( "The transport time from the client to the broker and back in microseconds"                                                          ).labelNames( "broker", "service", "program" ).register();
			rpcTimeBroker               = Gauge.build().name  ( labelPrefix + "time_broker"                 ).help( "The time spent in the broker (active processing) in microseconds"                                                                   ).labelNames( "broker", "service", "program" ).register();
			rpcTimeBrokerWaitForServer  = Gauge.build().name  ( labelPrefix + "time_broker_wait_for_server" ).help( "The time spent in the broker waiting for an available server in microseconds"                                                       ).labelNames( "broker", "service", "program" ).register();
			rpcTimeServerTransport      = Gauge.build().name  ( labelPrefix + "time_server_transport"       ).help( "The transport time from the broker to the server and back in microseconds"                                                          ).labelNames( "broker", "service", "program" ).register();
			rpcTimeServerLayer          = Gauge.build().name  ( labelPrefix + "time_server_layer"           ).help( "The time spent in the server RPC layer (runtime and stub) in microseconds"                                                          ).labelNames( "broker", "service", "program" ).register();
			rpcTimeServerProgram        = Gauge.build().name  ( labelPrefix + "time_server_program"         ).help( "The time spent in the user program in microseconds"                                                                                 ).labelNames( "broker", "service", "program" ).register();
			rpcTimeDBCalls              = Gauge.build().name  ( labelPrefix + "time_db_calls"               ).help( "The time spent for database calls  microseconds"                                                                                    ).labelNames( "broker", "service", "program" ).register();
			rpcTimeDBTransport          = Gauge.build().name  ( labelPrefix + "time_db_transport"           ).help( "The transport time from the Natural user program to the Adabas router and back including the client receiving time in microseconds" ).labelNames( "broker", "service", "program" ).register();
			
			rpcCalls                    = Counter.build().name( labelPrefix + "calls"           ).help( "Count RPC calls"         ).labelNames( "broker", "service", "program" ).register();
			rpcCallsSuccess             = Counter.build().name( labelPrefix + "success_calls"   ).help( "Count RPC success calls" ).labelNames( "broker", "service", "program" ).register();
			rpcCallsError               = Counter.build().name( labelPrefix + "error_calls"     ).help( "Count RPC error calls"   ).labelNames( "broker", "service", "program" ).register();
		}
		catch (Throwable e ) {
			logger.error( "On ApplicationMonitoringCallback init: ", e );
		}
		
    }

    @Override
	public void stop() {
    	logger.info( "Application Monitoring DC Callback is stopped" );
	}

	@Override
	public void processEvent(Map<String, String> attributes) throws Exception {
		String scenario = attributes.get( "AppMonScenario" );
		if ( scenario != null && scenario.equals( "RPC" ) ) {
			//Copy metrics from DC to Prometheus
			String broker  = getLabelBroker( attributes );
			String service = getLabelService( attributes );
			String program = getLabelProgram( attributes );

			rpcCalls.labels( broker, service, program ).inc();
			if ( hasError( attributes ) ) 
				rpcCallsError.labels( broker, service, program ).inc();
			else
				rpcCallsSuccess.labels( broker, service, program ).inc();

			String valueS = attributes.get( "TimeResponse" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeResponse.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeClientLayer" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeClientLayer.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeClientTransport" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeClientTransport.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeBroker" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeBroker.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeBrokerWaitForServer" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeBrokerWaitForServer.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeServerTransport" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeServerTransport.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeServerLayer" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeServerLayer.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeServerProgram" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeServerProgram.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeDBCalls" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeDBCalls.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
			valueS = attributes.get( "TimeDBTransport" );
			if ( valueS != null && valueS.length() > 0 )
				rpcTimeDBTransport.labels( broker, service, program ).inc( Double.valueOf( valueS ) );
		}
	}
	private String getLabelBroker( Map<String, String> attributes ) {
		String back = attributes.get( "Address" );
		int i = back.indexOf( "/" );
		return back.substring( 0, i );
	}
	private String getLabelService( Map<String, String> attributes ) {
		String back = attributes.get( "ApplicationName" );
		if ( back.startsWith( "RPC" ) ) {
			int i = back.indexOf( "/" );
			if ( i > 0 ) {
				int j = back.indexOf( "/", i + 1 );
				if ( j > 0 )
					back = back.substring( i + 1, j );				
			}
		}
		return back;
	}
	
	private String getLabelProgram( Map<String, String> attributes ) {
		return attributes.get( "Program" );
	}
	private boolean hasError( Map<String, String> attributes ) {
		return attributes.get( "ErrorCode" ) != null;
	}
}