package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import java.math.*;
import com.softwareag.entirex.cis.utils.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale;

public class UOWObject
    extends AbstractServiceResponseObject
	{

	//ETB_CHAR  uowid[S_UOW_ID];            /* Unit of Work Id            WBLV3*/1
 	//ETB_CHAR  convid[S_CONV_ID];          /* Conversation Id            WBLV3*/2
 	//ETB_CHAR  senderUid[S_USER_ID];       /* Sender User Id             WBLV3*/3
	//ETB_CHAR  senderToken[S_TOKEN];       /* Sender User Token          WBLV3*/4
	//ETB_CHAR  senderServer[S_SERVER_NAME];/* Sender Server name         WBLV3*/5
	//ETB_CHAR  senderClass[S_SERVER_CLASS];/* Sender Server class        WBLV3*/6
	//ETB_CHAR  senderService[S_SERVICE];   /* Sender Service name        WBLV3*/7
	//ETB_CHAR  recvrUid[S_USER_ID];        /* Receiver User Id           WBLV3*/8
	//ETB_CHAR  recvrToken[S_TOKEN];        /* Receiver User Token        WBLV3*/9
	//ETB_CHAR  recvrServer[S_SERVER_NAME]; /* Receiver Server name       WBLV3*/0
	//ETB_CHAR  recvrClass[S_SERVER_CLASS]; /* Receiver Server class      WBLV3*/1
	//ETB_CHAR  recvrService[S_SERVICE];    /* Receiver Service name      WBLV3*/2
	//ETB_CHAR  userStatus[S_U_STATUS];     /* User Status                WBLV3*/3
	//ETB_CHAR  uwStatus;                   /* Unit of Work status        WBLV3*/4
	//ETB_CHAR  cEoc;                       /* End of Conversation State  WBLV3*/5
	//ETB_CHAR  cStore;                     /* Persistence flag           WBLV3*/6
	//ETB_CHAR  cUOWStatStore;              /* UOW Status persist flag    WBLV3*/7
	//ETB_LONG  lEocReason;                 /* End of Conv Reason code    WBLV3*/8
	//ETB_LONG  lAttemptCount;              /* Attempted delivery count   WBLV3*/9
	//ETB_LONG  lMsqCnt;                    /* Number of messages         WBLV3*/0
	//ETB_LONG  lMsqSize;                   /* Total message size         WBLV3*/1
	//ETB_CHAR  uwStatusLifeTime[S_DATE];   /* Status life time           WBLV3*/2
	//ETB_CHAR  uwCreateTime[S_DATE];       /* Time Unit of Work created  WBLV3*/3
	//ETB_LONG  uwLifeTime;                 /* Unit of Work life time     WBLV3*/4

    private static final int O_UOW_ID           = 0;
    private static final int L_UOW_ID           = 16;

    private static final int O_CONV_ID          = O_UOW_ID + L_UOW_ID;
    private static final int L_CONV_ID          = 16;

    private static final int O_SENDER_USER_ID   = O_CONV_ID + L_CONV_ID;
    private static final int L_SENDER_USER_ID   = 32;

    private static final int O_SENDER_TOKEN     = O_SENDER_USER_ID + L_SENDER_USER_ID;
    private static final int L_SENDER_TOKEN     = 32;

    private static final int O_SENDER_SERVER    = O_SENDER_TOKEN + L_SENDER_TOKEN;
    private static final int L_SENDER_SERVER    = 32;

    private static final int O_SENDER_CLASS     = O_SENDER_SERVER + L_SENDER_SERVER;
    private static final int L_SENDER_CLASS     = 32;

    private static final int O_SENDER_SERVICE   = O_SENDER_CLASS + L_SENDER_CLASS;
    private static final int L_SENDER_SERVICE   = 32;

    private static final int O_RECEIVER_USER_ID = O_SENDER_SERVICE + L_SENDER_SERVICE;
    private static final int L_RECEIVER_USER_ID = 32;

    private static final int O_RECEIVER_TOKEN   = O_RECEIVER_USER_ID + L_RECEIVER_USER_ID;
    private static final int L_RECEIVER_TOKEN   = 32;

    private static final int O_RECEIVER_SERVER  = O_RECEIVER_TOKEN + L_RECEIVER_TOKEN;
    private static final int L_RECEIVER_SERVER  = 32;

    private static final int O_RECEIVER_CLASS   = O_RECEIVER_SERVER + L_RECEIVER_SERVER;
    private static final int L_RECEIVER_CLASS   = 32;

    private static final int O_RECEIVER_SERVICE = O_RECEIVER_CLASS + L_RECEIVER_CLASS;
    private static final int L_RECEIVER_SERVICE = 32;

	private static final int O_USER_STATUS      = O_RECEIVER_SERVICE + L_RECEIVER_SERVICE;
	private static final int L_USER_STATUS      = 32; //48;
	
	private static final int O_STATUS           = O_USER_STATUS + L_USER_STATUS;
	private static final int L_STATUS           = 1;

	private static final int O_EOC_STATUS       = O_STATUS + L_STATUS;
	private static final int L_EOC_STATUS       = 1;

	private static final int O_STORE            = O_EOC_STATUS + L_EOC_STATUS;
	private static final int L_STORE            = 1;

	private static final int O_UOW_STATUS_STORE = O_STORE + L_STORE;
	private static final int L_UOW_STATUS_STORE = 1;

	private static final int O_EOC_REASON       = O_UOW_STATUS_STORE + L_UOW_STATUS_STORE;
	private static final int L_EOC_REASON       = 4;

	private static final int O_ATTEMPT_COUNT    = O_EOC_REASON + L_EOC_REASON;
	private static final int L_ATTEMPT_COUNT    = 4;

	private static final int O_MSG_COUNT        = O_ATTEMPT_COUNT + L_ATTEMPT_COUNT;
	private static final int L_MSG_COUNT        = 4;

	private static final int O_MSG_SIZE         = O_MSG_COUNT + L_MSG_COUNT;
	private static final int L_MSG_SIZE         = 4;

	private static final int O_STATUS_LIFE_TIME = O_MSG_SIZE + L_MSG_SIZE;
	private static final int L_STATUS_LIFE_TIME = 32;

	private static final int O_CREATE_TIME      = O_STATUS_LIFE_TIME + L_STATUS_LIFE_TIME;
	private static final int L_CREATE_TIME      = 32;

	private static final int O_LIFE_TIME        = O_CREATE_TIME + L_CREATE_TIME;
	private static final int L_LIFE_TIME        = 4;


	private static final int L_PSF_OBJECT       = O_LIFE_TIME + L_LIFE_TIME;

	public UOWObject()
		{
		}

	public UOWObject( byte[] abResponse, int iOff, int iLen )
	throws ServiceResponseException
		{
		super( abResponse, iOff, iLen );
		}

	public int getLength()
		{
		return L_PSF_OBJECT;
		}

	public String getUnitOfWorkID()
		{
		return new String( abResponse, O_UOW_ID + iOff, L_UOW_ID ).trim();
		}

	public String getConversationID()
		{
		return new String( abResponse, O_CONV_ID + iOff, L_CONV_ID ).trim();
		}

	public String getSenderUserID()
		{
		return new String( abResponse, O_SENDER_USER_ID + iOff, L_SENDER_USER_ID ).trim();
		}

	public String getSenderToken()
		{
		return new String( abResponse, O_SENDER_TOKEN + iOff, L_SENDER_TOKEN ).trim();
		}

	public String getSenderServer()
		{
		return new String( abResponse, O_SENDER_SERVER + iOff, L_SENDER_SERVER ).trim();
		}

	public String getSenderClass()
		{
		return new String( abResponse, O_SENDER_CLASS + iOff, L_SENDER_CLASS ).trim();
		}

	public String getSenderService()
		{
		return new String( abResponse, O_SENDER_SERVICE + iOff, L_SENDER_SERVICE ).trim();
		}

	public String getReceiverUserID()
		{
		return new String( abResponse, O_RECEIVER_USER_ID + iOff, L_RECEIVER_USER_ID ).trim();
		}

	public String getReceiverToken()
		{
		return new String( abResponse, O_RECEIVER_TOKEN + iOff, L_RECEIVER_TOKEN ).trim();
		}

	public String getReceiverServer()
		{
		return new String( abResponse, O_RECEIVER_SERVER + iOff, L_RECEIVER_SERVER ).trim();
		}

	public String getReceiverClass()
		{
		return new String( abResponse, O_RECEIVER_CLASS + iOff, L_RECEIVER_CLASS ).trim();
		}

	public String getReceiverService()
		{
		return new String( abResponse, O_RECEIVER_SERVICE + iOff, L_RECEIVER_SERVICE ).trim();
		}

	public String getUserStatus()
		{
		return new String( abResponse, O_USER_STATUS + iOff, L_USER_STATUS ).trim();
		}

	public int getStatus()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_STATUS + iOff, L_STATUS ) ).intValue();
		}

	public int getEOCStatus()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_EOC_STATUS + iOff, L_EOC_STATUS ) ).intValue();
		}

	public int getStoreFlag()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_STORE + iOff, L_STORE ) ).intValue();
		}

	public int getDeliveryCount()
		{
		return new BigInteger( Utils.getSubArray( abResponse, O_ATTEMPT_COUNT + iOff, L_ATTEMPT_COUNT ) ).intValue();
		}

	public int getMessageCount()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_MSG_COUNT + iOff, L_MSG_COUNT ) ).intValue();
		}

	public int getMessageSize()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_MSG_SIZE + iOff, L_MSG_SIZE ) ).intValue();
		}

	public String getStatusLifeTimeInternal()
		{
		return new String( abResponse, O_STATUS_LIFE_TIME + iOff, L_STATUS_LIFE_TIME ).trim(); 
		}

	public String getCreateTimeInternal()
		{
		return new String( abResponse, O_CREATE_TIME + iOff, L_CREATE_TIME ).trim(); 
		}

	private static final String ETB_DATE_FORMAT = "EEE MMM dd HH:mm:ss yyyy";

	public Date getCreateTime()
		{
		try
			{
			return new SimpleDateFormat( ETB_DATE_FORMAT, Locale.ENGLISH ).parse( getCreateTimeInternal() );
			}

		catch ( ParseException e )
			{
			}

		return null;
		}

	public Date getStatusLifeTime()
		{
		try
			{
			return new SimpleDateFormat( ETB_DATE_FORMAT, Locale.ENGLISH ).parse( getStatusLifeTimeInternal() );
			}

		catch ( ParseException e )
			{
			}

		return null;
		}

	public int getLifeTime()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_LIFE_TIME + iOff, L_LIFE_TIME ) ).intValue();
		}

	public String toString()
		{
		return
			//"Length = " + abResponse.length + "\n" +
			"[PSF=\n" +
			"   UOW-ID           : " + getUnitOfWorkID()   + "\n" +
			"   CONV-ID          : " + getConversationID() + "\n" +
			"   SENDER USERID    : " + getSenderUserID()   + "\n" +
			"   SENDER TOKEN     : " + getSenderToken()    + "\n" +
			"   SENDER CLASS     : " + getSenderClass()    + "\n" +
			"   SENDER SERVER    : " + getSenderServer()   + "\n" +
			"   SENDER SERVICE   : " + getSenderService()  + "\n" +
			"   RECEIVER USERID  : " + getReceiverUserID() + "\n" +
			"   RECEIVER TOKEN   : " + getReceiverToken()  + "\n" +
			"   RECEIVER CLASS   : " + getReceiverClass()  + "\n" +
			"   RECEIVER SERVER  : " + getReceiverServer() + "\n" +
			"   RECEIVER SERVICE : " + getReceiverService()+ "\n" +
			"   USER STATUS      : " + getUserStatus()     + "\n" +
			"   STATUS           : " + getStatus()         + "\n" +
			"   EOC STATUS       : " + getEOCStatus()      + "\n" +
			"   STORE FLAG       : " + getStoreFlag()      + "\n" +
			"   DELIVERY COUNT   : " + getDeliveryCount()  + "\n" +
			"   MESSAGE COUNT    : " + getMessageCount()   + "\n" +
			"   MESSAGE SIZE     : " + getMessageSize()    + "\n" +
			"   STATUS LIFE TIME : " + getStatusLifeTime() + "\n" +
			"   CREATE TIME      : " + getCreateTime()     + "\n" +
			"   LIFE TIME        : " + getLifeTime()       + "\n" +
			"]";
		}

	private ConvObject myConversationObject;

	public void setConversationObject( ConvObject conv )
		{
		myConversationObject = conv;
		}

	public ConvObject getConversationObject()
		{
		return myConversationObject;
		}  

	} //End of class