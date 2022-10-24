package com.softwareag.entirex.cis;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.objects.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Provides a filter to filter all UOWs of one specific service.
 * 
 * @author thr
 *
 */
public class FilterUOWObjectForService implements Query.Filter
	{
	protected String className;
	protected String serverName;
	protected String serviceName;
	
	/**
	 * Define a filter with class, server and service parameter.
	 * 
	 * @param className
	 * @param serverName
	 * @param serviceName
	 */
	public FilterUOWObjectForService( String className, String serverName, String serviceName )
		{
		this.className = className;
		this.serverName = serverName;
		this.serviceName = serviceName;
		}

	public FilterUOWObjectForService( String service )
		{
		StringTokenizer st = new StringTokenizer( service, "/" );
		if ( st.hasMoreElements() )
			className = st.nextToken();
		else
			className = "";

		if ( st.hasMoreElements() )
			serverName = st.nextToken();
		else
			serverName = "";

		if ( st.hasMoreElements() )
			serviceName = st.nextToken();
		else
			serviceName = "";
		}
	
	public boolean accept( AbstractServiceResponseObject o )
		{
		UOWObject   uow = (UOWObject) o;
		ConvObject conv = uow.getConversationObject();
		
		return conv.getClassName().equals( className ) && 
			conv.getServerName().equals( serverName ) &&
			conv.getServiceName().equals( serviceName );
		}
	}
