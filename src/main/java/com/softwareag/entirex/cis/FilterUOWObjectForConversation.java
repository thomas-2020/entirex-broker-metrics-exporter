package com.softwareag.entirex.cis;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.objects.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Iterator;


public class FilterUOWObjectForConversation implements Query.Filter
	{
	private Hashtable conversationHashtable;
	private String    userID;
		
	public FilterUOWObjectForConversation( List list )
		{
		conversationHashtable = new Hashtable( list.size() );
		for ( Iterator i = list.iterator(); i.hasNext(); )
			{
			ConvObject co = (ConvObject) i.next();			
			conversationHashtable.put( co.getConversationID(), co );
			}
		}

	public FilterUOWObjectForConversation( List list, String userID )
		{
		this( list );
		this.userID = userID;
		}

	public boolean accept( AbstractServiceResponseObject o )
		{
		UOWObject uow = (UOWObject) o;
		
		return conversationHashtable.get( uow.getConversationID() ) != null
			||
			( userID != null && uow.getSenderUserID().equals( userID ) );
		}
	}
