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
    private static final int O_ATTACH_MGRS_ACT = O_PSTORE_CONNECTED + L_PSTORE_CONNECTED + 10*4; // 10 * reserved ETB_LONG
    
    //LUWSTAT-ADD-TIME 	    I4 	4 	Unit of work status additional lifetime.
    private static final int L_LUWSTAT_ADD_TIME = 4;
    private static final int O_LUWSTAT_ADD_TIME = O_ATTACH_MGRS_ACT + L_ATTACH_MGRS_ACT;
    
    //PRODUCT-VERSION 	    A16 	4 	Version, release, service pack, and patch level, e.g. 8.0.1.00.
    private static final int L_PRODUCT_VERSION = 16;
    private static final int O_PRODUCT_VERSION = O_LUWSTAT_ADD_TIME + L_LUWSTAT_ADD_TIME;


	//LICENSE-EXPIRATION-DATE 	A10 	5 	License expiration date.
    private static final int L_LICENSE_EXPIRATION_DATE = 10;
    private static final int O_LICENSE_EXPIRATION_DATE = O_PRODUCT_VERSION + L_PRODUCT_VERSION;
	//SECURITY-TYPE 	I1 	5 	Security type:0 	None1 	SAG2 	Light3 	Other
    private static final int L_SECURITY_TYPE = 1;
    private static final int O_SECURITY_TYPE = O_LICENSE_EXPIRATION_DATE + L_LICENSE_EXPIRATION_DATE;
	//ACCOUNTING-ENABLED 	I1 	5 	1 	Accounting enabled0 	Accounting disabled
    private static final int L_ACCOUNTING_ENABLED = 1;
    private static final int O_ACCOUNTING_ENABLED = O_SECURITY_TYPE + L_SECURITY_TYPE;
	//NUM-FREE-CCB 	I4 	5 	Number of free CCB entries (conversation control block).
    private static final int L_NUM_FREE_CCB = 4;
    private static final int O_NUM_FREE_CCB = O_ACCOUNTING_ENABLED + L_ACCOUNTING_ENABLED;
	//NUM-FREE-PCB 	I4 	5 	Number of free PCB entries(participant control block).
    private static final int L_NUM_FREE_PCB = 4;
    private static final int O_NUM_FREE_PCB = O_NUM_FREE_CCB + L_NUM_FREE_CCB;
	//NUM-FREE-PCBEXT 	I4 	5 	Number of free PCBEXT entries (PCB extension).
    private static final int L_NUM_FREE_PCBEXT = 4;
    private static final int O_NUM_FREE_PCBEXT = O_NUM_FREE_PCB + L_NUM_FREE_PCB;
	//NUM-FREE-SCB 	I4 	5 	Number of free SCB entries (service control block).
    private static final int L_NUM_FREE_SCB = 4;
    private static final int O_NUM_FREE_SCB = O_NUM_FREE_PCBEXT + L_NUM_FREE_PCBEXT;
	//NUM-FREE-SCBEXT 	I4 	5 	Number of free SCBEXT entries (SCB extension).
    private static final int L_NUM_FREE_SCBEXT = 4;
    private static final int O_NUM_FREE_SCBEXT = O_NUM_FREE_SCB + L_NUM_FREE_SCB;
	//NUM-FREE-TCBEXT 	I4 	5 	Number of free TCBEXT entries (TCP extension).
    private static final int L_NUM_FREE_TCBEXT = 4;
    private static final int O_NUM_FREE_TCBEXT = O_NUM_FREE_SCBEXT + L_NUM_FREE_SCBEXT;
	//NUM-FREE-TOQ 	I4 	5 	Number of free TOQ entries (timeout queue).
    private static final int L_NUM_FREE_TOQ = 4;
    private static final int O_NUM_FREE_TOQ = O_NUM_FREE_TCBEXT + L_NUM_FREE_TCBEXT + 2 * 4; //reserved_etbinfo_v910_66 ... 68
	//NUM-FREE-UWCB 	I4 	5 	Number of free UWCB entries (UOW control block).
    private static final int L_NUM_FREE_UWCB = 4;
    private static final int O_NUM_FREE_UWCB = O_NUM_FREE_TOQ + L_NUM_FREE_TOQ;
	//NUM-COM-BUFFER 	I4 	5 	Number of communication buffers.
    private static final int L_NUM_COM_BUFFER = 4;
    private static final int O_NUM_COM_BUFFER = O_NUM_FREE_UWCB + L_NUM_FREE_UWCB;
	//NUM-COM-SLOT 	I4 	5 	Number of communication buffer slots.
    private static final int L_NUM_COM_SLOT = 4;
    private static final int O_NUM_COM_SLOT = O_NUM_COM_BUFFER + L_NUM_COM_BUFFER;
	//NUM-COM-SLOT-FREE 	I4 	5 	Number of communication buffer slots free.
    private static final int L_NUM_COM_SLOT_FREE = 4;
    private static final int O_NUM_COM_SLOT_FREE = O_NUM_COM_SLOT + L_NUM_COM_SLOT;
	//NUM-CMDLOG-FILTER 	I4 	5 	Number of CMDLOG filters.
    private static final int L_NUM_CMDLOG_FILTER = 4;
    private static final int O_NUM_CMDLOG_FILTER = O_NUM_COM_SLOT_FREE + L_NUM_COM_SLOT_FREE;
	//NUM-CMDLOG-FILTER-ACTIVE 	I4 	5 	Number of CMDLOG filters active.
    private static final int L_NUM_CMDLOG_FILTER_ACTIVE = 4;
    private static final int O_NUM_CMDLOG_FILTER_ACTIVE = O_NUM_CMDLOG_FILTER + L_NUM_CMDLOG_FILTER;
	//CMDLOG 	I1 	5 	Reflects status of Broker attribute CMDLOG:1 	Command logging features are available for the Broker0 	Command logging not available
    private static final int L_CMDLOG = 1;
    private static final int O_CMDLOG = O_NUM_CMDLOG_FILTER_ACTIVE + L_NUM_CMDLOG_FILTER_ACTIVE;
	//CMDLOG-ENABLED 	I1 	5 	Reflects result of commands DISABLE-CMDLOG and ENABLE-CMDLOG:1 	Command logging enabled0 	Command logging temporarily disabled
    private static final int L_CMDLOG_ENABLED = 1;
    private static final int O_CMDLOG_ENABLED = O_CMDLOG + L_CMDLOG;
	//NOTUSED3 	A2 	5 	Alignment.
    private static final int L_NOTUSED3 = 2;
    private static final int O_NOTUSED3 = O_CMDLOG_ENABLED + L_CMDLOG_ENABLED;
	//ATTRIBUTE-FILE-NAME 	A256 	5 	Attribute file name.
    private static final int L_ATTRIBUTE_FILE_NAME = 256;
    private static final int O_ATTRIBUTE_FILE_NAME = O_NOTUSED3 + L_NOTUSED3;
	//LOG-FILE-NAME 	A256 	5 	Name of trace log file.
    private static final int L_LOG_FILE_NAME = 256;
    private static final int O_LOG_FILE_NAME = O_ATTRIBUTE_FILE_NAME + L_ATTRIBUTE_FILE_NAME;
	//LOG-FILE-SIZE 	I4 	5 	Size of trace log file.
    private static final int L_LOG_FILE_SIZE = 4;
    private static final int O_LOG_FILE_SIZE = O_LOG_FILE_NAME + L_LOG_FILE_NAME;
	//LICENSE-FILE-NAME 	A256 	5 	License file name.
    private static final int L_LICENSE_FILE_NAME = 256;
    private static final int O_LICENSE_FILE_NAME = O_LOG_FILE_SIZE + L_LOG_FILE_SIZE;
	//CMDLOG-FILE-SIZE 	I4 	5 	Max. size of CMDLOG file.
    private static final int L_CMDLOG_FILE_SIZE = 4;
    private static final int O_CMDLOG_FILE_SIZE = O_LICENSE_FILE_NAME + L_LICENSE_FILE_NAME;
	//OPEN-CMDLOG-FILE-NAME 	A256 	5 	Name of open CMDLOG file.
    private static final int L_OPEN_CMDLOG_FILE_NAME = 256;
    private static final int O_OPEN_CMDLOG_FILE_NAME = O_CMDLOG_FILE_SIZE + L_CMDLOG_FILE_SIZE;
	//OPEN-CMDLOG-FILE-SIZE 	I4 	5 	Size of CMDLOG file.
    private static final int L_OPEN_CMDLOG_FILE_SIZE = 4;
    private static final int O_OPEN_CMDLOG_FILE_SIZE = O_OPEN_CMDLOG_FILE_NAME + L_OPEN_CMDLOG_FILE_NAME;
	//CLOSED-CMDLOG-FILE-NAME 	A256 	5 	Name of closed CMDLOG file.
    private static final int L_CLOSED_CMDLOG_FILE_NAME = 256;
    private static final int O_CLOSED_CMDLOG_FILE_NAME = O_OPEN_CMDLOG_FILE_SIZE + L_OPEN_CMDLOG_FILE_SIZE;
	//CLOSED-CMDLOG-FILE-SIZE 	I4 	5 	Size of closed CMDLOG file.
    private static final int L_CLOSED_CMDLOG_FILE_SIZE = 4;
    private static final int O_CLOSED_CMDLOG_FILE_SIZE = O_CLOSED_CMDLOG_FILE_NAME + L_CLOSED_CMDLOG_FILE_NAME;
	//RESERVED 	I4 	5 	Reserved for future use.
    private static final int L_RESERVED = 4;
    private static final int O_RESERVED = O_CLOSED_CMDLOG_FILE_SIZE + L_CLOSED_CMDLOG_FILE_SIZE;
	//ACCOUNTING-FILE-NAME 	A256 	5 	Name of accounting output file.
    private static final int L_ACCOUNTING_FILE_NAME = 256;
    private static final int O_ACCOUNTING_FILE_NAME = O_RESERVED + L_RESERVED;
	//ACCOUNTING-FILE-SIZE 	I4 	5 	Size of accounting output file.
    private static final int L_ACCOUNTING_FILE_SIZE = 4;
    private static final int O_ACCOUNTING_FILE_SIZE = O_ACCOUNTING_FILE_NAME + L_ACCOUNTING_FILE_NAME;
	//CONTROL-INTERVAL 	I4 	5 	Control interval in seconds.
    private static final int L_CONTROL_INTERVAL = 4;
    private static final int O_CONTROL_INTERVAL = O_ACCOUNTING_FILE_SIZE + L_ACCOUNTING_FILE_SIZE;
	//MAX-TAKEOVER-ATTEMPTS 	I4 	5 	Max. number of takeover attempts.
    private static final int L_MAX_TAKEOVER_ATTEMPTS = 4;
    private static final int O_MAX_TAKEOVER_ATTEMPTS = O_CONTROL_INTERVAL + L_CONTROL_INTERVAL;
	//RUN-MODE 	A16 	5 	Broker run mode.
    private static final int L_RUN_MODE = 16;
    private static final int O_RUN_MODE = O_MAX_TAKEOVER_ATTEMPTS + L_MAX_TAKEOVER_ATTEMPTS;
	//PARTNER-CLUSTER-ADDRESS 	A32 	5 	Partner Cluster Address.
    private static final int L_PARTNER_CLUSTER_ADDRES = 32;
    private static final int O_PARTNER_CLUSTER_ADDRES = O_RUN_MODE + L_RUN_MODE;
	//CMDLOG-SWITCHES-BY-SIZE 	I4 	5 	Number of CMDLOG switches by size.
    private static final int L_CMDLOG_SWITCHES_BY_SIZE = 4;
    private static final int O_CMDLOG_SWITCHES_BY_SIZE = O_PARTNER_CLUSTER_ADDRES + L_PARTNER_CLUSTER_ADDRES;
	//CMDLOG-SWITCHES-BY-CIS 	I4 	5 	Number of CMDLOG switches by CIS.
    private static final int L_CMDLOG_SWITCHES_BY_CIS = 4;
    private static final int O_CMDLOG_SWITCHES_BY_CIS = O_CMDLOG_SWITCHES_BY_SIZE + L_CMDLOG_SWITCHES_BY_SIZE;
	//CLIENT-NONACT 	I4 	7 	Client timeout in seconds. See broker attribute CLIENT-NONACT.
    private static final int L_CLIENT_NONAC = 4;
    private static final int O_CLIENT_NONAC = O_CMDLOG_SWITCHES_BY_CIS + L_CMDLOG_SWITCHES_BY_CIS;
	//NUM-WQE 	I4 	7 	Number of work queue entries. See broker attribute NUM-WQE.
    private static final int L_NUM_WQE = 4;
    private static final int O_NUM_WQE = O_CLIENT_NONAC + L_CLIENT_NONAC;
	//TOTAL-STORAGE-ALLOCATED 	I4 	7 	Size of allocated storage in bytes.
    private static final int L_TOTAL_STORAGE_ALLOCATED = 4;
    private static final int O_TOTAL_STORAGE_ALLOCATED = O_NUM_WQE + L_NUM_WQE;
	//TOTAL-STORAGE-ALLOCATED-HIGH 	I4 	7 	Highest size of allocated storage in bytes since Broker started.
    private static final int L_TOTAL_STORAGE_ALLOCATED_HIGH = 4;
    private static final int O_TOTAL_STORAGE_ALLOCATED_HIGH = O_TOTAL_STORAGE_ALLOCATED + L_TOTAL_STORAGE_ALLOCATED;
	//TOTAL-STORAGE-LIMIT 	I4 	7 	Maximum of storage that can be allocated. See broker attribute MAX-MEMORY.
    private static final int L_TOTAL_STORAGE_LIMIT = 4;
    private static final int O_TOTAL_STORAGE_LIMIT = O_TOTAL_STORAGE_ALLOCATED_HIGH + L_TOTAL_STORAGE_ALLOCATED_HIGH;
	//BROKER-ID 	A32 	7 	BROKER-ID. See broker attribute BROKER-ID.
    private static final int L_BROKER_ID = 32;
    private static final int O_BROKER_ID = O_TOTAL_STORAGE_LIMIT + L_TOTAL_STORAGE_LIMIT;
	//HOST-NAME 	A256 	7 	Name of host running broker (on z/OS copied from CVTSNAME).
    private static final int L_HOST_NAME = 256;
    private static final int O_HOST_NAME = O_BROKER_ID + L_BROKER_ID;
	//SYSPLEX-NAME 	A8 	7 	Name of SYSPLEX (copied from ECVTSPLX).
    private static final int L_SYSPLEX_NAME = 8;
    private static final int O_SYSPLEX_NAME = O_HOST_NAME + L_HOST_NAME;
	//CAUTOLOGON 	I1 	7 	Auto logon:0 	NO1 	YESSee broker attribute AUTOLOGON.
    private static final int L_CAUTOLOGON = 1;
    private static final int O_CAUTOLOGON = O_SYSPLEX_NAME + L_SYSPLEX_NAME;
	//CDYNAMIC-MEMORY-MANAGEMENT 	I1 	7 	Dynamic memory management:0 	NO1 	YESSee broker attribute DYNAMIC-MEMORY-MANAGEMENT.
    private static final int L_CDYNAMIC_MEMORY_MANAGEMENT = 1;
    private static final int O_CDYNAMIC_MEMORY_MANAGEMENT = O_CAUTOLOGON + L_CAUTOLOGON;
	//CDYNAMIC-WORKER-MANAGEMENT 	I1 	7 	Dynamic worker management:0 	NO1 	YESSee broker attribute DYNAMIC-WORKER-MANAGEMENT.
    private static final int L_CDYNAMIC_WORKER_MANAGEMENT = 1;
    private static final int O_CDYNAMIC_WORKER_MANAGEMENT = O_CDYNAMIC_MEMORY_MANAGEMENT + L_CDYNAMIC_MEMORY_MANAGEMENT;
	//CSERVICE-UPDATES 	I1 	7 	Service updates:0 	NO1 	YESSee broker attribute SERVICE-UPDATES.
    private static final int L_CSERVICE_UPDATES = 1;
    private static final int O_CSERVICE_UPDATES = O_CDYNAMIC_WORKER_MANAGEMENT + L_CDYNAMIC_WORKER_MANAGEMENT;
    //ETB_CHAR reserved_etbinfo_v910_36
    private static final int L_RESERVED_ETBINFO = 1;
    private static final int O_RESERVED_ETBINFO = O_CSERVICE_UPDATES + L_CSERVICE_UPDATES;
    //CTRANSPORT-NET 	I1 	7 	Was TRANSPORT=NET specified?0 	NO1 	YESSee broker attribute TRANSPORT=NET.
    private static final int L_CTRANSPORT_NET = 1;
    private static final int O_CTRANSPORT_NET = O_RESERVED_ETBINFO + L_RESERVED_ETBINFO;
	//CTRANSPORT-SSL 	I1 	7 	Was TRANSPORT=SSL specified?0 	NO1 	YESSee broker attribute TRANSPORT=SSL.
    private static final int L_CTRANSPORT_SSL = 1;
    private static final int O_CTRANSPORT_SSL = O_CTRANSPORT_NET + L_CTRANSPORT_NET;
	//CTRANSPORT-TCP 	I1 	7 	Was TRANSPORT=TCP specified?0 	NO1 	YESSee broker attribute TRANSPORT=TCP.
    private static final int L_CTRANSPORT_TCP = 1;
    private static final int O_CTRANSPORT_TCP = O_CTRANSPORT_SSL + L_CTRANSPORT_SSL;
	//NTRAP-ERROR 	I4 	7 	Value defined for attribute TRAP-ERROR.
    private static final int L_NTRAP_ERROR = 4;
    private static final int O_NTRAP_ERROR = O_CTRANSPORT_TCP + L_CTRANSPORT_TCP;
	//CPU-USED-IN-SECONDS 	I4 	9 	Amount of CPU time in seconds used by Broker process since Broker start.
    private static final int L_CPU_USED_IN_SECONDS = 4;
    private static final int O_CPU_USED_IN_SECONDS = O_NTRAP_ERROR + L_NTRAP_ERROR;
	//CPU-USED-REST-IN-MICROSECONDS 	I4 	9 	Additional CPU time in microseconds used by Broker process since Broker start. (CPU time is provided by two fields because total value including microseconds may exceed one 4-byte integer.)
    private static final int L_CPU_USED_REST_IN_MICROSECONDS = 4;
    private static final int O_CPU_USED_REST_IN_MICROSECONDS = O_CPU_USED_IN_SECONDS + L_CPU_USED_IN_SECONDS;
	//CPU-USED-PERCENTAGE 	I4 	9 	CPU time consumed by Broker process in relation to total CPU workload in percent and normalized by the number of CPUs. It never exceeds 100%.
    private static final int L_CPU_USED_PERCENTAGE = 4;
    private static final int O_CPU_USED_PERCENTAGE = O_CPU_USED_REST_IN_MICROSECONDS + L_CPU_USED_REST_IN_MICROSECONDS;
	//APPLICATION-MONITORING 	I1 	11 	Application Monitoring.0 	NO1 	YESSee broker attribute APPLICATION-MONITORING.
    private static final int L_APPLICATION_MONITORING = 1;
    private static final int O_APPLICATION_MONITORING = O_CPU_USED_PERCENTAGE + L_CPU_USED_PERCENTAGE;
	//COLLECTOR-BROKER-ID 	A64 	11 	Collector Broker ID. See Application Monitoring attribute COLLECTOR-BROKER-ID.
    private static final int L_COLLECTOR_BROKER_ID = 64;
    private static final int O_COLLECTOR_BROKER_ID = O_APPLICATION_MONITORING + L_APPLICATION_MONITORING;
	//UNUSED3 	A3 	11 	Alignment.
    private static final int L_UNUSED3 = 3;
    private static final int O_UNUSED3 = O_COLLECTOR_BROKER_ID + L_COLLECTOR_BROKER_ID;
	//PROCESS-ID 	A16 	12 	Process ID of Broker. Under z/OS, the JOB-ID is returned.
    private static final int L_PROCESS_ID = 16;
    private static final int O_PROCESS_ID = O_UNUSED3 + L_UNUSED3;
	//THREAD-ID 	A16 	12 	Thread ID of Broker. Under z/OS, the TCB address of the main task is returned.
    private static final int L_THREAD_ID = 16;
    private static final int O_THREAD_ID = O_PROCESS_ID + L_PROCESS_ID;

    //private static final int L_BROKER_OBJECT = O_PRODUCT_VERSION + L_PRODUCT_VERSION; // CIS 2: O_CDEFERRED + L_CDEFERRED;
    private static final int L_BROKER_OBJECT = O_THREAD_ID + L_THREAD_ID;

    //public static final InterfaceVersion IV = InterfaceVersion.VERSION_4; //Implemented Interface Version
    public static final InterfaceVersion IV = InterfaceVersion.VERSION_12; //Implemented Interface Version
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
            "   APPMON_ENABLED: " + isApplicationMonitoringEnabled() + "\n" +
            "   CPU_USAGE_%   : " + getCpuUsageInPercent() + "\n" + 
            "   CPU_USAGE     : " + getCpuUsageInMicros() + "\n" +
            "   NUM_WQE       : " + getNumWQE() + "\n" +
            "   ATTRIBUTE_FILE: " + getAttributeFilename() + "\n" +
            "   HOSTNAME      : " + getHostname() + "\n" +
            "   TRAP_ERRORS   : " + getTrapErrors() + "\n" +
            "   AUTOLOGON_ENAB: " + isAutoLogonEnabled() + "\n" +
            "   SYSPLEX_NAME  : " + getSysplexName() + "\n" +
            "   BROKER_ID     : " + getBrokerID() + "\n" +
            "   LICENSE_FILE  : " + getLicenseFilename() + "\n" + 
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

	public int isApplicationMonitoringEnabled() {
		return new BigInteger(Utils.getSubArray(abResponse, O_APPLICATION_MONITORING + iOff, L_APPLICATION_MONITORING)).intValue();
	}
	
	public int getCpuUsageInPercent() {
		return new BigInteger(Utils.getSubArray(abResponse, O_CPU_USED_PERCENTAGE + iOff, L_CPU_USED_PERCENTAGE)).intValue();
	}

	private long getCpuUsageInSeconds() {
		return new BigInteger(Utils.getSubArray(abResponse, O_CPU_USED_IN_SECONDS + iOff, L_CPU_USED_IN_SECONDS)).intValue();		
	}

	private long getCpuUsageRestInMicros() {
		return new BigInteger(Utils.getSubArray(abResponse, O_CPU_USED_REST_IN_MICROSECONDS + iOff, L_CPU_USED_REST_IN_MICROSECONDS)).intValue();		
	}

	public double getCpuUsageInMicros() {
		return ( getCpuUsageInSeconds() * 1000000 ) + getCpuUsageRestInMicros();
	}

	public int getNumWQE() {
		return new BigInteger(Utils.getSubArray(abResponse, O_NUM_WQE + iOff, L_NUM_WQE)).intValue();
	}

	public String getAttributeFilename() {
		return new String( Utils.getSubArray(abResponse, O_ATTRIBUTE_FILE_NAME + iOff, L_ATTRIBUTE_FILE_NAME) ).trim();
	}

	public String getHostname() {
		return new String( Utils.getSubArray(abResponse, O_HOST_NAME + iOff, L_HOST_NAME) ).trim();
	}

	public int getTrapErrors() {
		return new BigInteger(Utils.getSubArray(abResponse, O_NTRAP_ERROR + iOff, L_NTRAP_ERROR)).intValue();		
	}

	public int isAutoLogonEnabled() {
		return new BigInteger(Utils.getSubArray(abResponse, O_CAUTOLOGON + iOff, L_CAUTOLOGON)).intValue();
	}

	public String getSysplexName() {
		return new String( Utils.getSubArray(abResponse, O_SYSPLEX_NAME + iOff, L_SYSPLEX_NAME) ).trim();
	}

	public String getBrokerID() {
		return new String( Utils.getSubArray(abResponse, O_BROKER_ID + iOff, L_BROKER_ID) ).trim();
	}

	public String getLicenseFilename() {
		return new String( Utils.getSubArray(abResponse, O_LICENSE_FILE_NAME + iOff, L_LICENSE_FILE_NAME) ).trim();
	}
}