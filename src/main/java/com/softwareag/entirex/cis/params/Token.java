package com.softwareag.entirex.cis.params;

public class Token
    extends AbstractAlphanumericRequestParam
{
    public Token(String sToken)
    {
        super(sToken, 32);
    }
}