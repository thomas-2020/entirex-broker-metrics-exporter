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

public class PSFDIVObject
    extends AbstractServiceResponseObject
	{
    private static final int O_INFO_VERS        = 0;
    private static final int L_INFO_VERS        = 4;

    private static final int O_SH_NAME          = O_INFO_VERS + L_INFO_VERS;
    private static final int L_SH_NAME          = 8;
	
    private static final int O_SH_FORMAT_TOD    = O_SH_NAME + L_SH_NAME;
    private static final int L_SH_FORMAT_TOD    = 16;

    private static final int O_SH_FORMAT_VERS   = O_SH_FORMAT_TOD + L_SH_FORMAT_TOD;
    private static final int L_SH_FORMAT_VERS   = 4;

    private static final int O_SH_HWMARK        = O_SH_FORMAT_VERS + L_SH_FORMAT_VERS;
    private static final int L_SH_HWMARK        = 4;

    private static final int O_SH_START_CNT     = O_SH_HWMARK + L_SH_HWMARK;
    private static final int L_SH_START_CNT     = 4;

    private static final int O_SH_DS_ALET       = O_SH_START_CNT + L_SH_START_CNT;
    private static final int L_SH_DS_ALET       = 4;

    private static final int O_SH_ATT_LEN       = O_SH_DS_ALET + L_SH_DS_ALET;
    private static final int L_SH_ATT_LEN       = 4;

    private static final int O_SH_OID_LEN       = O_SH_ATT_LEN + L_SH_ATT_LEN;
    private static final int L_SH_OID_LEN       = 4;

    private static final int O_SH_OID_OFF       = O_SH_OID_LEN + L_SH_OID_LEN;
    private static final int L_SH_OID_OFF       = 4;

    private static final int O_SH_IXMODULUS     = O_SH_OID_OFF + L_SH_OID_OFF;
    private static final int L_SH_IXMODULUS     = 4;

    private static final int O_SH_CP_DEF_CNT    = O_SH_IXMODULUS + L_SH_IXMODULUS;
    private static final int L_SH_CP_DEF_CNT    = 4;

    private static final int O_CP_NAME          = O_SH_CP_DEF_CNT + L_SH_CP_DEF_CNT;
    private static final int L_CP_NAME          = 8;

    private static final int O_CP_CELL_SIZE     = O_CP_NAME + L_CP_NAME;
    private static final int L_CP_CELL_SIZE     = 4;

    private static final int O_CP_CELL_TOTAL    = O_CP_CELL_SIZE + L_CP_CELL_SIZE;
    private static final int L_CP_CELL_TOTAL    = 4;

    private static final int O_CP_CELL_AVAIL    = O_CP_CELL_TOTAL + L_CP_CELL_TOTAL;
    private static final int L_CP_CELL_AVAIL    = 4;

    private static final int O_CP_EXTENT_CNT    = O_CP_CELL_AVAIL + L_CP_CELL_AVAIL;
    private static final int L_CP_EXTENT_CNT    = 4;

    private static final int O_CP_QUERY_RC      = O_CP_EXTENT_CNT + L_CP_EXTENT_CNT;
    private static final int L_CP_QUERY_RC      = 4;

    private static final int O_CX_STATUS        = O_CP_QUERY_RC + L_CP_QUERY_RC;
    private static final int L_CX_STATUS        = 4;

    private static final int O_CX_EXTENT_ADDR   = O_CX_STATUS + L_CX_STATUS;
    private static final int L_CX_EXTENT_ADDR   = 4;

    private static final int O_CX_EXTENT_LEN       = O_CX_EXTENT_ADDR + L_CX_EXTENT_ADDR;
    private static final int L_CX_EXTENT_LEN       = 4;

    private static final int O_CX_AREA_ADDR        = O_CX_EXTENT_LEN + L_CX_EXTENT_LEN;
    private static final int L_CX_AREA_ADDR        = 4;

    private static final int O_CX_AREA_LEN         = O_CX_AREA_ADDR + L_CX_AREA_ADDR;
    private static final int L_CX_AREA_LEN         = 4;

    private static final int O_CX_CELL_TOTAL       = O_CX_AREA_LEN + L_CX_AREA_LEN;
    private static final int L_CX_CELL_TOTAL       = 4;

    private static final int O_CX_CELL_AVAIL       = O_CX_CELL_TOTAL + L_CX_CELL_TOTAL;
    private static final int L_CX_CELL_AVAIL       = 4;

    private static final int O_CX_QUERY_RC         = O_CX_CELL_AVAIL + L_CX_CELL_AVAIL;
    private static final int L_CX_QUERY_RC         = 4;

    private static final int L_PSFDIV_OBJECT       = O_CX_QUERY_RC + L_CX_QUERY_RC;

    public PSFDIVObject()
    	{
    	}

    public PSFDIVObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
	    {
        super( abResponse, iOff, iLen );
    	}

    public int getLength()
    	{
        return L_PSFDIV_OBJECT;
	    }

	public int getVersion()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_INFO_VERS + iOff, L_INFO_VERS ) ).intValue();		
		}

	public String getName()
		{
		return new String( abResponse, O_SH_NAME + iOff, L_SH_NAME ).trim();		
		}

	public String getFormatTime()
		{
		return new String( abResponse, O_SH_FORMAT_TOD + iOff, L_SH_FORMAT_TOD ).trim();		
		}

	public int getFormatVersion()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_SH_FORMAT_VERS + iOff, L_SH_FORMAT_VERS ) ).intValue();		
		}

	public int getStartCount()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_SH_START_CNT + iOff, L_SH_START_CNT ) ).intValue();		
		}

	public String getCellPoolName()
		{
		return new String( abResponse, O_CP_NAME + iOff, L_CP_NAME ).trim();		
		}

	public int getCellSize()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CP_CELL_SIZE + iOff, L_CP_CELL_SIZE ) ).intValue();		
		}

	public int getCellTotal()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CP_CELL_TOTAL + iOff, L_CP_CELL_TOTAL ) ).intValue();		
		}

	public int getCellAvailable()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CP_CELL_AVAIL + iOff, L_CP_CELL_AVAIL ) ).intValue();		
		}

	public int getCellPoolExtentStatus()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CX_STATUS + iOff, L_CX_STATUS ) ).intValue();		
		}

	public int getCellPoolExtentLength()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CX_EXTENT_LEN + iOff, L_CX_EXTENT_LEN ) ).intValue();		
		}

	public int getCellAreaLength()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CX_AREA_LEN + iOff, L_CX_AREA_LEN ) ).intValue();		
		}
	}