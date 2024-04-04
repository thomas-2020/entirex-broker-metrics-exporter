/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Projekt: Systemmanagement
 *
 * Change history:
 *
 * Nr ! Datum    ! Name            ! Änderungsgrund
 * -----------------------------------------------------------------------------
 *  1 ! 11.05.04 ! Rupp            ! neu erstellt
 *
 */

package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import java.math.*;

public class PSFADAObject
    extends AbstractServiceResponseObject
	{
    private static final int O_DBID             = 0;
    private static final int L_DBID             = 4;

    private static final int O_FNR              = O_DBID + L_DBID;
    private static final int L_FNR              = 8;
	

    private static final int L_PSFADA_OBJECT       = O_FNR + L_FNR;

    public PSFADAObject()
    	{
    	}

    public PSFADAObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
	    {
        super( abResponse, iOff, iLen );
    	}

    public int getLength()
    	{
        return L_PSFADA_OBJECT;
	    }

	public int getDBID()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_DBID + iOff, L_DBID ) ).intValue();		
		}
	}