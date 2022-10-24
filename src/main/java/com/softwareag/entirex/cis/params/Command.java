/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Project: Systemmanagement
 *
 * Change list:
 *
 * Nr ! Datum    ! Name            ! Description
 * -----------------------------------------------------------------------------
 *  1 ! 11.05.04 ! Rupp            ! created
 *
 */

package com.softwareag.entirex.cis.params;

/**
 * Defines the comand types for command and info services.
**/
public class Command
    extends AbstractBinaryRequestParam
	{
	public static final Command SHUTDOWN  = new Command( new byte[] { (byte)0x00, (byte)0x08 } );
	public static final Command TRACE_ON  = new Command( new byte[] { (byte)0x00, (byte)0x01 } );
	public static final Command TRACE_OFF = new Command( new byte[] { (byte)0x00, (byte)0x02 } );
	public static final Command PURGE     = new Command( new byte[] { (byte)0x00, (byte)0x0c } );
	
	private Command(byte[] abSetting)
		{
		super(abSetting);
		}
	}
