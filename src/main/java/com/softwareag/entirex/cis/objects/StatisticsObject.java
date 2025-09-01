package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;

import java.math.*;

/*
 * https://docs.webmethods.io/on-premises/entirex/en/10.7.0/webhelp/aci/cisData.htm#cisData_infoReply_STATISTICS-OBJECT
 */
public class StatisticsObject
    extends AbstractServiceResponseObject
{
	//NUM-SERVICE 	I4 	7 	Number of services defined (see NUM-SERVER).
	private static final int L_NUM_SERVICE = 4;
	private static final int O_NUM_SERVICE = 0;
	//SERVICE-ACT 	I4 	7 	Number of services active.
	private static final int L_SERVICE_ACT = 4;
	private static final int O_SERVICE_ACT = O_NUM_SERVICE + L_NUM_SERVICE;
	//NUM-CLIENT 	I4 	7 	Number of clients defined (see NUM-CLIENT).
	private static final int L_NUM_CLIENT = 4;
	private static final int O_NUM_CLIENT = O_SERVICE_ACT + L_SERVICE_ACT;
	//CLIENT-ACT 	I4 	7 	Number of clients active.
	private static final int L_CLIENT_ACT = 4;
	private static final int O_CLIENT_ACT = O_NUM_CLIENT + L_NUM_CLIENT;
	//CLIENT-HIGH 	I4 	7 	Highest number of clients active since Broker started.
	private static final int L_CLIENT_HIGH = 4;
	private static final int O_CLIENT_HIGH = O_CLIENT_ACT + L_CLIENT_ACT;
	//NUM-SERVER 	I4 	7 	Number of servers (see NUM-SERVER).
	private static final int L_NUM_SERVER = 4;
	private static final int O_NUM_SERVER = O_CLIENT_HIGH + L_CLIENT_HIGH;
	//SERVER-ACT 	I4 	7 	Number of servers active. This counter also includes the active Attach Server instances.
	private static final int L_SERVER_ACT = 4;
	private static final int O_SERVER_ACT = O_NUM_SERVER + L_NUM_SERVER;
	//SERVER-HIGH 	I4 	7 	Highest number of servers active since Broker started.
	private static final int L_SERVER_HIGH = 4;
	private static final int O_SERVER_HIGH = O_SERVER_ACT + L_SERVER_ACT;
	//NUM-CONV 	I4 	7 	Number of conversations defined (see NUM-CONVERSATION).
	private static final int L_NUM_CONV = 4;
	private static final int O_NUM_CONV = O_SERVER_HIGH + L_SERVER_HIGH;
	//CONV-ACT 	I4 	7 	Number of conversations active.
	private static final int L_CONV_ACT = 4;
	private static final int O_CONV_ACT = O_NUM_CONV + L_NUM_CONV;
	//CONV-HIGH 	I4 	7 	Highest number of conversations active since Broker started.
	private static final int L_CONV_HIGH = 4;
	private static final int O_CONV_HIGH = O_CONV_ACT + L_CONV_ACT;
	//NUM-LONG 	I4 	7 	Number of long buffers defined (see NUM-LONG-BUFFER).
	private static final int L_NUM_LONG = 4;
	private static final int O_NUM_LONG = O_CONV_HIGH + L_CONV_HIGH;
	//LONG-ACT 	I4 	7 	Number of long buffers active.
	private static final int L_LONG_ACT = 4;
	private static final int O_LONG_ACT = O_NUM_LONG + L_NUM_LONG;
	//LONG-HIGH 	I4 	7 	Highest number of long buffers active since Broker started.
	private static final int L_LONG_HIGH = 4;
	private static final int O_LONG_HIGH = O_LONG_ACT + L_LONG_ACT;
	//NUM-SHORT 	I4 	7 	Number of short buffers defined (see NUM-SHORT-BUFFER).
	private static final int L_NUM_SHORT = 4;
	private static final int O_NUM_SHORT = O_LONG_HIGH + L_LONG_HIGH;
	//SHORT-ACT 	I4 	7 	Number of short buffers active.
	private static final int L_SHORT_ACT = 4;
	private static final int O_SHORT_ACT = O_NUM_SHORT + L_NUM_SHORT;
	//SHORT-HIGH 	I4 	7 	Highest number of short buffers active since Broker started.
	private static final int L_SHORT_HIGH = 4;
	private static final int O_SHORT_HIGH = O_SHORT_ACT + L_SHORT_ACT;

    private static final int L_OBJECT = O_SHORT_HIGH + L_SHORT_HIGH;

    public static final InterfaceVersion IV = InterfaceVersion.VERSION_7; //Implemented Interface Version
    public static final ObjectType       OT = ObjectType.STATISTICS;

    public StatisticsObject()
	    {
    	}

    public StatisticsObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
    {
        super(abResponse, iOff, iLen);
    }

    public int getLength()
    {
        return L_OBJECT;
    }

    public String toString()
    {
        return
            "[StatisticsObject=\n" +
            "   ClientsActive      : " + getClientsActive() + "\n" +
            "   ServersActive      : " + getServersActive() + "\n" + 
            "   ConversationsActive: " + getConversationsActive() + "\n" +
            "]";
    }

    public int getClientsActive() {
    	return new BigInteger( Utils.getSubArray( abResponse, O_CLIENT_ACT + iOff, L_CLIENT_ACT ) ).intValue();
    }

    public int getServersActive() {
    	return new BigInteger( Utils.getSubArray( abResponse, O_SERVER_ACT + iOff, L_SERVER_ACT ) ).intValue();
    }

    public int getConversationsActive() {
    	return new BigInteger( Utils.getSubArray( abResponse, O_CONV_ACT + iOff, L_CONV_ACT ) ).intValue();
    }
}