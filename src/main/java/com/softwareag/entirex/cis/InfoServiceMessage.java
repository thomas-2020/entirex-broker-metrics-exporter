package com.softwareag.entirex.cis;

import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.utils.*;

/**
 * This class encapsulates a info service message. You can create an instance
 * from this class and set all the values defined for an information request
 * (see entirex documentation).<br>
 * <br>
 * <u>Example</u>
 * <p><pre>
 *      InfoServiceMessage info = new InfoServiceMessage();
 *      info.setInterfaceVersion(InterfaceVersion.VERSION_1);
 *      info.setObjectType(ObjectType.BROKER);
 *      info.setBlockLength(new BlockLength(1024));
 *      ...
 * </pre></p>
 *
 * All parameters are type-save (wrapped by classes). For every parameter
 * which has a range of allowed values (f.eg. ObjectType can be SERVER,
 * BROKER, WORKER, CLIENT, CONV, SERVICE, PSF), there are constants defined
 * (f.eg. ObjectType.SERVER). Every parameter where you can define a value
 * yourself, you have to instantiate manually (f.ef. new BlockLength(1024)).
 * You can pass this object directly to the ServiceRequest class which
 * knows how to get the bytes from the message and sends the request to
 * the broker.
 *
 * @author Andreas Hitzbleck
 */
public class InfoServiceMessage
    extends AbstractServiceMessage
{
    private BlockLength blockLength = null;
    private InfoLevel infoLevel = InfoLevel.INFO_LEVEL;
    private UserID userID = new UserID("");
    private Token token = new Token("");
    private ServerClass serverClass = new ServerClass("");
    private ServerName serverName = new ServerName("");
    private Service service = new Service("");
    private ConvID convID = new ConvID("");
    private Reserved reserved = Reserved.RESERVED;
    private UOWStatus uowStatus = new UOWStatus("");
    private UserStatus userStatus = new UserStatus("");
    private RecVUID recVUID = new RecVUID("");
    private RecVToken recVToken = new RecVToken("");
    private RecVServer recVServer = new RecVServer("");
    private RecVService recVService = new RecVService("");
    private RecVClass recVClass = new RecVClass("");

    public void setBlockLength(BlockLength blockLength)
    {
        this.blockLength = blockLength;
    }

    public void setUserID(UserID userID)
    {
        this.userID = userID;
    }

    public void setToken(Token token)
    {
        this.token = token;
    }

    public void setServerClass(ServerClass serverClass)
    {
        this.serverClass = serverClass;
    }

    public void setServerName(ServerName serverName)
    {
        this.serverName = serverName;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public void setConvID(ConvID convID)
    {
        this.convID = convID;
    }

    public void setUOWStatus(UOWStatus uowStatus)
    {
        this.uowStatus = uowStatus;
    }

    public void setUserStatus(UserStatus userStatus)
    {
        this.userStatus = userStatus;
    }

    public void serRecVUID(RecVUID recVUID)
    {
        this.recVUID = recVUID;
    }

    public void setRecVToken(RecVToken recVToken)
    {
        this.recVToken = recVToken;
    }

    public void setRecVServer(RecVServer recVServer)
    {
        this.recVServer = recVServer;
    }

    public void setRecVService(RecVService recVService)
    {
        this.recVService = recVService;
    }

    public void setRecVClass(RecVClass recVClass)
    {
        this.recVClass = recVClass;
    }

    public byte[] getMessage()
        throws ServiceMessageException
    {
        checkCompleteness();

        /*
         * 428 + Topic(96) + publicationId(16) + subscriptionType(2) + NotUsed_4(2)
         */
        byte[] msg = new byte[ 428 + 96 + 16+ 2 + 2 ];

        Utils.copy(msg, 0, blockLength.getValue());
        Utils.copy(msg, 4, version.getValue());
        Utils.copy(msg, 6, infoLevel.getValue());
        Utils.copy(msg, 8, objectType.getValue());
        Utils.copy(msg, 10, userID.getValue());
        Utils.copy(msg, 42, pUserID.getValue());
        Utils.copy(msg, 70, token.getValue());
        Utils.copy(msg, 102, serverClass.getValue());
        Utils.copy(msg, 134, serverName.getValue());
        Utils.copy(msg, 166, service.getValue());
        Utils.copy(msg, 198, convID.getValue());
        Utils.copy(msg, 214, reserved.getValue());
        Utils.copy(msg, 216, uowID.getValue());
        Utils.copy(msg, 232, uowStatus.getValue());
        Utils.copy(msg, 233, userStatus.getValue());
        Utils.copy(msg, 265, recVUID.getValue());
        Utils.copy(msg, 297, recVToken.getValue());
        Utils.copy(msg, 329, recVServer.getValue());
        Utils.copy(msg, 361, recVService.getValue());
        Utils.copy(msg, 393, recVClass.getValue());

        return msg;
    }

    protected void checkCompleteness()
        throws ServiceMessageException
    {
        super.checkCompletness();

        if (blockLength == null) {
            throw new ServiceMessageException("BLOCK-LENGTH missing");
        }
    }

    public String toString()
    {
        return
        "[InfoServiceMessage=\n" +
            "   BLOCK-LENGTH: " + blockLength + "]\n" +
            "   VERSION     : " + version + "]\n" +
            "   INFO-LEVEL  : " + infoLevel + "]\n" +
            "   OBJECT-TYPE : " + objectType + "]\n" +
            "   USER-ID     : " + userID + "]\n" +
            "   P-USER-ID   : " + pUserID + "]\n" +
            "   TOKEN       : " + token + "]\n" +
            "   SERVER-CLASS: " + serverClass + "]\n" +
            "   SERVER-NAME : " + serverName + "]\n" +
            "   SERVICE     : " + service + "]\n" +
            "   CONV-ID     : " + convID + "]\n" +
            "   RESERVED    : " + reserved + "]\n" +
            "   UOWID       : " + uowID + "]\n" +
            "   UOWSTATUS   : " + uowStatus + "]\n" +
            "   USERSTATUS  : " + userStatus + "]\n" +
            "   RECVUID     : " + recVUID + "]\n" +
            "   RECVTOKEN   : " + recVToken + "]\n" +
            "   RECVSERVER  : " + recVServer + "]\n" +
            "   RECVSERVICE : " + recVService + "]\n" +
            "   RECVCLASS   : " + recVClass + "]\n" +
        "]";
    }
}