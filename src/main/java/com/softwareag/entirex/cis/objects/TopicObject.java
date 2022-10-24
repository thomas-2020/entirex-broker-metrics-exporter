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

public class TopicObject
    extends AbstractServiceResponseObject
	{
	public TopicObject()
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

	public String getTranslation()
		{
		return new String( abResponse, iOff + 96, 8 ).trim();
		}

	public String getConversation()
		{
		return new String( abResponse, iOff + 96 + 8, 8 ).trim();
		}

	public int getPublisherNonActivity()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8, 4 ) ).intValue();
		}

	public int getSubscriberNonActivity()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4, 4 ) ).intValue();
		}

	public Date getSubscriberExpiration()
		{
		return new Date( new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4, 4 ) ).intValue() );
		}

	public int getPublicationActive()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getPublicationHigh()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getDurableSubscriberActive()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getNonDurableSubscriberActive()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getLongBufferActive()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getLongBufferHigh()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getShortBufferActive()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public int getShortBufferHigh()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 4 ) ).intValue();
		}

	public boolean isDurableAllowed()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4, 1 ) ).intValue() == 1;
		}

	public boolean isSubscribeAllowed()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 1, 1 ) ).intValue() == 1;
		}

	public boolean isAutoCommit()
		{
		return new BigInteger( Utils.getSubArray( abResponse, iOff + 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 1 + 1, 1 ) ).intValue() == 1;
		}

	private void notUsed()
		{
		//1
		}	

	public int getLength()
		{
		return 96 + 8 + 8 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 4 + 1 + 1 + 1 + 1;
		}
	}