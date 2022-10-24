package com.softwareag.entirex.cis.params;

public abstract class AbstractBinaryRequestParam
    extends AbstractRequestParam
{
    protected AbstractBinaryRequestParam()
    {
    }

    protected AbstractBinaryRequestParam(byte[] abSetting)
    {
        super(abSetting);
    }

    public byte[] getValue()
    {
        return abSetting;
    }
}