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

public class PublisherObject
    extends AbstractServiceResponseObject
	{
	public PublisherObject()
		{
		}

	public InterfaceVersion getInterfaceVersion()
		{
		return InterfaceVersion.VERSION_4;
		}


	public String getUserID()
		{
		return new String( abResponse, iOff + 0, 32 ).trim();
		}

	public String getPUID()
		{
		return new String( abResponse, iOff + 32, 28 ).trim();
		}

	public String getPUIDTranslated()
		{
		return new String( abResponse, iOff + 32 + 28, 28 ).trim();
		}

	public String getToken()
		{
		return new String( abResponse, iOff + 32 + 28 + 28, 32 ).trim();
		}

	public int getCharSet()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32, 2 ) ).intValue();
		}

	public boolean isHighOrderFirst()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2, 1 ) ).intValue() == 1;
		}

	/**
	 * Status field
	**/
	public boolean isStatusUserWaiting()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1, 1 ) ).intValue() == 5;
		}

	public void notUsed()
		{
		//2
		}

	public String getWaitingFor()
		{
		return new String( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2, 32 ).trim();
		}

	public String getTopic()
		{
		return new String( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32, 96 ).trim();
		}

	public int getPublicationActive()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96, 4 ) ).intValue();
		}

	public int getTopicActive()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4, 4 ) ).intValue();
		}

	public int getLastActivity()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4, 4 ) ).intValue();
		}

	public int getNonActivity()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getWaitNew()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getWaitNewCount()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getWaitOld()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getWaitOldCount()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getPublicationCount()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public String getPublisherIPAddresse()
		{
		return new String( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 16 );
		}

	public String getPublisherHostName()
		{
		return new String( abResponse, iOff + 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 16, 256 );
		}
	
	public int getLength()
		{
		return 32 + 28 + 28 + 32 + 2 + 1 + 1 + 2 + 32 + 96 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 16 + 256;
		}
	}