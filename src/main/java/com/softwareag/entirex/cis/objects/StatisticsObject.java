package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;

import java.math.*;

public class StatisticsObject
    extends AbstractServiceResponseObject
{
	/*
	NUM-SERVICE 	I4 	7 	Number of services defined (see NUM-SERVER).
	SERVICE-ACT 	I4 	7 	Number of services active.
	NUM-CLIENT 	I4 	7 	Number of clients defined (see NUM-CLIENT).
	CLIENT-ACT 	I4 	7 	Number of clients active.
	CLIENT-HIGH 	I4 	7 	Highest number of clients active since Broker started.
	NUM-SERVER 	I4 	7 	Number of servers (see NUM-SERVER).
	SERVER-ACT 	I4 	7 	Number of servers active. This counter also includes the active Attach Server instances.
	SERVER-HIGH 	I4 	7 	Highest number of servers active since Broker started.
	NUM-CONV 	I4 	7 	Number of conversations defined (see NUM-CONVERSATION).
	CONV-ACT 	I4 	7 	Number of conversations active.
	CONV-HIGH 	I4 	7 	Highest number of conversations active since Broker started.
	NUM-LONG 	I4 	7 	Number of long buffers defined (see NUM-LONG-BUFFER).
	LONG-ACT 	I4 	7 	Number of long buffers active.
	LONG-HIGH 	I4 	7 	Highest number of long buffers active since Broker started.
	NUM-SHORT 	I4 	7 	Number of short buffers defined (see NUM-SHORT-BUFFER).
	SHORT-ACT 	I4 	7 	Number of short buffers active.
	SHORT-HIGH 	I4 	7 	Highest number of short buffers active since Broker started. 
	*/

    private static final int L_OBJECT = 16*4;

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
            "]";
    }
}