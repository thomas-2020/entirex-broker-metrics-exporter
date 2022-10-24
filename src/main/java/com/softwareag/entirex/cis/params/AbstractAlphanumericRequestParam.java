package com.softwareag.entirex.cis.params;

public abstract class AbstractAlphanumericRequestParam
    extends AbstractRequestParam
	{
	protected int iLen;
	
	protected AbstractAlphanumericRequestParam()
		{
		}
	
	protected AbstractAlphanumericRequestParam(String sValue, int iLen)
		{
		super(sValue.getBytes());
		this.iLen = iLen;
		}
	
	public byte[] getValue()
		{
		byte[] abResult = new byte[iLen];
	
		for (int i = 0; i < abSetting.length; i++)
			{
			abResult[abResult.length - abSetting.length + i] = abSetting[i];
			}
		
		return abResult;
		}
	
	protected byte[] getStringValueLeftAlignment()
		{
		byte[] abResult = new byte[iLen];
		
		int i;
		for (i = 0; i < abSetting.length; i++)
		    abResult[i] = abSetting[i];

		for ( ; i < iLen; i++ )
			abResult[i] = ' ';
		
		return abResult;
		}
	
	
	public String toString()
		{
		return new String( getValue() );
		}
	}