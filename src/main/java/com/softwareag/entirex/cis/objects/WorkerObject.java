package com.softwareag.entirex.cis.objects;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.utils.*;
import java.math.*;

public class WorkerObject
    extends AbstractServiceResponseObject
{
    private static final int O_WORKER_ID = 0;
    private static final int L_WORKER_ID = 2;

    private static final int O_WORKER_STAT = 2;
    private static final int L_WORKER_STAT = 2;

    private static final int O_CALL_SUM = 4;
    private static final int L_CALL_SUM = 4;

    private static final int O_IDLE_SUM = 8;
    private static final int L_IDLE_SUM = 4;

    private static final int L_WORKER_OBJECT = 12;

    public WorkerObject()
	    {
    	}

    public WorkerObject(byte[] abResponse, int iOff, int iLen)
        throws ServiceResponseException
    {
        super(abResponse, iOff, iLen);
    }

    public int getLength()
    {
        return L_WORKER_OBJECT;
    }

    public int getWorkerID()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_WORKER_ID + iOff, L_WORKER_ID)).intValue();
    }

    public int getWorkerStat()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_WORKER_STAT + iOff, L_WORKER_STAT)).intValue();
    }

    public int getCallSum()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_CALL_SUM + iOff, L_CALL_SUM)).intValue();
    }

    public int getIdleSum()
    {
        return new BigInteger(Utils.getSubArray(abResponse, O_IDLE_SUM + iOff, L_IDLE_SUM)).intValue();
    }

    public String toString()
    {
        return
            "[WorkerObject=\n" +
            "   WORKER-ID  : " + getWorkerID() + "\n" +
            "   WORKER-STAT: " + getWorkerStat() + "\n" +
            "   CALL-SUM   : " + getCallSum() + "\n" +
            "   IDLE-SUM   : " + getIdleSum() + "\n" +
            "]";
    }
}