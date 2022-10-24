package com.softwareag.entirex.cis.params;

import java.math.*;

public class BlockLength
    extends AbstractBinaryRequestParam
{
    public BlockLength(int iLen)
    {
        super();
        byte[] abBlockLen = new BigInteger(String.valueOf(iLen)).toByteArray();

        if (abBlockLen.length < 4) {
            byte[] abTmp = new byte[4];
            int iDif = 4 - abBlockLen.length;

            for (int i = 0; i < abBlockLen.length; i++) {
                abTmp[i + iDif] = abBlockLen[i];
            }

            abBlockLen = abTmp;
        }

        super.setValue(abBlockLen);
    }
}
