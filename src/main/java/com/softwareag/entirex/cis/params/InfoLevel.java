package com.softwareag.entirex.cis.params;

public class InfoLevel
    extends AbstractBinaryRequestParam
{
    public static final InfoLevel INFO_LEVEL = new InfoLevel();

    private InfoLevel()
    {
        super(new byte[]{0x00, 0x00});
    }
}
