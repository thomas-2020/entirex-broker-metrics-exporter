package com.softwareag.entirex.cis;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.objects.*;

/**
 * This class encapsulates the response of an CmdService request.
 * It inherits from AbstractServiceResponse and thus implements the interface
 * IServiceResponse. The class provides you 2 methods to access the information
 * of the response (see method documentation).
 *
 * @author Andreas Hitzbleck
 */
public class CmdServiceResponse
    extends AbstractServiceResponse
{
    /**
     * Default constructor.
     *
     * @param abResponse The response of the request.
     * @throws ServiceResponseException If the response is malformed.
     */
    public CmdServiceResponse(byte[] abResponse)
        throws ServiceResponseException
    {
        super(abResponse);
    }

    /**
     * Always return null as the response of a CmdService request
     * only contains the common header.
     *
     * @param i
     * @return null.
     */
    public IServiceResponseObject getServiceResponseObject(int i)
    {
        return null;
    }
}