package com.softwareag.entirex.metrics;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

@RestController
@RequestMapping( "/metrics" )
public class Controller {

	@GetMapping("")
	public void doGet( HttpServletResponse response ) throws IOException {
		
		response.addHeader( "content-type", "text/plain; charset=utf-8" );
		response.setStatus( 200 );
		try {
			PrintWriter out = response.getWriter();
			TextFormat.write004( out, CollectorRegistry.defaultRegistry.metricFamilySamples() );
			out.flush();
			out.close();
		}
		catch ( IOException e ) {
			response.sendError( 500, e.getLocalizedMessage() );
		}
	}
}
