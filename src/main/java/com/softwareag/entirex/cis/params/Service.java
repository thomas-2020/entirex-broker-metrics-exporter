package com.softwareag.entirex.cis.params;

public class Service
    extends AbstractAlphanumericRequestParam
	{
	public Service(String sService)
		{
		super(sService, 32);
		}

	public byte[] getValue()
		{
		return getStringValueLeftAlignment();
		}
	}