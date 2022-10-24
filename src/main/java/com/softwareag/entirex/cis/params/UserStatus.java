package com.softwareag.entirex.cis.params;

public class UserStatus
    extends AbstractAlphanumericRequestParam
{
    public UserStatus(String sUserStatus)
    {
        super(sUserStatus, 32);
    }
}
