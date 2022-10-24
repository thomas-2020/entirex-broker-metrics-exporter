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
 * Defines the option values for comand info servies.
**/
public class Option extends AbstractBinaryRequestParam
	{
	public static final Option DEFAULT = new Option( new byte[] { (byte)0x00, (byte)0x00 } );
	public static final Option IMMED   = new Option( new byte[] { (byte)0x00, (byte)0x03 } );
	public static final Option QUIESCE = new Option( new byte[] { (byte)0x00, (byte)0x04 } );
	public static final Option LEVEL1  = new Option( new byte[] { (byte)0x00, (byte)0x11 } );
	public static final Option LEVEL2  = new Option( new byte[] { (byte)0x00, (byte)0x12 } );
	public static final Option LEVEL3  = new Option( new byte[] { (byte)0x00, (byte)0x13 } );
	public static final Option LEVEL4  = new Option( new byte[] { (byte)0x00, (byte)0x14 } );

	private byte[] abSetting;
	
	private Option( byte[] abSetting )
		{
		super( abSetting );
		}
	}
