package com.softwareag.entirex.metrics;

import com.softwareag.entirex.appmondc.DataCollectorCallback;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationMonitoringCallback implements DataCollectorCallback {

	private static final Logger logger = LoggerFactory.getLogger( ApplicationMonitoringCallback.class );

    @Override
    public void start( String appmondcDirectory ) {
    	logger.info( "Application Monitoring DC Callback [" + appmondcDirectory + "] is started" );
	}

    @Override
	public void stop() {
    	logger.info( "Application Monitoring DC Callback is stopped" );
	}

	@Override
	public void processEvent(Map<String, String> attributes) throws Exception {
	}
}