package com.softwareag.entirex.cis.params;

public class Reserved
    extends AbstractBinaryRequestParam
{
    public static final Reserved RESERVED = new Reserved();

    private Reserved()
    {
        super(new byte[]{0x00, 0x00});
    }
}
