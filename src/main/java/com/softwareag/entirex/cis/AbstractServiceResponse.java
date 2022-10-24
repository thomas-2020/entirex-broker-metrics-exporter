package com.softwareag.entirex.cis;

import com.softwareag.entirex.cis.objects.*;
import com.softwareag.entirex.cis.params.*;

/**
 * This class encapsulates the response of an any service request.
 * It implements the interface IServiceResponse.
 *
 * @author Andreas Hitzbleck
 */
public abstract class AbstractServiceResponse
    implements IServiceResponse
{
    private ICommonHeader oCommonHeader;
    protected byte[] abResponse;

    /**
     * Default constructor.
     *
     * @param abResponse The response of the request.
     * @throws ServiceResponseException If the response is malformed.
     */
    public AbstractServiceResponse(byte[] abResponse)
        throws ServiceResponseException
    {
        this.abResponse = abResponse;
        oCommonHeader = new CommonHeader(abResponse);
    }

    /**
     * @return The common header of the response.
     */
    public ICommonHeader getCommonHeader()
    {
        return oCommonHeader;
    }
    
    public InterfaceVersion getInterfaceVersion()
    {
    	return null;
    }
}