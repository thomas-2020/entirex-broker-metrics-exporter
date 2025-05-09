package com.softwareag.entirex.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App
{
    public static void main( String[] args ) {
		SpringApplication.run( App.class, args );


    	Thread thread = new Thread(){
    	    public void run(){
    	    	com.softwareag.entirex.appmondc.Collector.main( args );
    	    }
    	};
    	thread.start();
    }
}
