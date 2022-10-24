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

import com.softwareag.entirex.cis.params.InterfaceVersion;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import java.math.*;
import com.softwareag.entirex.cis.utils.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale;

public class PublicationObject
    extends AbstractServiceResponseObject
	{
	public PublicationObject()
		{
		}

	public InterfaceVersion getInterfaceVersion()
		{
		return InterfaceVersion.VERSION_4;
		}


	public String getPublicationID()
		{
		return new String( abResponse, iOff + 0, 16 ).trim();
		}

	public String getUserID()
		{
		return new String( abResponse, iOff + 16, 32 ).trim();
		}

	public String getPUID()
		{
		return new String( abResponse, iOff + 16 + 32, 28 ).trim();
		}

	public String getPUIDTransated()
		{
		return new String( abResponse, iOff + 16 + 32 + 28, 28 ).trim();
		}

	public String getToken()
		{
		return new String( abResponse, iOff + 16 + 32 + 28 + 28, 32 ).trim();
		}

	public String getTopic()
		{
		return new String( abResponse, iOff + 16 + 32 + 28 + 28 + 32, 96 ).trim();
		}

	public int getLastActivity()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 16 + 32 + 28 + 28 + 32 + 96, 4 ) ).intValue();
		}

	public Date getExpirationTime()
		{
		return new Date( new BigInteger( Utils.getSubArray( abResponse, iOff + 16 + 32 + 28 + 28 + 32 + 96 + 4, 4 ) ).intValue() );
		}

	public int getMessageCount()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 16 + 32 + 28 + 28 + 32 + 96 + 4 + 4, 4 ) ).intValue();
		}

	public int getStatus()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 16 + 32 + 28 + 28 + 32 + 96 + 4 + 4 + 4, 1 ) ).intValue();
		}

	private void notUsed()
		{
		//3
		}	

	public int getLength()
		{
		return 16 + 32 + 28 + 28 + 32 + 96 + 4 + 4 + 4 + 1 + 3;
		}
	}