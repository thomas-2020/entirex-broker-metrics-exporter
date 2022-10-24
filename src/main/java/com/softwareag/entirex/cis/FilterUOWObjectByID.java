package com.softwareag.entirex.cis;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.objects.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Iterator;


public class FilterUOWObjectByID implements Query.Filter
	{
	private Hashtable conversationHashtable;
	private String    id;
		
	public FilterUOWObjectByID( String id )
		{
		this.id = id;
		}

	public boolean accept( AbstractServiceResponseObject o )
		{
		UOWObject uow = (UOWObject) o;
		
		return uow.getUnitOfWorkID().equals( id );
		}
	}
