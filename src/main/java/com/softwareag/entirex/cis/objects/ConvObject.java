package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import java.math.*;

import com.softwareag.entirex.cis.utils.*;

import java.util.List;
import java.util.LinkedList;

public class ConvObject
    extends AbstractServiceResponseObject
	{
	//ETB_CHAR    convid      [S_CONV_ID];  /* conversation ID            */
	//ETB_CHAR    uidServer   [S_USER_ID];  /* server's userID            */
	//ETB_CHAR    puidServer     [S_PUID];  /* server's physical UID      */
	//ETB_CHAR    puidTransServer[S_PUID];  /* server's translated physUID*/
	//ETB_CHAR    tokenServer   [S_TOKEN];  /* server's token             */
	//ETB_CHAR    uidClient   [S_USER_ID];  /* client's userID            */
	//ETB_CHAR    puidClient     [S_PUID];  /* client's physical UID      */
	//ETB_CHAR    puidTransClient[S_PUID];  /* client's translated physUID*/
	//ETB_CHAR    tokenClient   [S_TOKEN];  /* client's token             */
	//ETB_CHAR    server_class  [S_SERVER_CLASS];/* class            WBLV3*/
	//ETB_CHAR    server  [S_SERVER_NAME];  /* server                     */
	//ETB_CHAR    service     [S_SERVICE];  /* service                    */
	//ETB_LONG    ConvNonact;               /* conversation non activity  */
	//                                    /* timeout                    */
	//ETB_LONG    LastActivity;             /* time (in seconds) since    */
	//                                    /* last activity              */
	//ETB_SHORT   fType;                    /* conversation type flag: ****/
	//                                    /* OFF ........ 0 (convers.)  */
	//                                    /* NONE ....... 4 (non-conv.) */
	//ETB_SHORT   NotUsed;                  /* alignment                  */
	//                                    /* begin version 2 additions  WBLV3*/
	//ETB_LONG    lTotalUOWs;               /* Number of active UOWs      WBLV3*/

    private static final int O_CONV_ID = 0;
    private static final int L_CONV_ID = 16;

    private static final int O_SERVER_USER_ID = 16;
    private static final int L_SERVER_USER_ID = 32;
    
    private static final int O_SERVER_PUSER_ID = 48;
    private static final int L_SERVER_PUSER_ID = 28;

    private static final int O_SERVER_TRANS_ID = 76;
    private static final int L_SERVER_TRANS_ID = 28;

    private static final int O_SERVER_TOKEN    = 104;
    private static final int L_SERVER_TOKEN    = 32;

    private static final int O_CLIENT_USER_ID  = 136;
    private static final int L_CLIENT_USER_ID  = 32;
    
    private static final int O_CLIENT_PUSER_ID = 168;
    private static final int L_CLIENT_PUSER_ID = 28;

    private static final int O_CLIENT_TRANS_ID = 196;
    private static final int L_CLIENT_TRANS_ID = 28;

    private static final int O_CLIENT_TOKEN    = 224;
    private static final int L_CLIENT_TOKEN    = 32;
    
    private static final int O_CLASS           = 256;
    private static final int L_CLASS           = 32;
    
    private static final int O_SERVER_NAME     = 288;
    private static final int L_SERVER_NAME     = 32;

    private static final int O_SERVICE         = 320;
    private static final int L_SERVICE         = 32;

	private static final int O_LAST_ACTIVITY   = 352;
	private static final int L_LAST_ACTIVITY   = 4;

	private static final int O_CONV_NONACT     = 356;
	private static final int L_CONV_NONACT     = 4;

	private static final int O_CONV_TYPE       = 360;
	private static final int L_CONV_TYPE       = 2;

	private static final int O_NOT_USED        = 362;
	private static final int L_NOT_USED        = 2;

	private static final int O_TOTAL_UOWS      = 364;
	private static final int L_TOTAL_UOWS      = 4;


    private static final int L_CONVERSATION_OBJECT = 368;

	public ConvObject()
		{
		}

	public ConvObject( byte[] abResponse, int iOff, int iLen )
		throws ServiceResponseException
		{
		super( abResponse, iOff, iLen );
		}

	public int getLength()
		{
		return L_CONVERSATION_OBJECT;
		}

	public String getConversationID()
		{
		return new String( abResponse, O_CONV_ID + iOff, L_CONV_ID ).trim();
		}

	public String getClientUserID()
		{
		return new String( abResponse, O_CLIENT_USER_ID + iOff, L_CLIENT_USER_ID ).trim();
		}

	public String getClientToken()
		{
		return new String( abResponse, O_CLIENT_TOKEN + iOff, L_CLIENT_TOKEN ).trim();
		}

	public String getServerUserID()
		{
		return new String( abResponse, O_SERVER_USER_ID + iOff, L_SERVER_USER_ID ).trim();
		}

	public String getServerToken()
		{
		return new String( abResponse, O_SERVER_TOKEN + iOff, L_SERVER_TOKEN ).trim();
		}

	public int getTotalUnitOfWorks()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_TOTAL_UOWS + iOff, L_TOTAL_UOWS ) ).intValue();
		}

	public String toString()
		{
		return
			//"Length = " + abResponse.length + "\n" +
			"[CONVERSATION=\n" +
			"   CONV-ID          : " + getConversationID()   + "\n" +
			"   TOTAL UOWS       : " + getTotalUnitOfWorks() + "\n" +
			"]";
		}

	private List myUnitOfWorkObjects = new LinkedList();
	
	public void addUnitOfWorkObject( UOWObject uow )
		{
		myUnitOfWorkObjects.add( uow );
		}

	public List getUnitOfWorkObjects()
		{
		return myUnitOfWorkObjects;
		}

	public String getClassName()
		{
		return new String( abResponse, O_CLASS + iOff, L_CLASS ).trim();		
		}

	public String getServerName()
		{
		return new String( abResponse, O_SERVER_NAME + iOff, L_SERVER_NAME ).trim();		
		}

	public String getServiceName()
		{
		return new String( abResponse, O_SERVICE + iOff, L_SERVICE ).trim();		
		}

	public int getConversationNonActivity()
		{
        return new BigInteger( Utils.getSubArray( abResponse, O_CONV_NONACT + iOff, L_CONV_NONACT ) ).intValue();		
		}
	}