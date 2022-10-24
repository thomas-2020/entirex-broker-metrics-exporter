package com.softwareag.entirex.cis.params;

public class ConvID
    extends AbstractAlphanumericRequestParam
{
    public ConvID(String sConvID)
    {
        super(sConvID, 32);
    }
}