package com.softwareag.entirex.cis.params;

public class ServerName
    extends AbstractAlphanumericRequestParam
	{
	public ServerName(String sServerName)
		{
		super(sServerName, 32);
		}

	public byte[] getValue()
		{
		return getStringValueLeftAlignment();
		}
	}