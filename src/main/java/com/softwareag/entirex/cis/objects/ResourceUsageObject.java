package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import com.softwareag.entirex.cis.params.InterfaceVersion;
import com.softwareag.entirex.cis.params.ObjectType;
import java.math.*;

/*
 * https://docs.webmethods.io/on-premises/entirex/en/10.7.0/webhelp/aci/cisData.htm#cisData_infoReply_RESOURCE-USAGE-OBJECT
 * CIS Interface Version: 7
 */
public class ResourceUsageObject
    extends AbstractServiceResponseObject
    {

    private static final int L_TOTAL_STORAGE_ALLOCATED             = 4; //Size of allocated storage in bytes.
    private static final int O_TOTAL_STORAGE_ALLOCATED             = 0;
    private static final int L_TOTAL_STORAGE_ALLOCATED_HIGH        = 4; //Highest size of allocated storage in bytes since Broker started.
    private static final int O_TOTAL_STORAGE_ALLOCATED_HIGH        = O_TOTAL_STORAGE_ALLOCATED + L_TOTAL_STORAGE_ALLOCATED_HIGH;
    private static final int L_TOTAL_STORAGE_LIMIT                 = 4; //Maximum of storage that can be allocated (broker attribute MAX_MEMORY).
    private static final int O_TOTAL_STORAGE_LIMIT                 = O_TOTAL_STORAGE_ALLOCATED_HIGH + L_TOTAL_STORAGE_LIMIT;
    private static final int L_ACCOUNTING_BUFFERS_ALLOCATED        = 4; //ACCOUNTING: Number of buffers allocated.
    private static final int L_ACCOUNTING_BUFFERS_FREE             = 4; //ACCOUNTING: Number of buffers free.
    private static final int L_ACCOUNTING_BUFFERS_USED             = 4; //ACCOUNTING: Number of buffers used.
    private static final int L_BLACKLIST_ENTRIES_ALLOCATED         = 4; //BLACKLIST: Number of entries allocated.
    private static final int L_BLACKLIST_ENTRIES_FREE              = 4; //BLACKLIST: Number of entries free.
    private static final int L_BLACKLIST_ENTRIES_USED              = 4; //BLACKLIST: Number of entries used.
    private static final int L_BROKER_TO_BROKER_ENTRIES_ALLOCATED  = 4; //BROKER_TO_BROKER: Number of entries allocated.
    private static final int L_BROKER_TO_BROKER_ENTRIES_FREE       = 4; //BROKER_TO_BROKER: Number of entries free.
    private static final int L_BROKER_TO_BROKER_ENTRIES_USED       = 4; //BROKER_TO_BROKER: Number of entries used.
    private static final int L_COM_BUFFERS_ALLOCATED               = 4; //COM_BUFFER: Number of buffers allocated.
    private static final int L_COM_BUFFERS_FREE                    = 4; //COM_BUFFER: Number of buffers free.
    private static final int L_COM_BUFFERS_USED                    = 4; //COM_BUFFER: Number of buffers used.
    private static final int L_CMDLOG_FILTER_ENTRIES_ALLOCATED     = 4; //CMDLOG_FILTER: Number of entries allocated.
    private static final int L_CMDLOG_FILTER_ENTRIES_FREE          = 4; //CMDLOG_FILTER: Number of entries free.
    private static final int L_CMDLOG_FILTER_ENTRIES_USED          = 4; //CMDLOG_FILTER: Number of entries used.
    private static final int L_CONNECTION_ENTRIES_ALLOCATED        = 4; //CONNECTION: Number of entries allocated.
    private static final int L_CONNECTION_ENTRIES_FREE             = 4; //CONNECTION: Number of entries free.
    private static final int L_CONNECTION_ENTRIES_USED             = 4; //CONNECTION: Number of entries used.
    private static final int L_CONVERSATION_ENTRIES_ALLOCATED      = 4; //CONVERSATION: Number of entries allocated.
    private static final int L_CONVERSATION_ENTRIES_FREE           = 4; //CONVERSATION: Number of entries free.
    private static final int L_CONVERSATION_ENTRIES_USED           = 4; //CONVERSATION: Number of entries used.
    private static final int L_HEAP_BYTES_ALLOCATED                = 4; //HEAP: Number of bytes allocated.
    private static final int L_HEAP_BYTES_FREE                     = 4; //HEAP: Number of bytes free.
    private static final int L_HEAP_BYTES_USED                     = 4; //HEAP: Number of bytes used.
    private static final int L_MSG_BUFFER_LONG_ALLOCATED           = 4; //MSG_BUFFER_LONG: Number of buffers allocated.
    private static final int L_MSG_BUFFER_LONG_FREE                = 4; //MSG_BUFFER_LONG: Number of buffers free.
    private static final int L_MSG_BUFFER_LONG_USED                = 4; //MSG_BUFFER_LONG: Number of buffers used.
    private static final int L_MSG_BUFFER_SHORT_ALLOCATED          = 4; //MSG_BUFFER_SHORT: Number of buffers allocated.
    private static final int L_MSG_BUFFER_SHORT_FREE               = 4; //MSG_BUFFER_SHORT: Number of buffers free.
    private static final int L_MSG_BUFFER_SHORT_USED               = 4; //MSG_BUFFER_SHORT: Number of buffers used.
    private static final int L_PARTICIPANT_ENTRIES_ALLOCATED       = 4; //PARTICIPANT: Number of entries allocated.
    private static final int L_PARTICIPANT_ENTRIES_FREE            = 4; //PARTICIPANT: Number of entries free.
    private static final int L_PARTICIPANT_ENTRIES_USED            = 4; //PARTICIPANT: Number of entries used.
    private static final int L_PARTICIPANT_EXT_ENTRIES_ALLOCATED   = 4; //PARTICIPANT_EXT: Number of entries allocated.
    private static final int L_PARTICIPANT_EXT_ENTRIES_FREE        = 4; //PARTICIPANT_EXT: Number of entries free.
    private static final int L_PARTICIPANT_EXT_ENTRIES_USED        = 4; //PARTICIPANT_EXT: Number of entries used.
    private static final int L_PROXY_QUEUE_ENTRIES_ALLOCATED       = 4; //PROXY_QUEUE: Number of entries allocated.
    private static final int L_PROXY_QUEUE_ENTRIES_FREE            = 4; //PROXY_QUEUE: Number of entries free.
    private static final int L_PROXY_QUEUE_ENTRIES_USED            = 4; //PROXY_QUEUE: Number of entries used.
    private static final int L_SERVICE_ATTRIBUTE_ENTRIES_ALLOCATED = 4; //SERVICE_ATTRIBUTE: Number of entries allocated.
    private static final int L_SERVICE_ATTRIBUTE_ENTRIES_FREE      = 4; //SERVICE_ATTRIBUTE: Number of entries free.
    private static final int L_SERVICE_ATTRIBUTE_ENTRIES_USED      = 4; //SERVICE_ATTRIBUTE: Number of entries used.
    private static final int L_SERVICE_ENTRIES_ALLOCATED           = 4; //SERVICE: Number of entries allocated.
    private static final int L_SERVICE_ENTRIES_FREE                = 4; //SERVICE: Number of entries free.
    private static final int L_SERVICE_ENTRIES_USED                = 4; //SERVICE: Number of entries used.
    private static final int L_SERVICE_EXT_ENTRIES_ALLOCATED       = 4; //SERVICE_EXT: Number of entries allocated.
    private static final int L_SERVICE_EXT_ENTRIES_FREE            = 4; //SERVICE_EXT: Number of entries free.
    private static final int L_SERVICE_EXT_ENTRIES_USED            = 4; //SERVICE_EXT: Number of entries used.
    private static final int L_TIMEOUT_QUEUE_ENTRIES_ALLOCATED     = 4; //TIMEOUT_QUEUE: Number of entries allocated.
    private static final int L_TIMEOUT_QUEUE_ENTRIES_FREE          = 4; //TIMEOUT_QUEUE: Number of entries free.
    private static final int L_TIMEOUT_QUEUE_ENTRIES_USED          = 4; //TIMEOUT_QUEUE: Number of entries used.
    private static final int L_TRANSLATION_ENTRIES_ALLOCATED       = 4; //TRANSLATION: Number of entries allocated.
    private static final int L_TRANSLATION_ENTRIES_FREE            = 4; //TRANSLATION: Number of entries free.
    private static final int L_TRANSLATION_ENTRIES_USED            = 4; //TRANSLATION: Number of entries used.
    private static final int L_UOW_ENTRIES_ALLOCATED               = 4; //UOW: Number of entries allocated.
    private static final int L_UOW_ENTRIES_FREE                    = 4; //UOW: Number of entries free.
    private static final int L_UOW_ENTRIES_USED                    = 4; //UOW: Number of entries used.
    private static final int L_WORK_QUEUE_ENTRIES_ALLOCATED        = 4; //WORK_QUEUE: Number of entries allocated.
    private static final int L_WORK_QUEUE_ENTRIES_FREE             = 4; //WORK_QUEUE: Number of entries free.
    private static final int L_WORK_QUEUE_ENTRIES_USED             = 4; //WORK_QUEUE: Number of entries used. 

    private static final int L_OBJECT = 66 * 4;

    public static final InterfaceVersion IV = InterfaceVersion.VERSION_7; //Implemented Interface Version
    public static final ObjectType       OT = ObjectType.RESOURCE_USAGE;

    public ResourceUsageObject()
	    {
    	}

    public ResourceUsageObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
    {
        super(abResponse, iOff, iLen);
    }

    public int getLength()
    {
        return L_OBJECT;
    }

    public int getTotalStorageAllocated()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_TOTAL_STORAGE_ALLOCATED + iOff, L_TOTAL_STORAGE_ALLOCATED)).intValue();
    }

    public int getTotalStorageAllocatedHigh()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_TOTAL_STORAGE_ALLOCATED_HIGH + iOff, L_TOTAL_STORAGE_ALLOCATED_HIGH)).intValue();
    }

    public int getTotalStorageLimit()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_TOTAL_STORAGE_LIMIT + iOff, L_TOTAL_STORAGE_LIMIT)).intValue();
    }


    public String toString()
    {
        return
            "[ResourceUsageObject=\n" +
            "   TOTAL_STORAGE_ALLOCATED     : " + getTotalStorageAllocated() + "\n" +
            "   TOTAL_STORAGE_ALLOCATED_HIGH: " + getTotalStorageAllocatedHigh() + "\n" +
            "   TOTAL_STORAGE_LIMIT         : " + getTotalStorageLimit() + "\n" +
            "]";
    }
}