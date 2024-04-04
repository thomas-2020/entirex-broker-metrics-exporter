/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Projekt: Systemmanagement
 *
 * Change history:
 *
 * Nr ! Datum    ! Name            ! Change reason
 * -----------------------------------------------------------------------------
 *  1 ! 11.05.04 ! Rupp            ! neu erstellt
 *
 */

package com.softwareag.entirex.cis;

import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.objects.*;

/**
 * This class encapsulates the response of an InfoService request. It inherits
 * from AbstractServiceResponse and thus implements the interface
 * IServiceResponse. The class provides you 2 methods to access the information
 * of the response (see method documentation).
 * 
 * @author Andreas Hitzbleck
 */
public class InfoServiceResponse extends AbstractServiceResponse
	{
	private IServiceResponseObject[] aoResponseObjects;

	/**
	 * Default constructor.
	 * 
	 * @param abResponse
	 *            The response (byte array) from the broker.
	 * @param objectType
	 *            The object type of the message wend as request.
	 * @throws ServiceResponseException
	 *             If the response is malformed.
	 */
	public InfoServiceResponse(byte[] abResponse, ObjectType objectType)
		throws Throwable
		{
		super( abResponse );

		getCommonHeader( ).setInterfaceVersion(
				objectType.getInterfaceVersion( ) );
		int iCurNumObjs = getCommonHeader( ).getCurrentNumObjects( );
		if (iCurNumObjs == 0)
			return;

		int iHeaderLen = getCommonHeader( ).getLength( );
		int iBlockLen = (int) ( abResponse.length - iHeaderLen ) / iCurNumObjs;

		aoResponseObjects = new IServiceResponseObject[iCurNumObjs];

		for (int i = 0; i < aoResponseObjects.length; i++)
			{
			aoResponseObjects[i] = createResponseObject( objectType, ( i * iBlockLen + iHeaderLen ), iBlockLen );
			}
		}

	/**
	 * Creates the right response object for each of the bytes in the response.
	 * 
	 * @param objectType
	 *            The object type of the request send to the broker.
	 * @param iOff
	 *            The offset to start in the response (byte array).
	 * @param iLen
	 *            The length to recognize in the response (byte array).
	 * @return The interface of the newly created response object.
	 * @throws ServiceResponseException
	 *             If something went wrong.
	 */
	private IServiceResponseObject createResponseObject( ObjectType objectType, int iOff, int iLen )
		throws ServiceResponseException
		{
		IServiceResponseObject back = null;
		Class c = objectType.getClassFor( );
		if (c != null)
			{
			try
				{
				back = (IServiceResponseObject) c.newInstance( );
				back.init( abResponse, iOff, iLen );
				}
			catch (Throwable e)
				{
				throw new ServiceResponseException(
						"Cannot create instance for object type ["
								+ c.getName( ) + "]" + " at offset=" + iOff
								+ " length=" + iLen + " in buffer length="
								+ abResponse.length + " exception="
								+ e.getMessage( ) );
				}
			}
		else
			{
			throw new ServiceResponseException( "Unknown object type ["
					+ objectType + "]" );
			}

		return back;
		}

	/**
	 * Returns the response object found at the given index. You can determine
	 * the number of objects in the current response by calling the method
	 * getCommonHeader().getCurrentNumObjects().
	 * 
	 * @param i
	 *            The index of the object to return.
	 * @return The response object found at the given index.
	 * @throws ArrayIndexOutOfBoundsException
	 *             If you pass a wrong index.
	 */
	public IServiceResponseObject getServiceResponseObject( int i )
		{
		return aoResponseObjects[i];
		}
	}