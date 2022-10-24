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

import com.softwareag.entirex.cis.objects.AbstractServiceResponseObject;

public class InterfaceVersion extends AbstractBinaryRequestParam
	{
	public static final InterfaceVersion VERSION_1 = new InterfaceVersion( new byte[]{ (byte)0x00, (byte)0x01 } );
	public static final InterfaceVersion VERSION_2 = new InterfaceVersion( new byte[]{ (byte)0x00, (byte)0x02 } );
	public static final InterfaceVersion VERSION_3 = new InterfaceVersion( new byte[]{ (byte)0x00, (byte)0x03 } );
	public static final InterfaceVersion VERSION_4 = new InterfaceVersion( new byte[]{ (byte)0x00, (byte)0x04 } );
	
	private InterfaceVersion(byte[] abSetting)
		{
	    super(abSetting);
		}

	public static InterfaceVersion getFor( ObjectType type )
		throws Throwable
		{
		return ( (AbstractServiceResponseObject) type.getClassFor().newInstance() ).getInterfaceVersion();
		}
	}
