package com.softwareag.entirex.cis;

import com.softwareag.entirex.cis.objects.*;

/**
 * This interface defines the methods all response encapsulating classes
 * must implement.
 *
 * @author Andreas Hitzbleck
 */
public interface IServiceResponse
{
    public ICommonHeader getCommonHeader();
    public IServiceResponseObject getServiceResponseObject(int i);
}