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

import com.softwareag.entirex.cis.ServiceResponseException;

public interface IServiceResponseObject
	{
	public int getLength();
	public void init( byte[] abResponse, int iOff, int iLen )
		throws ServiceResponseException;
	}