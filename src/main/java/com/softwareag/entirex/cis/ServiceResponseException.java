/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Projekt: EntireX Broker Command and Info Services Interface
 *
 * Change history:
 *
 * Nr ! Datum    ! Name            ! Änderungsgrund
 * -----------------------------------------------------------------------------
 *  1 ! 03.04.03 ! Rupp            ! created
 *
 */

package com.softwareag.entirex.cis;

import java.io.PrintStream;

public class ServiceResponseException
    extends Exception
{
    private Throwable oThrowable;

    public ServiceResponseException( String sMsg )
    	{
        super( sMsg );
    	}

    public ServiceResponseException( String sMsg, Throwable oThrowable )
    	{
        super(sMsg);
        this.oThrowable = oThrowable;
    	}

    public Throwable getThrowable()
    	{
        return oThrowable;
    	}

	public void printStackTrace( PrintStream writer )
		{
		super.printStackTrace( writer );
		writer.print( "\nNested Exception:\n" );
		
		if ( oThrowable != null )
			oThrowable.printStackTrace( writer );
		}

	public String getMessage()
		{
		if ( oThrowable != null )
			return super.getMessage() + " - Nested Exception: " + oThrowable.getMessage();
		else
			return super.getMessage();
		}
}