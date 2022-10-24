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

package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.params.InterfaceVersion;

public abstract class AbstractServiceResponseObject implements IServiceResponseObject
	{
	protected byte[] abResponse;
	protected int iOff;
	protected int iLen;

	public AbstractServiceResponseObject()
		{
		}
	
	public AbstractServiceResponseObject( byte[] abResponse, int iOff, int iLen )
		throws ServiceResponseException
		{
		init( abResponse, iOff, iLen );
		}

	public void init( byte[] abResponse, int iOff, int iLen )
		throws ServiceResponseException
		{
		if (abResponse.length - iOff < getLength())
			{
			throw new ServiceResponseException("Malformed response (wrong length [" + getLength() + " bytes] extpected but [" + (abResponse.length - iOff) + " bytes] received)");
			}
	
		this.abResponse = abResponse;
		this.iOff = iOff;
		this.iLen = iLen;
		}
	
	public int getLength()
		{
		return -1;
		}

	public InterfaceVersion getInterfaceVersion()
		{
		return InterfaceVersion.VERSION_2;
		}
	}