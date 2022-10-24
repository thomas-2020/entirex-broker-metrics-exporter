package com.softwareag.entirex.cis.params;

public class UOWStatus
    extends AbstractAlphanumericRequestParam
	{
	public UOWStatus( String sUOWStatus )
		{
		super(sUOWStatus, 1);
		}

	public static String toString( int status )
		{
		switch ( status )
			{
			case 0: return "recv_none";
			case 1: return "received";
			case 2: return "accepted";
			case 3: return "delivered";
			case 4: return "backedout";
			case 5: return "processed";
			case 6: return "cancelled";
			case 7: return "timeout";
			case 8: return "discarded";
			case 9: return "recv_first";
			case 10: return "recv_middle";
			case 11: return "recv_last";
			case 12: return "recv_only";
			}    
		
		return "";
		}
	}
