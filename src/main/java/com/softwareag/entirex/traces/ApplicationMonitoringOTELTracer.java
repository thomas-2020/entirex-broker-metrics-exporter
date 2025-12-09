
package com.softwareag.entirex.traces;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.GlobalOpenTelemetry;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softwareag.entirex.metrics.ApplicationMonitoringCallback;
import com.softwareag.entirex.metrics.BrokerDataCollector;

public class ApplicationMonitoringOTELTracer {

	private static final Logger logger = LoggerFactory.getLogger( ApplicationMonitoringOTELTracer.class );

	private static Tracer tracer = GlobalOpenTelemetry.getTracer( "entirex-application-monitoring");

	/*
	 * Attributes ...
	 * {TimeResponse=6000, ApplicationName=RPC/SRV1/CALLNAT, Program=CALC, Address=localhost:1971/RPC/SRV1/CALLNAT, TimeStamp=1765290050777, ClientHost=IBM-PF45YEV6, ClientApplication=IDL Tester, CorrelationID=42eb8527-d50a-11f0-339f-4da9bc9c213c, ErrorCode=10010016, ErrorMessage=Callee not found. Library: EXAMPLE  Program: CALC  RPC Server: Interface Object (Library) DEXAMPLE not found., ClientUser=ThomasRupp, MessageID=75d3e73a-6bb6-4d5b-aa65-b2231a2a04d6, AppMonScenario=RPC}
	 */
	public static void trace( Map<String, String> attributes ) {

		String program = ApplicationMonitoringCallback.getLabelProgram( attributes );
		if ( program == null || program.length() == 0 )
			return;


		String valueS = attributes.get( "TimeStamp" );
		if ( valueS == null || valueS.length() == 0 )
			return;

		long endTime = Long.valueOf( valueS );

		valueS = attributes.get( "TimeResponse" ); // in Microseconds
		if ( valueS == null || valueS.length() == 0 )
			return;

		long timeResponse = Long.valueOf( valueS );

		//Start the Span
		Span s = tracer.spanBuilder( program ).setStartTimestamp( ( endTime * 1000 ) - timeResponse , TimeUnit.MICROSECONDS ).startSpan();

		//Copy all attributes to Span
		attributes.forEach( ( k ,v ) -> { s.setAttribute( k, v ); } ); 

		if ( ApplicationMonitoringCallback.hasError( attributes ) )
			s.setStatus( StatusCode.ERROR, ApplicationMonitoringCallback.getError( attributes ) );

		//End the Span
		s.end( endTime, TimeUnit.MILLISECONDS );
	}

	public static void log( String message ) {
		Span s = tracer.spanBuilder( message ).startSpan();
		s.end();
	}
}