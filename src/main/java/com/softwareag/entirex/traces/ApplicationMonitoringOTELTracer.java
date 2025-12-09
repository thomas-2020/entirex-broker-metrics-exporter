
package com.softwareag.entirex.traces;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.GlobalOpenTelemetry;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.softwareag.entirex.metrics.ApplicationMonitoringCallback;

public class ApplicationMonitoringOTELTracer {

	private static Tracer tracer = GlobalOpenTelemetry.getTracer( "entirex-application-monitoring");

	public static void trace( Map<String, String> attributes ) {

		String program = ApplicationMonitoringCallback.getLabelProgram( attributes );
		if ( program == null || program.length() == 0 )
			return;

		String valueS = attributes.get( "Timestamp" );
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

		//End the Span
		s.end( endTime, TimeUnit.MILLISECONDS );
	}

	public static void log( String message ) {
		Span s = tracer.spanBuilder( message ).startSpan();
		s.end();
	}
}