package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;

import java.math.*;

public class BrokerObject
    extends AbstractServiceResponseObject
{
    private static final int O_PLATFORM = 0;
    private static final int L_PLATFORM = 8;

    private static final int O_RUNTIME = 8;
    private static final int L_RUNTIME = 4;

    private static final int O_NUM_WORKER_ACT = 12;
    private static final int L_NUM_WORKER_ACT = 4;

    private static final int O_NUM_LONG = 16;
    private static final int L_NUM_LONG = 4;

    private static final int O_LONG_ACT = 20;
    private static final int L_LONG_ACT = 4;

    private static final int O_LONG_HIGH = 24;
    private static final int L_LONG_HIGH = 4;

    private static final int O_NUM_SHORT = 28;
    private static final int L_NUM_SHORT = 4;

    private static final int O_SHORT_ACT = 32;
    private static final int L_SHORT_ACT = 4;

    private static final int O_SHORT_HIGH = 36;
    private static final int L_SHORT_HIGH = 4;

    private static final int O_LONG_SIZE = 40;
    private static final int L_LONG_SIZE = 4;

    private static final int O_SHORT_SIZE = 44;
    private static final int L_SHORT_SIZE = 4;

    private static final int O_NUM_SERVICE = 48;
    private static final int L_NUM_SERVICE = 4;

    private static final int O_SERVICE_ACT = 52;
    private static final int L_SERVICE_ACT = 4;

    private static final int O_NUM_SERVER = 56;
    private static final int L_NUM_SERVER = 4;

    private static final int O_SERVER_ACT = 60;
    private static final int L_SERVER_ACT = 4;

    private static final int O_SERVER_HIGH = 64;
    private static final int L_SERVER_HIGH = 4;

    private static final int O_NUM_CLIENT = 68;
    private static final int L_NUM_CLIENT = 4;

    private static final int O_CLIENT_ACT = 72;
    private static final int L_CLIENT_ACT = 4;

    private static final int O_CLIENT_HIGH = 76;
    private static final int L_CLIENT_HIGH = 4;

    private static final int O_NUM_CONV = 80;
    private static final int L_NUM_CONV = 4;

    private static final int O_CONV_HIGH = 84;
    private static final int L_CONV_HIGH = 4;

    private static final int O_TRACE_LEVEL = 88;
    private static final int L_TRACE_LEVEL = 2;

    private static final int O_RESERVE = 90;
    private static final int L_RESERVE = 2;

    // ONLY VERSION 2
    private static final int O_LMAXUOWS = 92;
    private static final int L_LMAXUOWS = 4;

    private static final int O_LMAXUOWMSG = 96;
    private static final int L_LMAXUOWMSG = 4;

    private static final int O_LUWTIME = 100;
    private static final int L_LUWTIME = 4;

    private static final int O_LMAXDELCNT = 104;
    private static final int L_LMAXDELCNT = 4;

    private static final int O_LMAXMSGSIZE = 108;
    private static final int L_LMAXMSGSIZE = 4;

    private static final int O_LTOTALUOWS = 112;
    private static final int L_LTOTALUOWS = 4;

    private static final int O_CSTORE = 116;
    private static final int L_CSTORE = 1;

    private static final int O_CPSTORE = 117;
    private static final int L_CPSTORE = 1;

    private static final int O_CUWSTATP = 118;
    private static final int L_CUWSTATP = 1;

    private static final int O_CDEFERRED = 119;
    private static final int L_CDEFERRED = 1;

    private static final int L_BROKER_OBJECT = O_CDEFERRED + L_CDEFERRED;

    public static final InterfaceVersion IV = InterfaceVersion.VERSION_2; //Implemented Interface Version
    public static final ObjectType       OT = ObjectType.BROKER;
    
    public BrokerObject()
    	{
    	}

    public BrokerObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
    {
        super(abResponse, iOff, iLen);
    }

    public int getLength()
    {
        return L_BROKER_OBJECT;
    }

    public String getPlatform()
    {
         return new String(abResponse, O_PLATFORM + iOff, L_PLATFORM).trim();
    }

    public int getRuntime()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_RUNTIME + iOff, L_RUNTIME)).intValue();
    }

    public int getNumWorkerAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_WORKER_ACT + iOff, L_NUM_WORKER_ACT)).intValue();
    }

    public int getNumLong()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_LONG + iOff, L_NUM_LONG)).intValue();
    }

    public int getLongAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LONG_ACT + iOff, L_LONG_ACT)).intValue();
    }

    public int getLongHigh()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LONG_HIGH + iOff, L_LONG_HIGH)).intValue();
    }

    public int getNumShort()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_SHORT + iOff, L_NUM_SHORT)).intValue();
    }

    public int getShortAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SHORT_ACT + iOff, L_SHORT_ACT)).intValue();
    }

    public int getShortHigh()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SHORT_HIGH + iOff, L_SHORT_HIGH)).intValue();
    }

    public int getLongSize()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LONG_SIZE + iOff, L_LONG_SIZE)).intValue();
    }

    public int getShortSize()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SHORT_SIZE + iOff, L_SHORT_SIZE)).intValue();
    }

    public int getNumService()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_SERVICE + iOff, L_NUM_SERVICE)).intValue();
    }

    public int getServiceAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SERVICE_ACT + iOff, L_SERVICE_ACT)).intValue();
    }

    public int getNumServer()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_SERVER + iOff, L_NUM_SERVER)).intValue();
    }

    public int getServerAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SERVER_ACT + iOff, L_SERVER_ACT)).intValue();
    }

    public int getServerHigh()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_SERVER_HIGH + iOff, L_SERVER_HIGH)).intValue();
    }

    public int getNumClient()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_CLIENT + iOff, L_NUM_CLIENT)).intValue();
    }

    public int getClientAct()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CLIENT_ACT + iOff, L_CLIENT_ACT)).intValue();
    }

    public int getClientHigh()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CLIENT_HIGH + iOff, L_CLIENT_HIGH)).intValue();
    }

    public int getNumConv()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_NUM_CONV + iOff, L_NUM_CONV)).intValue();
    }

    public int getConvHigh()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CONV_HIGH + iOff, L_CONV_HIGH)).intValue();
    }

    public int getTraceLevel()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_TRACE_LEVEL + iOff, L_TRACE_LEVEL)).intValue();
    }

    public int getReserve()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_RESERVE + iOff, L_RESERVE)).intValue();
    }

    public int getCStore()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_CSTORE + iOff, L_CSTORE)).intValue() : -1;
    }

    public int getLMaxUOWS()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_LMAXUOWS + iOff, L_LMAXUOWS)).intValue() : -1;
    }

    public int getLMaxUOWMsg()
    {
        return (iLen >= L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_LMAXUOWMSG + iOff, L_LMAXUOWMSG)).intValue() : -1;
    }

    public int getCPStore()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_CPSTORE + iOff, L_CPSTORE)).intValue() : -1;
    }

    public int getCUWStatP()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_CUWSTATP + iOff, L_CUWSTATP)).intValue() : -1;
    }

    public int getLUWTime()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_LUWTIME + iOff, L_LUWTIME)).intValue() : -1;
    }

    public int getLMaxDELCNT()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_LMAXDELCNT + iOff, L_LMAXDELCNT)).intValue() : -1;
    }

    public int getLMaxMsgSize()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_LMAXMSGSIZE + iOff, L_LMAXMSGSIZE)).intValue() : -1;
    }

    public int getCDeferred()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_CDEFERRED + iOff, L_CDEFERRED)).intValue() : -1;
    }

    public int getLTotalUOWS()
    {
        return (iLen > L_BROKER_OBJECT) ? new BigInteger(Utils.getSubArray(abResponse, O_LTOTALUOWS + iOff, L_LTOTALUOWS)).intValue() : -1;
    }

    public String toString()
    {
        return
            "[BrokerObject=\n" +
            "   PLATFORM      : " + getPlatform() + "\n" +
            "   RUNTIME       : " + getRuntime() + "\n" +
            "   NUM_WORKER_ACT: " + getNumWorkerAct() + "\n" +
            "   NUM_LONG      : " + getNumLong() + "\n" +
            "   LONG_ACT      : " + getLongAct() + "\n" +
            "   LONG_HIGH     : " + getLongHigh() + "\n" +
            "   NUM_SHORT     : " + getNumShort() + "\n" +
            "   SHORT_ACT     : " + getShortAct() + "\n" +
            "   SHORT_HIGH    : " + getShortHigh() + "\n" +
            "   LONG_SIZE     : " + getLongSize() + "\n" +
            "   SHORT_SIZE    : " + getShortSize() + "\n" +
            "   NUM_SERVICE   : " + getNumService() + "\n" +
            "   SERVICE_ACT   : " + getServiceAct() + "\n" +
            "   NUM_SERVER    : " + getNumServer() + "\n" +
            "   SERVER_ACT    : " + getServerAct() + "\n" +
            "   SERVER_HIGH   : " + getServerHigh() + "\n" +
            "   NUM_CLIENT    : " + getNumClient() + "\n" +
            "   CLIENT_ACT    : " + getClientAct() + "\n" +
            "   CLIENT_HIGH   : " + getClientHigh() + "\n" +
            "   NUM_CONV      : " + getNumConv() + "\n" +
            "   CONV_HIGH     : " + getConvHigh() + "\n" +
            "   TRACE_LEVEL   : " + getTraceLevel() + "\n" +
            "   RESERVE       : " + getReserve() + "\n" +
            "   CSTORE        : " + getCStore() + "\n" +
            "   LMAXUOWS      : " + getLMaxUOWS() + "\n" +
            "   LMAXUOWMSG    : " + getLMaxUOWMsg() + "\n" +
            "   CPSTORE       : " + getCPStore() + "\n" +
            "   CUWSTATP      : " + getCUWStatP() + "\n" +
            "   LUWTIME       : " + getLUWTime() + "\n" +
            "   LMAXDELCNT    : " + getLMaxDELCNT() + "\n" +
            "   LMAXMSGSIZE   : " + getLMaxMsgSize() + "\n" +
            "   CDEFERRED     : " + getCDeferred() + "\n" +
            "   LTOTALUOWS    : " + getLTotalUOWS() + "\n" +
            "]";
    }
}