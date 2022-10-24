package com.softwareag.entirex.cis;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.objects.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Iterator;


public class FilterConversationForUserID implements Query.Filter
	{
	private String userID;
		
	public FilterConversationForUserID( String userID )
		{
		this.userID = userID;
		}

	public boolean accept( AbstractServiceResponseObject o )
		{
		ConvObject c = (ConvObject) o;
		
		return c.getClientUserID().equals( userID ) || c.getServerUserID().equals( userID );
		}
	}
