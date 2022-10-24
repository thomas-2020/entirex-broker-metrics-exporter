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

import java.math.*;
import com.softwareag.entirex.cis.utils.*;

import java.util.Date;

public class SubscriberObject
    extends AbstractServiceResponseObject
	{
	public SubscriberObject()
		{
		}

	public InterfaceVersion getInterfaceVersion()
		{
		return InterfaceVersion.VERSION_4;
		}


	public String getTopic()
		{
		return new String( abResponse, iOff + 0, 96 ).trim();
		}

	public String getUserID()
		{
		return new String( abResponse, iOff + 96, 32 ).trim();
		}


	public String getPUID()
		{
		return new String( abResponse, iOff + 96 + 32, 28 ).trim();
		}

	public String getPUIDTranslated()
		{
		return new String( abResponse, iOff + 96 + 32 + 28 , 28 ).trim();
		}
	
	public String getToken()
		{
		return new String( abResponse, iOff + 96 + 32 + 28 + 28, 32 ).trim();
		}

	public Date getSubscriptionTime()
		{
		return new Date( new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 32 + 28 + 28 + 32, 4 ) ).intValue() );
		}

	public Date getLastActivityTime()
		{
		return new Date( new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 32 + 28 + 28 + 32 + 4, 4 ) ).intValue() );
		}


	public Date getExpirationTime()
		{
		return new Date( new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 32 + 28 + 28 + 32 + 4 + 4, 4 ) ).intValue() );
		}

	public String getLastPublicationCommitted()
		{
		return new String( abResponse, iOff + 96 + 32 + 28 + 28 + 32 + 4 + 4 + 4, 16 ).trim();
		}

	public String getLastPublicationReceived()
		{
		return new String( abResponse, iOff + 96 + 32 + 28 + 28 + 32 + 4 + 4 + 4 + 16, 16 ).trim();
		}

	public boolean getDurable()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 32 + 28 + 28 + 32 + 4 + 4 + 4 + 16 + 16, 1 ) ).intValue() == 1;
		}

	public boolean getSwappedOut()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 32 + 28 + 28 + 32 + 4 + 4 + 4 + 16 + 16 + 1, 1 ) ).intValue() == 1;
		}

	private void notUsed()
		{
		//2
		}
	
	public int getLength()
		{
		return 96 + 32 + 28 + 28 + 32 + 4 + 4 + 4 + 16 + 16 + 1 + 1 + 2;
		}
	}