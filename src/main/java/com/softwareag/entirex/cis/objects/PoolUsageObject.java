package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;
import java.math.*;

public class PoolUsageObject
    extends AbstractServiceResponseObject
{
	/*
	TOTAL-NUM-POOLS 	I4 	7 	Number of pools currently allocated.
	TOTAL-STORAGE-ALLOCATED 	I4 	7 	Size of allocated storage in bytes.
	ACCOUNTING-NUM-POOLS 	I4 	7 	ACCOUNTING: Number of pools.
	ACCOUNTING-SIZE-ALL-POOLS 	I4 	7 	ACCOUNTING: Size of all pools in bytes.
	ACCOUNTING-SIZE-ONE-POOL 	I4 	7 	ACCOUNTING: Size of one pool in bytes.
	BLACKLIST-NUM-POOLS 	I4 	7 	BLACKLIST: Number of pools.
	BLACKLIST-SIZE-ALL-POOLS 	I4 	7 	BLACKLIST: Size of all pools in bytes.
	BLACKLIST-SIZE-ONE-POOL 	I4 	7 	BLACKLIST: Size of one pool in bytes.
	BROKER-TO-BROKER-NUM-POOLS 	I4 	7 	BROKER-TO-BROKER: Number of pools.
	BROKER-TO-BROKER-SIZE-ALL-POOLS 	I4 	7 	BROKER-TO-BROKER: Size of all pools in bytes.
	BROKER-TO-BROKER-SIZE-ONE-POOL 	I4 	7 	BROKER-TO-BROKER: Size of one pool in bytes.
	COM-BUFFER-NUM-POOLS 	I4 	7 	COM-BUFFER: Number of pools.
	COM-BUFFER-SIZE-ALL-POOLS 	I4 	7 	COM-BUFFER: Size of all pools in bytes.
	COM-BUFFER-SIZE-ONE-POOL 	I4 	7 	COM-BUFFER: Size of one pool in bytes.
	CMDLOG-NUM-POOLS 	I4 	7 	CMDLOG: Number of pools.
	CMDLOG-SIZE-ALL-POOLS 	I4 	7 	CMDLOG: Size of all pools in bytes.
	CMDLOG-SIZE-ONE-POOL 	I4 	7 	CMDLOG: Size of one pool in bytes.
	CONNECTION-NUM-POOLS 	I4 	7 	CONNECTION: Number of pools.
	CONNECTION-SIZE-ALL-POOLS 	I4 	7 	CONNECTION: Size of all pools in bytes.
	CONNECTION-SIZE-ONE-POOL 	I4 	7 	CONNECTION: Size of one pool in bytes.
	CONVERSATION-NUM-POOLS 	I4 	7 	CONVERSATION: Number of pools.
	CONVERSATION-SIZE-ALL-POOLS 	I4 	7 	CONVERSATION: Size of all pools in bytes.
	CONVERSATION-SIZE-ONE-POOL 	I4 	7 	CONVERSATION: Size of one pool in bytes.
	HEAP-NUM-POOLS 	I4 	7 	HEAP: Number of pools.
	HEAP-SIZE-ALL-POOLS 	I4 	7 	HEAP: Size of all pools in bytes.
	HEAP-SIZE-ONE-POOL 	I4 	7 	HEAP: Size of one pool in bytes.
	MSG-BUFFER-LONG-NUM-POOLS 	I4 	7 	MSG-BUFFER-LONG: Number of pools.
	MSG-BUFFER-LONG-SIZE-ALL-POOLS 	I4 	7 	MSG-BUFFER-LONG: Size of all pools in bytes.
	MSG-BUFFER-LONG-SIZE-ONE-POOL 	I4 	7 	MSG-BUFFER-LONG: Size of one pool in bytes.
	MSG-BUFFER-SHORT-NUM-POOLS 	I4 	7 	MSG-BUFFER-SHORT: Number of pools.
	MSG-BUFFER-SHORT-SIZE-ALL-POOLS 	I4 	7 	MSG-BUFFER-SHORT: Size of all pools in bytes.
	MSG-BUFFER-SHORT-SIZE-ONE-POOL 	I4 	7 	MSG-BUFFER-SHORT: Size of one pool in bytes.
	PARTICIPANT-NUM-POOLS 	I4 	7 	PARTICIPANT: Number of pools.
	PARTICIPANT-SIZE-ALL-POOLS 	I4 	7 	PARTICIPANT: Size of all pools in bytes.
	PARTICIPANT-SIZE-ONE-POOL 	I4 	7 	PARTICIPANT: Size of one pool in bytes.
	PARTICIPANT-EXT-NUM-POOLS 	I4 	7 	PARTICIPANT-EXT: Number of pools.
	PARTICIPANT-EXT-SIZE-ALL-POOLS 	I4 	7 	PARTICIPANT-EXT: Size of all pools in bytes.
	PARTICIPANT-EXT-SIZE-ONE-POOL 	I4 	7 	PARTICIPANT-EXT: Size of one pool in bytes.
	PROXY-QUEUE-NUM-POOLS 	I4 	7 	PROXY-QUEUE: Number of pools.
	PROXY-QUEUE-SIZE-ALL-POOLS 	I4 	7 	PROXY-QUEUE: Size of all pools in bytes.
	PROXY-QUEUE-SIZE-ONE-POOL 	I4 	7 	PROXY-QUEUE: Size of one pool in bytes.
	SERVICE-ATTRIBUTES-NUM-POOLS 	I4 	7 	SERVICE-ATTRIBUTES: Number of pools.
	SERVICE-ATTRIBUTES-SIZE-ALL-POOLS 	I4 	7 	SERVICE-ATTRIBUTES: Size of all pools in bytes.
	SERVICE-ATTRIBUTES-SIZE-ONE-POOL 	I4 	7 	SERVICE-ATTRIBUTES: Size of one pool in bytes.
	SERVICE-NUM-POOLS 	I4 	7 	SERVICE: Number of pools.
	SERVICE-SIZE-ALL-POOLS 	I4 	7 	SERVICE: Size of all pools in bytes.
	SERVICE-SIZE-ONE-POOL 	I4 	7 	SERVICE: Size of one pool in bytes.
	SERVICE-EXT-NUM-POOLS 	I4 	7 	SERVICE-EXT: Number of pools.
	SERVICE-EXT-SIZE-ALL-POOLS 	I4 	7 	SERVICE-EXT: Size of all pools in bytes.
	SERVICE-EXT-SIZE-ONE-POOL 	I4 	7 	SERVICE-EXT: Size of one pool in bytes.
	TIMEOUT-QUEUE-NUM-POOLS 	I4 	7 	TIMEOUT-QUEUE: Number of pools.
	TIMEOUT-QUEUE-SIZE-ALL-POOLS 	I4 	7 	TIMEOUT-QUEUE: Size of all pools in bytes.
	TIMEOUT-QUEUE-SIZE-ONE-POOL 	I4 	7 	TIMEOUT-QUEUE: Size of one pool in bytes.
	TRANSLATION-NUM-POOLS 	I4 	7 	TRANSLATION: Number of pools.
	TRANSLATION-SIZE-ALL-POOLS 	I4 	7 	TRANSLATION: Size of all pools in bytes.
	TRANSLATION-SIZE-ONE-POOL 	I4 	7 	TRANSLATION: Size of one pool in bytes.
	UOW-NUM-POOLS 	I4 	7 	UOW: Number of pools.
	UOW-SIZE-ALL-POOLS 	I4 	7 	UOW: Size of all pools in bytes.
	UOW-SIZE-ONE-POOL 	I4 	7 	UOW: Size of one pool in bytes.
	WORK-QUEUE-NUM-POOLS 	I4 	7 	WORK-QUEUE: Number of pools.
	WORK-QUEUE-SIZE-ALL-POOLS 	I4 	7 	WORK-QUEUE: Size of all pools in bytes.
	WORK-QUEUE-SIZE-ONE-POOL 	I4 	7 	WORK-QUEUE: Size of one pool in bytes. 
	 */
    private static final int L_OBJECT = 62 * 4;

    public static final InterfaceVersion IV = InterfaceVersion.VERSION_7; //Implemented Interface Version
    public static final ObjectType       OT = ObjectType.POOL_USAGE;

    public PoolUsageObject()
	    {
    	}

    public PoolUsageObject(byte[] abResponse, int iOff, int iLen)
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
            "[PoolUsage=\n" +
            "]";
    }
}