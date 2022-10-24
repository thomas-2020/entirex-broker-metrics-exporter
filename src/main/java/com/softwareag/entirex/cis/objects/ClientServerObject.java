package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import java.math.*;

public class ClientServerObject
    extends AbstractServiceResponseObject
{
    private static final int O_USER_ID = 0;
    private static final int L_USER_ID = 32;

    private static final int O_P_USER_ID = 32;
    private static final int L_P_USER_ID = 28;

    private static final int O_P_USER_ID_CHAR = 60;
    private static final int L_P_USER_ID_CHAR = 28;

    private static final int O_TOKEN = 88;
    private static final int L_TOKEN = 32;

    private static final int O_CHARSET = 120;
    private static final int L_CHARSET = 2;

    private static final int O_ENDIAN = 122;
    private static final int L_ENDIAN = 2;

    private static final int O_STATUS = 124;
    private static final int L_STATUS = 2;

    private static final int O_RESERVED = 126;
    private static final int L_RESERVED = 2;

    private static final int O_WAIT_CONV_TYPE = 128;
    private static final int L_WAIT_CONV_TYPE = 16;

    private static final int O_WAIT_SERVER_CLASS = 144;
    private static final int L_WAIT_SERVER_CLASS = 32;

    private static final int O_WAIT_SERVER_NAME = 176;
    private static final int L_WAIT_SERVER_NAME = 32;

    private static final int O_WAIT_SERVICE = 208;
    private static final int L_WAIT_SERVICE = 32;

    private static final int O_CONV_ACT = 240;
    private static final int L_CONV_ACT = 4;

    private static final int O_SERVICE_ACT = 244;
    private static final int L_SERVICE_ACT = 4;

    private static final int O_LAST_ACTIVE = 248;
    private static final int L_LAST_ACTIVE = 4;

    private static final int O_NONACT = 252;
    private static final int L_NONACT = 4;

    private static final int O_WAIT_NEW = 256;
    private static final int L_WAIT_NEW = 4;

    private static final int O_NUM_WAIT_NEW = 260;
    private static final int L_NUM_WAIT_NEW = 4;

    private static final int O_WAIT_OLD = 264;
    private static final int L_WAIT_OLD = 4;

    private static final int O_NUM_WAIT_OLD = 268;
    private static final int L_NUM_WAIT_OLD = 4;

    private static final int O_SUM_CONV = 272;
    private static final int L_SUM_CONV = 4;

    private static final int O_LTOTALUOWS = 276;
    private static final int L_LTOTALUOWS = 4;

    private static final int L_CLIENT_SERVER_OBJECT = 276;

    public ClientServerObject()
    	{
    	}

    public ClientServerObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
    {
        super(abResponse, iOff, iLen);
    }

    public int getLength()
    {
        return L_CLIENT_SERVER_OBJECT;
    }

    public String getUserID()
    {
        return new String(abResponse, O_USER_ID + iOff, L_USER_ID).trim();
    }

    public byte[] getPUserID()
    {
        return Utils.getSubArray(abResponse, O_P_USER_ID + iOff, L_P_USER_ID);
    }

    public String getPUserIDChar()
    {
        return new String(abResponse, O_P_USER_ID_CHAR + iOff, L_P_USER_ID_CHAR).trim();
    }

    public String getToken()
    {
        return new String(abResponse, O_TOKEN + iOff, L_TOKEN).trim();
    }

    public int getCharSet()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CHARSET + iOff, L_CHARSET)).intValue();
    }

    public int getEndian()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_ENDIAN + iOff, L_ENDIAN)).intValue();
    }

    public int getStatus()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_STATUS + iOff, L_STATUS)).intValue();
    }

    public int getReserved()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_RESERVED + iOff, L_RESERVED)).intValue();
    }

    public String getWaitConvType()
    {
        return new String(abResponse, O_WAIT_CONV_TYPE + iOff, L_WAIT_CONV_TYPE).trim();
    }

    public String getWaitServerClass()
    {
        return new String(abResponse, O_WAIT_SERVER_CLASS + iOff, L_WAIT_SERVER_CLASS).trim();
    }

    public String getWaitServerName()
    {
        return new String(abResponse, O_WAIT_SERVER_NAME + iOff, L_WAIT_SERVER_NAME).trim();
    }

    public String getWaitService()
    {
        return new String(abResponse, O_WAIT_SERVICE + iOff, L_WAIT_SERVICE).trim();
    }

    public int getConvAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CONV_ACT + iOff, L_CONV_ACT)).intValue();
    }

    public int getServiceAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SERVICE_ACT + iOff, L_SERVICE_ACT)).intValue();
    }

    public int getLastActive()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LAST_ACTIVE + iOff, L_LAST_ACTIVE)).intValue();
    }

    public int getNonAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NONACT + iOff, L_NONACT)).intValue();
    }

    public int getWaitNew()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_WAIT_NEW + iOff, L_WAIT_NEW)).intValue();
    }

    public int getNumWaitNew()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_WAIT_NEW + iOff, L_NUM_WAIT_NEW)).intValue();
    }

    public int getWaitOld()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_WAIT_OLD + iOff, L_WAIT_OLD)).intValue();
    }

    public int getNumWaitOld()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_WAIT_OLD + iOff, L_NUM_WAIT_OLD)).intValue();
    }

    public int getSumConv()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SUM_CONV + iOff, L_SUM_CONV)).intValue();
    }

    public int getLTotalUOWS()
    {
        return (iLen >= L_CLIENT_SERVER_OBJECT + 4) ? new BigInteger(Utils.getSubArray(abResponse, O_LTOTALUOWS + iOff, L_LTOTALUOWS)).intValue() : -1;
    }

    public String toString()
    {
        return
            "[ClientServerObject=\n" +
            "   USER-ID          : " + getUserID() + "\n" +
            "   P-USER-ID        : " + getPUserID() + "\n" +
            "   P-USER-ID-CHAR   : " + getPUserIDChar() + "\n" +
            "   TOKEN            : " + getToken() + "\n" +
            "   CHAR-SET         : " + getCharSet() + "\n" +
            "   ENDIAN           : " + getEndian() + "\n" +
            "   STATUS           : " + getStatus() + "\n" +
            "   RESERVED         : " + getReserved() + "\n" +
            "   WAIT-CONV-TYPE   : " + getWaitConvType() + "\n" +
            "   WAIT-SERVER-CLASS: " + getWaitServerClass() + "\n" +
            "   WAIT-SERVER-NAME : " + getWaitServerName() + "\n" +
            "   WAIT-SERVICE     : " + getServiceAct() + "\n" +
            "   CONV-ACT         : " + getConvAct() + "\n" +
            "   SERVICE-ACT      : " + getServiceAct() + "\n" +
            "   LAST-ACTIVE      : " + getLastActive() + "\n" +
            "   NONACT           : " + getNonAct() + "\n" +
            "   WAIT-NEW         : " + getWaitNew() + "\n" +
            "   NUM-WAIT-NEW     : " + getNumWaitNew() + "\n" +
            "   WAIT-OLD         : " + getWaitOld() + "\n" +
            "   NUM-WAIT-OLD     : " + getNumWaitOld() + "\n" +
            "   SUM-CONV         : " + getSumConv() + "\n" +
            "   LTOTALUOWS       : " + getLTotalUOWS() + "\n" +
            "]";
    }
}	