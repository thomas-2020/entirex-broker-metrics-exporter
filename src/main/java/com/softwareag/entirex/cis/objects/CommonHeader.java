package com.softwareag.entirex.cis.objects;

import java.math.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.params.*;

public class CommonHeader implements ICommonHeader
	{
	/** O = offset; L = length */
	private static final int O_ERROR_CODE = 0;

	private static final int L_ERROR_CODE = 4;

	private static final int O_TOTAL_NUM_OBJECTS = 4;

	private static final int L_TOTAL_NUM_OBJECTS = 4;

	private static final int O_CURRENT_NUM_OBJECTS = 8;

	private static final int L_CURRENT_NUM_OBJECTS = 4;

	private static final int O_MAX_SC_LEN = 12;

	private static final int L_MAX_SC_LEN = 4;

	private static final int O_MAX_SN_LEN = 16;

	private static final int L_MAX_SN_LEN = 4;

	private static final int O_MAX_SV_LEN = 20;

	private static final int L_MAX_SV_LEN = 4;

	private static final int O_MAX_UID_LEN = 24;

	private static final int L_MAX_UID_LEN = 4;

	private static final int O_MAX_TK_LEN = 28;

	private static final int L_MAX_TK_LEN = 4;

	public static final int L_COMMON_HEADER_LENGTH = 32;

	private byte[] abHeader;

	private InterfaceVersion interfaceVersion;

	public CommonHeader(byte[] abHeader) throws ServiceResponseException
		{
		if (abHeader.length < L_COMMON_HEADER_LENGTH)
			{
			throw new ServiceResponseException(
					"Malformed common header (wrong size: [32 byte] expected but ["
							+ abHeader.length + " byte] received)" );
			}

		this.abHeader = abHeader;
		}

	protected byte[] getSubArray( byte[] src, int off, int len )
		{
		byte[] dest = new byte[len];
		for (int i = 0; i < dest.length; i++)
			{
			dest[i] = src[off + i];
			}
		return dest;
		}

	public int getLength()
		{
		if ( interfaceVersion == InterfaceVersion.VERSION_3 || interfaceVersion == InterfaceVersion.VERSION_2 || interfaceVersion == InterfaceVersion.VERSION_1 )
			return L_COMMON_HEADER_LENGTH;
		if ( interfaceVersion == InterfaceVersion.VERSION_4 )
			return L_COMMON_HEADER_LENGTH + 8;
		if ( interfaceVersion == InterfaceVersion.VERSION_5 )
			return L_COMMON_HEADER_LENGTH + 8 + 8 + 40;

		//Higher than 5 
		return L_COMMON_HEADER_LENGTH + 8 + 8 + 40 + 4 + 4 + 4;
		}

	public int getErrorCode()
		{
		return new BigInteger( getSubArray( abHeader, O_ERROR_CODE,
				L_ERROR_CODE ) ).intValue( );
		}

	public int getTotalNumObjects()
		{
		return new BigInteger( getSubArray( abHeader, O_TOTAL_NUM_OBJECTS,
				L_TOTAL_NUM_OBJECTS ) ).intValue( );
		}

	public int getCurrentNumObjects()
		{
		return new BigInteger( getSubArray( abHeader, O_CURRENT_NUM_OBJECTS,
				L_CURRENT_NUM_OBJECTS ) ).intValue( );
		}

	public int getMaxSCLen()
		{
		return new BigInteger( getSubArray( abHeader, O_MAX_SC_LEN,
				L_MAX_SC_LEN ) ).intValue( );
		}

	public int getMaxSNLen()
		{
		return new BigInteger( getSubArray( abHeader, O_MAX_SN_LEN,
				L_MAX_SN_LEN ) ).intValue( );
		}

	public int getMaxSVLen()
		{
		return new BigInteger( getSubArray( abHeader, O_MAX_SV_LEN,
				L_MAX_SV_LEN ) ).intValue( );
		}

	public int getMaxUIDLen()
		{
		return new BigInteger( getSubArray( abHeader, O_MAX_UID_LEN,
				L_MAX_UID_LEN ) ).intValue( );
		}

	public int getMaxTKLen()
		{
		return new BigInteger( getSubArray( abHeader, O_MAX_TK_LEN,
				L_MAX_TK_LEN ) ).intValue( );
		}

	public String toString()
		{
		return "[CommonHeader=\n" + "   ERROR-CODE         : " + getErrorCode( )
				+ "\n" + "   TOTAL-NUM-OBJECTS  : " + getTotalNumObjects( )
				+ "\n" + "   CURRENT-NUM-OBJECTS: " + getCurrentNumObjects( )
				+ "\n" + "   MAX-SC-LEN         : " + getMaxSCLen( ) + "\n"
				+ "   MAX-SN-LEN         : " + getMaxSNLen( ) + "\n"
				+ "   MAX-SV-LEN         : " + getMaxSVLen( ) + "\n"
				+ "   MAX-UID-LEN        : " + getMaxUIDLen( ) + "\n"
				+ "   MAX-TK-LEN         : " + getMaxTKLen( ) + "\n" + "]";
		}

	public void setInterfaceVersion( InterfaceVersion version )
		{
		interfaceVersion = version;
		}
	}