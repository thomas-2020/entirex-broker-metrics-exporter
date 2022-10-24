package com.softwareag.entirex.cis;

import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.utils.*;

/**
 * This class encapsulates a cmd service message. You can create an instance
 * from this class and set all the values defined for an command request
 * (see entirex documentation).<br>
 * <br>
 * <u>Example</u>
 * <p><pre>
 *      CmdServiceMessage cmd = new CmdServiceMessage();
 *      cmd.setInterfaceVersion(InterfaceVersion.VERSION_1);
 *      cmd.setObjectType(ObjectType.SERVER);
 *      cmd.setCommand(Command.SHUTDOWN);
 *      cmd.setOption(Option.IMMED);
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
public class CmdServiceMessage
    extends AbstractServiceMessage
{
    private Command command = null;
    private Option option = null;

    public void setCommand(Command command)
    {
        this.command = command;
    }

    public void setOption(Option option)
    {
        this.option = option;
    }

    public byte[] getMessage()
        throws ServiceMessageException
    {
        checkCompleteness();
        /* 
         * API Version 4
         * 52 + Topic(96) + UserID(32) + Token(32)
         */
        byte[] msg = new byte[52 + 96 + 32 + 32 ];

        Utils.copy(msg, 0, version.getValue());
        Utils.copy(msg, 2, objectType.getValue());
        Utils.copy(msg, 4, command.getValue());
        Utils.copy(msg, 6, option.getValue());
        Utils.copy(msg, 8, pUserID.getValue());
        Utils.copy(msg, 36,uowID.getValue());

        return msg;
    }

    protected void checkCompleteness()
        throws ServiceMessageException
    {
        super.checkCompletness();

        if (pUserID == null) {
            throw new ServiceMessageException("P-USER-ID missing");
        }
    }

    public String toString()
    {
        return
        "[CmdServiceMessage\n" +
            "   [VERSION=" + this.version + "]\n" +
            "   [OBJECT-TYPE=" + this.objectType + "]\n" +
            "   [COMMAND=" + this.command + "]\n" +
            "   [OPTION=" + this.option + "]\n" +
            "   [P-USER-ID=" + this.pUserID + "]\n" +
            "   [UOWID=" + this.uowID + "]\n" +
        "]";
    }
}