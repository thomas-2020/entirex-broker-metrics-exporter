package com.softwareag.entirex.cis.params;

public abstract class AbstractRequestParam
    implements IRequestParam
{
    protected byte[] abSetting;

    protected AbstractRequestParam()
    {
    }

    protected AbstractRequestParam(byte[] abSetting)
    {
        this.abSetting = abSetting;
    }

    protected void setValue(byte[] abSetting)
    {
        this.abSetting = abSetting;
    }

    public abstract byte[] getValue();
}
