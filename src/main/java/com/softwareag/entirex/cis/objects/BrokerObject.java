package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;

import java.math.*;
import java.util.StringTokenizer;

/*
 * https://docs.webmethods.io/on-premises/entirex/en/10.7.0/webhelp/aci/cisData.htm#cisData_infoReply_BROKER-OBJECT
 */
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

    // Starting CIS Interface Version 3 ...
    //CACCOUNTING 	        A3 	3 	NO 	Accounting not active YES Accounting active on UNIX and Windows nnn SMF Record number on z/OS
    private static final int L_CACCOUNTING = 3;
    private static final int O_CACCOUNTING = 120;

    //CAUTHDEFAULT 	        I1 	3 	Authorization Default: 0 NO 1 YES
    private static final int L_CAUTHDEFAULT = 1;
    private static final int O_CAUTHDEFAULT = O_CACCOUNTING + L_CACCOUNTING;

    //LSSLPORT 	            I4 	3 	Port number being used for SSL transport (UNIX and Windows only).
    private static final int L_LSSLPORT = 4;
    private static final int O_LSSLPORT = O_CAUTHDEFAULT + L_CAUTHDEFAULT;

    //NEW-UOW-MESSAGES 	    I1 	3 	New UOW messages: 0 NO 1 YES
    private static final int L_NEW_UOW_MESSAGES = 1;
    private static final int O_NEW_UOW_MESSAGES = O_LSSLPORT + L_LSSLPORT;
    
    //UNUSED2 	            I1 	3 	Unused.
    private static final int L_UNUSED2 = 1;
    private static final int O_UNUSED2 = O_NEW_UOW_MESSAGES + L_NEW_UOW_MESSAGES;
    
    //CPLATNAME 	        A32 	3 	Full platform name where Broker is running
    private static final int L_CPLATNAME = 32;
    private static final int O_CPLATNAME = O_UNUSED2 + L_UNUSED2 + 2;

    //CPSTORETYPE 	        A8 	3 	Persistent store type. It will be one of the following values: DIV Data-in-Virtual Persistent Store (z/OS only) FILE B-Tree Store (UNIX and Windows only, no longer supported) ADABAS Adabas Persistent Store (all platforms)
    private static final int L_CPSTORETYPE = 8;
    private static final int O_CPSTORETYPE = O_CPLATNAME + L_CPLATNAME;

    // Starting CIS Interface Version 4 ...
    //HIGHEST-API-VERSION 	I1 	4 	For example: 0x06.
    private static final int L_HIGHEST_API_VERSION = 1;
    private static final int O_HIGHEST_API_VERSION = O_CPSTORETYPE + L_CPSTORETYPE + 1;

    //HIGHEST-CIS-VERSION 	I1 	4 	For example: 0x06.
    private static final int L_HIGHEST_CIS_VERSION = 1;
    private static final int O_HIGHEST_CIS_VERSION = O_HIGHEST_API_VERSION + L_HIGHEST_API_VERSION;

    //PSTORE-CONNECTED 	    I1 	4 0 NO 1 YES
    private static final int L_PSTORE_CONNECTED = 1;
    private static final int O_PSTORE_CONNECTED = O_HIGHEST_CIS_VERSION + L_HIGHEST_CIS_VERSION;
    
    //ATTACH-MGRS-ACT 	    I4 	4 	Number of attach servers active.
    private static final int L_ATTACH_MGRS_ACT = 4;
    private static final int O_ATTACH_MGRS_ACT = O_PSTORE_CONNECTED + L_PSTORE_CONNECTED;
    
    //LUWSTAT-ADD-TIME 	    I4 	4 	Unit of work status additional lifetime.
    private static final int L_LUWSTAT_ADD_TIME = 4;
    private static final int O_LUWSTAT_ADD_TIME = O_ATTACH_MGRS_ACT + L_ATTACH_MGRS_ACT;
    
    //PRODUCT-VERSION 	    A16 	4 	Version, release, service pack, and patch level, e.g. 8.0.1.00.
    private static final int L_PRODUCT_VERSION = 16;
    private static final int O_PRODUCT_VERSION = O_LUWSTAT_ADD_TIME + L_LUWSTAT_ADD_TIME + 10*4;

    private static final int L_BROKER_OBJECT = O_PRODUCT_VERSION + L_PRODUCT_VERSION; // CIS 2: O_CDEFERRED + L_CDEFERRED;

    public static final InterfaceVersion IV = InterfaceVersion.VERSION_4; //Implemented Interface Version
    public static final ObjectType       OT = ObjectType.BROKER;
    
    public BrokerObject()
    	{
    	}

    public BrokerObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
    {
        super(abResponse, iOff, iLen);
    }

	public InterfaceVersion getInterfaceVersion()
	{
		return IV;
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
        return new BigInteger(Utils.getSubArray(abResponse, O_CSTORE + iOff, L_CSTORE)).intValue();
    }

    public int getLMaxUOWS()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LMAXUOWS + iOff, L_LMAXUOWS)).intValue();
    }

    public int getLMaxUOWMsg()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LMAXUOWMSG + iOff, L_LMAXUOWMSG)).intValue();
    }

    public int getCPStore()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CPSTORE + iOff, L_CPSTORE)).intValue();
    }

    public int getCUWStatP()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CUWSTATP + iOff, L_CUWSTATP)).intValue();
    }

    public int getLUWTime()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LUWTIME + iOff, L_LUWTIME)).intValue();
    }

    public int getLMaxDELCNT()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LMAXDELCNT + iOff, L_LMAXDELCNT)).intValue();
    }

    public int getLMaxMsgSize()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LMAXMSGSIZE + iOff, L_LMAXMSGSIZE)).intValue();
    }

    public int getCDeferred()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CDEFERRED + iOff, L_CDEFERRED)).intValue();
    }

    public int getLTotalUOWS()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_LTOTALUOWS + iOff, L_LTOTALUOWS)).intValue();
    }

    public int getHighestAPIVersion()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_HIGHEST_API_VERSION + iOff, L_HIGHEST_API_VERSION)).intValue();
    }

    public int getHighestCISVersion()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_HIGHEST_CIS_VERSION + iOff, L_HIGHEST_CIS_VERSION)).intValue();
    }

    public String getProductVersion()
    {
    	return new String( Utils.getSubArray(abResponse, O_PRODUCT_VERSION + iOff, L_PRODUCT_VERSION) ).trim();
    }

    public String getPlatformName()
    {
    	return new String( Utils.getSubArray(abResponse, O_CPLATNAME + iOff, L_CPLATNAME) ).trim();
    }

    public String getPStoreType()
    {
    	return new String( Utils.getSubArray(abResponse, O_CPSTORETYPE + iOff, L_CPSTORETYPE) ).trim();
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
            "   PLATNAME      : " + getPlatformName() + "\n" +
            "   PSTORETYPE    : " + getPStoreType() + "\n" +
            "   API_VERSION   : " + getHighestAPIVersion() + "\n" +
            "   CIS_VERSION   : " + getHighestCISVersion() + "\n" +
            "   PRODUCT_VERS  : " + getProductVersion() + "\n" +
            "]";
    }

	/**
	 * Version.Release.Service-Pack.Patch-Level
	 * "11.1.0.00" ->
	 *  11010000
	 *  1.101E9
	 */
	public double getProductVersionAsNumber() {
		String version = getProductVersion();
		double    back = 0;
		if ( version == null || version.length() == 0 )
			return back;

		StringTokenizer st = new StringTokenizer( version, "." );
		int          loops = 3;
		while ( st.hasMoreTokens() ) {
			Integer i = Integer.parseInt( st.nextToken() );
			back = back + ( Double.valueOf( Math.pow( 100, loops-- ) ) * i.doubleValue() ); 
		}
		return back;
	}
}