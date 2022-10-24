package com.softwareag.entirex.cis;

import com.softwareag.entirex.cis.params.*;

/**
 * This interface defines the methods all message encapsulating classes
 * must implement.
 *
 * @author Andreas Hitzbleck
 */
public interface IServiceMessage
{
    public ObjectType getObjectType();
    public byte[] getMessage() throws ServiceMessageException;
}