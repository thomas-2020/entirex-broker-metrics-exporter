/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Projekt: Systemmanagement
 *
 * �nderungshistorie:
 *
 * Nr ! Datum    ! Name            ! �nderungsgrund
 * -----------------------------------------------------------------------------
 *  1 ! 06.06.01 ! Rupp            ! neu erstellt
 *
 */

package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import java.math.*;
import com.softwareag.entirex.cis.utils.*;

public class ServiceObject
    extends AbstractServiceResponseObject
	{

	//ETB_CHAR    server_class [S_SERVER_CLASS];/* class             WBLV3*/
	//ETB_CHAR    server [S_SERVER_NAME];   /* server                     */
	//ETB_CHAR    service    [S_SERVICE];   /* service                    */
	//ETB_CHAR    sTranslation[S_T_NAME];   /* name of translation routine*/
	//ETB_LONG    ConvNonact;               /* conversation non activity  */
	//                                    /* timeout                    */
	//ETB_LONG    nServerActive;            /* no. servers active         */
	//ETB_LONG    nConvActive;              /* no. conversations active   */
	//ETB_LONG    nConvHigh;                /* highest no. conversations  */
	//                                    /* simultaneously active      */
	//                                    /* since broker start         */
	//ETB_LONG    nLongMsgBufferActive;     /* no. long msg.buffs. active */
	//ETB_LONG    nLongMsgBufferHigh;       /* highest no. long msg.buffs.*/
	//                                    /* simultaneously active      */
	//                                    /* since broker start         */
	//ETB_LONG    nShortMsgBufferActive;    /* no. short msg.buffs. active*/
	//ETB_LONG    nShortMsgBufferHigh;      /* highest no.short msg.buffs.*/
	//                                    /* simultaneously active      */
	//                                    /* since broker start         */
	//ETB_LONG    nWaitServer;              /* no. waits for server msgs. */
	//ETB_LONG    nServerOccupied;          /* no. times all server were  */
	//                                    /* occupied                   */
	//ETB_LONG    nPending;                 /* no. pending conversations  */
	//ETB_LONG    nPendingHigh;             /* highest no. pending conv.  */
	//ETB_LONG    nTotalRequests;           /* accumulated no. requests   */
	//                                    /* begin version 2 additions  WBLV3*/
	//ETB_LONG    lMaxUOWs;                 /* Max # of active UOWs       WBLV3*/
	//ETB_LONG    lMaxUOWMsg;               /* Max # of msg per UOW       WBLV3*/
	//ETB_LONG    lUwTime;                  /* Max UOW life time          WBLV3*/
	//ETB_LONG    lMaxDelCnt;               /* Max Delivery count         WBLV3*/
	//ETB_LONG    lMaxMsgSize;              /* Max Message size           WBLV3*/
	//ETB_LONG    lTotalUOWs;               /* Number of active UOWs      WBLV3*/
	//ETB_CHAR    cStore;                   /* Store attribute for UOWs   WBLV3*/
	//                                    /*  valid values:             WBLV3*/
	//                                    /*      ETB_BROKER            WBLV3*/
	//                                    /*      ETB_OFF               WBLV3*/
	//ETB_CHAR    cUwStatP;                 /* UOW Status life time mult  WBLV3*/
	//ETB_CHAR    cDeferred;                /* Default status attr        WBLV3*/
	//ETB_CHAR    NotUsed;                  /* alignment                  WBLV3*/

    private static final int O_SERVER_CLASS        = 0;
    private static final int L_SERVER_CLASS        = 32;

    private static final int O_SERVER_NAME         = O_SERVER_CLASS + L_SERVER_CLASS;
    private static final int L_SERVER_NAME         = 32;

    private static final int O_SERVICE_NAME        = O_SERVER_NAME + L_SERVER_NAME;
    private static final int L_SERVICE_NAME        = 32;

    private static final int O_TRANSLATION         = O_SERVICE_NAME + L_SERVICE_NAME;
    private static final int L_TRANSLATION         = 8;

    private static final int O_CONV_NONACT         = O_TRANSLATION + L_TRANSLATION;
    private static final int L_CONV_NONACT         = 4;

    private static final int O_SERVER_ACTIVE       = O_CONV_NONACT + L_CONV_NONACT;
    private static final int L_SERVER_ACTIVE       = 4;

    private static final int O_CONV_ACTIVE         = O_SERVER_ACTIVE + L_SERVER_ACTIVE;
    private static final int L_CONV_ACTIVE         = 4;

    private static final int O_CONV_HIGH           = O_CONV_ACTIVE + L_CONV_ACTIVE;
    private static final int L_CONV_HIGH           = 4;

    private static final int O_LONG_ACT            = O_CONV_HIGH + L_CONV_HIGH;
    private static final int L_LONG_ACT            = 4;

    private static final int O_LONG_HIGH           = O_LONG_ACT + L_LONG_ACT;
    private static final int L_LONG_HIGH           = 4;

    private static final int O_SHORT_ACT           = O_LONG_HIGH + L_LONG_HIGH;
    private static final int L_SHORT_ACT           = 4;

    private static final int O_SHORT_HIGH          = O_SHORT_ACT + L_SHORT_ACT;
    private static final int L_SHORT_HIGH          = 4;

    private static final int O_WAIT_SERVER         = O_SHORT_HIGH + L_SHORT_HIGH;
    private static final int L_WAIT_SERVER         = 4;

    private static final int O_SERVER_OCCUPIED     = O_WAIT_SERVER + L_WAIT_SERVER;
    private static final int L_SERVER_OCCUPIED     = 4;

    private static final int O_PENDING             = O_SERVER_OCCUPIED + L_SERVER_OCCUPIED;
    private static final int L_PENDING             = 4;

    private static final int O_PENDING_HIGH        = O_PENDING + L_PENDING;
    private static final int L_PENDING_HIGH        = 4;

    private static final int O_TOTAL_REQUESTS      = O_PENDING_HIGH + L_PENDING_HIGH;
    private static final int L_TOTAL_REQUESTS      = 4;

    private static final int O_MAX_UOWS            = O_TOTAL_REQUESTS + L_TOTAL_REQUESTS;
    private static final int L_MAX_UOWS            = 4;

    private static final int O_MAX_UOWS_MSG        = O_MAX_UOWS + L_MAX_UOWS;
    private static final int L_MAX_UOWS_MSG        = 4;

    private static final int O_UOW_LIFETIME        = O_MAX_UOWS_MSG + L_MAX_UOWS_MSG;
    private static final int L_UOW_LIFETIME        = 4;

    private static final int O_MAX_DEL_COUNT       = O_UOW_LIFETIME + L_UOW_LIFETIME;
    private static final int L_MAX_DEL_COUNT       = 4;

    private static final int O_MAX_MSG_SIZE        = O_MAX_DEL_COUNT + L_MAX_DEL_COUNT;
    private static final int L_MAX_MSG_SIZE        = 4;

    private static final int O_TOTAL_UOWS          = O_MAX_MSG_SIZE + L_MAX_MSG_SIZE;
    private static final int L_TOTAL_UOWS          = 4;

    private static final int O_C_STORE             = O_TOTAL_UOWS + L_TOTAL_UOWS;
    private static final int L_C_STORE             = 1;

    private static final int O_UOW_STATUS_P        = O_C_STORE + L_C_STORE;
    private static final int L_UOW_STATUS_P        = 1;

    private static final int O_DEFERRED            = O_UOW_STATUS_P + L_UOW_STATUS_P;
    private static final int L_DEFERRED            = 1;

    private static final int O_NOT_USED            = O_DEFERRED + L_DEFERRED;
    private static final int L_NOT_USED            = 1;

	private static final int L_SERVICE_OBJECT      = O_NOT_USED + L_NOT_USED;

	public ServiceObject()
		{
		}
	
	public ServiceObject( byte[] abResponse, int iOff, int iLen )
		throws ServiceResponseException
		{
		super( abResponse, iOff, iLen );
		}

	public int getLength()
		{
		return L_SERVICE_OBJECT;
		}

	public String getServerClass()
		{
		return new String( abResponse, O_SERVER_CLASS + iOff, L_SERVER_CLASS ).trim();
		}

	public String getServerName()
		{
		return new String( abResponse, O_SERVER_NAME + iOff, L_SERVER_NAME ).trim();
		}

	public String getService()
		{
		return new String( abResponse, O_SERVICE_NAME + iOff, L_SERVICE_NAME ).trim();
		}

	public String getTranslation()
		{
		return new String( abResponse, O_TRANSLATION + iOff, L_TRANSLATION ).trim();
		}

	public int getConvNonactTimeout()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CONV_NONACT + iOff, L_CONV_NONACT ) ).intValue();
		}

	public int getServerAct()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_SERVER_ACTIVE + iOff, L_SERVER_ACTIVE ) ).intValue();
		}

	public int getConvAct()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CONV_ACTIVE + iOff, L_CONV_ACTIVE ) ).intValue();
		}

	public int getConvHigh()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CONV_HIGH + iOff, L_CONV_HIGH ) ).intValue();
		}

	public int getShortAct()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_SHORT_ACT + iOff, L_SHORT_ACT ) ).intValue();
		}
	
	public int getShortHigh()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_SHORT_HIGH + iOff, L_SHORT_HIGH ) ).intValue();
		}

	public int getLongAct()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_LONG_ACT + iOff, L_LONG_ACT ) ).intValue();
		}
	
	public int getLongHigh()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_LONG_HIGH + iOff, L_LONG_HIGH ) ).intValue();
		}

	public int getNumWaits()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_WAIT_SERVER + iOff, L_WAIT_SERVER ) ).intValue();
		}

	public int getNumOccupied()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_SERVER_OCCUPIED + iOff, L_SERVER_OCCUPIED ) ).intValue();
		}

	public int getConvPending()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_PENDING + iOff, L_PENDING ) ).intValue();
		}

	public int getConvPendingHigh()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_PENDING_HIGH + iOff, L_PENDING_HIGH ) ).intValue();
		}

	public int getRequests()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_TOTAL_REQUESTS + iOff, L_TOTAL_REQUESTS ) ).intValue();
		}

	public int getUOWSMax()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_MAX_UOWS + iOff, L_MAX_UOWS ) ).intValue();
		}

	public int getUOWSAct()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_TOTAL_UOWS + iOff, L_TOTAL_UOWS ) ).intValue();
		}

	public int getUOWMsgMax()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_MAX_UOWS_MSG + iOff, L_MAX_UOWS_MSG ) ).intValue();
		}

	public int getUOWLifetime()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_UOW_LIFETIME + iOff, L_UOW_LIFETIME ) ).intValue();
		}

	public int getMsgSizeMax()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_MAX_MSG_SIZE + iOff, L_MAX_MSG_SIZE ) ).intValue();
		}

	public int getStoreType()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_C_STORE + iOff, L_C_STORE ) ).intValue();
		}

	public int getLifetimeMult()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_UOW_STATUS_P + iOff, L_UOW_STATUS_P ) ).intValue();
		}

	public int getDeferredFlag()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_DEFERRED + iOff, L_DEFERRED ) ).intValue();
		}

	}