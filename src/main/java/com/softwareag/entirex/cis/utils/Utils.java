package com.softwareag.entirex.cis.utils;

/**
 * Some utils usefor when working with byte arrays.
 *
 * @author Andreas Hitzbleck
 */
public class Utils
{
    /**
     * Returns a sub-array of src, starting at off and ending at off + len.
     *
     * @param src The source array to extract the sub-array from.
     * @param off The offset to start copying.
     * @param len The length tp copy.
     * @return The extracted sub-array.
     */
    public static byte[] getSubArray (byte[] src, int off, int len)
    {
        byte[] dest = new byte[len];
        for (int i = 0; i < dest.length; i++) {
            dest[i] = src[off + i];
        }
        return dest;
    }

    /**
     * Copies bytes from src into dest, starting at off and ending at off + len.
     *
     * @param dest The array to copy the bytes to.
     * @param off The offset to start copying.
     * @param src The destination array to copy to.
     */
    public static void copy(byte[] dest, int off, byte[] src)
    {
        for (int i = 0; i < src.length; i++) {
            dest[off + i] = src[i];
        }
    }

    /**
     * Returns a human readable represantation of the given byte array. The
     * bytes will not be converted but only the digits will be returned as
     * string.
     *
     * @param abBytes The bytes to return as string.
     * @return A string representation of the giben bytes.
     */
    public static String bytesAsString(byte[] abBytes)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < abBytes.length; i++) {
            sb.append(convertDigit(abBytes[i] >> 4));
            sb.append(convertDigit(abBytes[i] & 0xf));
        }
        return sb.toString();
    }

    /**
     * Private little helper.
     *
     * @param iValue
     * @return
     */
    private static char convertDigit(int iValue)
    {
        iValue &= 0xf;
        return (iValue >= 10) ? (char)((iValue - 10) + 97) : (char)(iValue + 48);
    }
}