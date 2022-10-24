package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.cis.params.*;

public interface ICommonHeader
{
    public int getLength();
    public int getErrorCode();
    public int getTotalNumObjects();
    public int getCurrentNumObjects();
    public int getMaxSCLen();
    public int getMaxSNLen();
    public int getMaxSVLen();
    public int getMaxUIDLen();
    public int getMaxTKLen();
	public void setInterfaceVersion( InterfaceVersion version );
}