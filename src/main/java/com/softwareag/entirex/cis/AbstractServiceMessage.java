package com.softwareag.entirex.cis;

import com.softwareag.entirex.cis.params.*;

/**
 * This class encapsulates the message of an any service request.
 * It implements the interface IServiceMessage. See the inheriting
 * classes for details.
 *
 * @author Andreas Hitzbleck
 */
public abstract class AbstractServiceMessage
    implements IServiceMessage
{
    protected InterfaceVersion version = null;
    protected ObjectType objectType = null;
    protected PUserID pUserID = new PUserID(new byte[28]);
    protected UOWID uowID = new UOWID("");

    public void setInterfaceVersion(InterfaceVersion version)
    {
        this.version = version;
    }

	public void setInterfaceVersion( ObjectType objectType )
		throws Throwable
		{
		this.version = InterfaceVersion.getFor( objectType );
		}

    public void setObjectType(ObjectType objectType)
    {
        this.objectType = objectType;
    }

    public ObjectType getObjectType()
    {
        return objectType;
    }

    public void setPUserID(PUserID pUserID)
    {
        this.pUserID = pUserID;
    }

    public void setUOWID(UOWID uowID)
    {
        this.uowID = uowID;
    }

    protected void checkCompletness()
        throws ServiceMessageException
    {
        if (version == null) {
            throw new ServiceMessageException("VERSION missing");
        }

        if (objectType == null) {
            throw new ServiceMessageException("OBJECT-TYPE missing");
        }
    }
}