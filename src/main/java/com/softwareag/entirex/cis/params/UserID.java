package com.softwareag.entirex.cis.params;

public class UserID
    extends AbstractAlphanumericRequestParam
{
    public UserID(String sUserID)
    {
        super(sUserID, 32);
    }

}