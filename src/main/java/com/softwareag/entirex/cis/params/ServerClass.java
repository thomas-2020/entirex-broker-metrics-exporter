package com.softwareag.entirex.cis.params;

public class ServerClass
    extends AbstractAlphanumericRequestParam
	{
	public ServerClass(String sServerClass)
		{
	    super(sServerClass, 32);
		}
	
	public byte[] getValue()
		{
		return getStringValueLeftAlignment();
		}
	}