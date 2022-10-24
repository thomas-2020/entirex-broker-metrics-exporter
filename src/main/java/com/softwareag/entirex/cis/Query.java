/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Project: Systemmanagement
 *
 * Change list:
 *
 * Nr ! Datum    ! Name            ! Description
 * -----------------------------------------------------------------------------
 *  1 ! 11.05.04 ! Rupp            ! created
 *
 */

package com.softwareag.entirex.cis;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.objects.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * This class provides a lot of utility functions for accessing
 * the EntireX command and info services.
**/
public class Query
	{
	/**
	 * Use the remembered Broker object.
	**/
	private Broker broker;

	/**
	 * Set the Broker object for all subsequent calls.
	 * Prerequisit is to do a logon to the EntireX Broker
	 * before this object is passed and remembered by this object.
	**/
	public Query( Broker broker )
		{
		this.broker = broker;
		}

	/**
	 * Returns a list of Broker objects of a specific type.
	**/
	public List getAllObjects( ObjectType type )
		throws Throwable
		{
		InfoServiceMessage info = new InfoServiceMessage();
		info.setObjectType( type );

		return getObjects( info, null );
		}

	public List getObjects( InfoServiceMessage info )
		throws Throwable
		{
		return getObjects( info, null );
		}

	public List getObjects( InfoServiceMessage info, Query.Filter filter )
		throws Throwable
		{
		info.setInterfaceVersion( InterfaceVersion.VERSION_2 );
		info.setBlockLength( new BlockLength( 7000 ) );

		LinkedList back = new LinkedList();

		ServiceRequest   req = new ServiceRequest( broker, info );
		IServiceResponse res = req.sendReceive();
		do
			{
            for (int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++)
            	{
            	IServiceResponseObject o = res.getServiceResponseObject( i );
            	if ( filter == null || filter.accept( (AbstractServiceResponseObject) o ) )
            		back.add( o );
            	}

            res = req.receive();
			} while ( res != null && res.getCommonHeader().getCurrentNumObjects() > 0 );

		return back;
		}

	public List getObjects( ObjectType type, Query.Filter filter )
		throws Throwable
		{
		InfoServiceMessage info = new InfoServiceMessage();
		info.setObjectType( type );

		return getObjects( info, filter );
		}


	public interface Filter
		{
		public boolean accept( AbstractServiceResponseObject o );
		}


	/**
	 * Returns a list of conversations which created by the logged on user.
	**/
	public List getConversationsForUserID()
		throws Throwable
		{
		return doGetConvsAndUOWsForUserId( true )[ 0 ];
		}

	/**
	 * Returns a list of UOWs which created by the logged on user.
	**/
	public List getUOWObjectsForUserID()
		throws Throwable
		{
		return doGetConvsAndUOWsForUserId( true )[ 1 ];
		}

	private List[] doGetConvsAndUOWsForUserId( boolean doLink )
		throws Throwable
		{
		Query.Filter filter;
		    filter = new FilterConversationForUserID( broker.getUserID() ) ;
		List convs = getObjects( ObjectType.CONV, filter );
		    filter = new FilterUOWObjectForConversation( convs, broker.getUserID() );
		List  uows = getObjects( ObjectType.UOW, filter );

		if ( doLink )
			doLinkObjects( convs, uows );

		List[] back = new List[ 2 ];
		back[ 0 ] = convs;
		back[ 1 ] = uows;
		return back;

		}

	private void doLinkObjects( List conversations, List uows )
		{
		Hashtable convHashtable = new Hashtable();
		for ( Iterator i = conversations.iterator(); i.hasNext(); )
			{
			ConvObject c = (ConvObject) i.next();
			convHashtable.put( c.getConversationID(), c );
			}

		for ( Iterator i = uows.iterator(); i.hasNext(); )
			{
			UOWObject  u = (UOWObject) i.next();
			ConvObject c = (ConvObject) convHashtable.get( u.getConversationID() );

			// Verlinke nur dann, falls zur UOW noch eine Conversation existiert
			if ( c != null )
				{
				u.setConversationObject( c );
				c.addUnitOfWorkObject( u );
				}
			}
		}


	/**
	 * Sends a 'shutdown' message to all server replicates of this service.
	**/
	public List doShutdown( BrokerService service )
		throws Throwable
		{
		LinkedList back         = new LinkedList();
		InfoServiceMessage info = new InfoServiceMessage();
		info.setInterfaceVersion( InterfaceVersion.VERSION_2 );
		info.setBlockLength     ( new BlockLength( 7200 ) );
		info.setObjectType      ( ObjectType.SERVER );

		info.setServerClass( new ServerClass( service.getServerClass() ) );
		info.setServerName ( new ServerName ( service.getServerName()  ) );
		info.setService    ( new Service    ( service.getServiceName() ) );

		ServiceRequest req = new ServiceRequest( broker, info );
		IServiceResponse res = req.sendReceive();

		CmdServiceMessage cmd = new CmdServiceMessage();
		cmd.setInterfaceVersion( InterfaceVersion.VERSION_2 );
		cmd.setObjectType      ( ObjectType.SERVER );
		cmd.setCommand         ( Command.SHUTDOWN );
		cmd.setOption          ( Option.IMMED );

		do
			{
			for ( int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++)
				{
				back.add( res.getServiceResponseObject( i ) );

				cmd.setPUserID( new PUserID( ( (ClientServerObject) res.getServiceResponseObject( i ) ).getPUserID() ) );
				ServiceRequest cmdreq = new ServiceRequest( broker, cmd );
				IServiceResponse cmdres = cmdreq.sendReceive();

				if ( cmdres.getCommonHeader().getErrorCode() == 0 )
					{
					//Erfolreich
					}
				else
					{
					//Fehler
					}
				}

			res = req.receive();
			}
			while ( res != null && res.getCommonHeader().getCurrentNumObjects() > 0 );

		return back;
		}

	/**
	 * Cancel the UOW with <code>uowID</code>. Don't use EntireX Security
	**/
	public UOWObject doCancelUOW( String uowID )
		throws Throwable
		{
		return doCancelUOW( uowID, null, null );
		}
	
	/**
	 * Cancel the UOW with <code>uowID</code>. The UOW must be created by logged on
	 * user ID in the Broker object. For EntireX Security, you must set <code>userID</code>
	 * and <code>password</code>.
	**/
	public UOWObject doCancelUOW( String uowID, String userID, String password )
		throws Throwable
		{
		Query.Filter filter = new FilterUOWObjectByID( uowID );;
		List           uows = getObjects( ObjectType.UOW, filter );

		if ( uows == null || uows.size() == 0 )
			throw new Exception( "UnitOfWork not found. ID=" + uowID );

		List convs = getAllObjects( ObjectType.CONV );
		doLinkObjects( convs, uows );

		UOWObject  uowObject  = (UOWObject) uows.get( 0 );
		ConvObject convObject = uowObject.getConversationObject();
		Broker        broker2 = new Broker( broker.getBrokerID(), convObject.getClientUserID(), convObject.getClientToken() );
		if ( userID != null &&
		     userID.length() > 0 && 
		     userID.equalsIgnoreCase( convObject.getClientUserID() ) && 
		     password != null &&
		     password.length() > 0 )
			{
			broker2.useEntireXSecurity();
			broker2.logon( password );
			}
		else
			{
			broker2.logon();
			}
		UnitofWork        uow = doGetUOW( uowID, broker2 );
		uow.cancel();

		return uowObject;
		}

	/**
	 * Cancel all UOWs of service without EntireX Security
	 * @param service
	 * @return
	 * @throws Throwable
	 */
	public List doCancelUOWsOfService( String service )
		throws Throwable
		{
		return doCancelUOWsOfService( service, null, null );
		}

	/**
	 * Cancel all UOWs of service
	 * @param service
	 * @return
	 * @throws Throwable
	 */
	public List doCancelUOWsOfService( String service, String userID, String password )
		throws Throwable
		{
		Query.Filter filter = new FilterUOWObjectForService( service );
		List           uows = getAllObjects( ObjectType.UOW );
		List          convs = getAllObjects( ObjectType.CONV );

		if ( uows == null || uows.size() == 0 )
			throw new Exception( "No UnitOfWork exists" );

		doLinkObjects( convs, uows );
		doPerformFilter( uows, filter );

		for ( int i = 0; i < uows.size(); i++ )
			{
			UOWObject  uowObject  = (UOWObject) uows.get( i );
			ConvObject convObject = uowObject.getConversationObject();
			Broker        broker2 = new Broker( broker.getBrokerID(), convObject.getClientUserID(), convObject.getClientToken() );
			if ( userID != null &&
				     userID.length() > 0 && 
				     userID.equalsIgnoreCase( convObject.getClientUserID() ) && 
				     password != null &&
				     password.length() > 0 )
					{
					broker2.useEntireXSecurity();
					broker2.logon( password );
					}
				else
					{
					broker2.logon();
					}
			UnitofWork        uow = doGetUOW( uowObject.getUnitOfWorkID(), broker2 );
			uow.cancel();
			}

		return uows;
		}

	private void doPerformFilter( List list, Query.Filter filter )
		{
		for ( int i = ( list.size() - 1 ); i >= 0; i-- )
			{
			if ( ! filter.accept( (AbstractServiceResponseObject) list.get( i ) ) )
				list.remove( i );
			}
		}

	public static UnitofWork doGetUOW( String id, Broker broker )
		throws BrokerException
		{
		UnitofWork uow = null;
		try
			{
			//Try with EntireX 7
			uow = UnitofWork.query( id, broker );
			}

		catch ( IllegalArgumentException e )
			{
			//Try with EntireX 6
			BrokerService service = new BrokerService( broker, "DUMMY/DUMMY/DUMMY" );
			uow = UnitofWork.query( id, service );
			}

		return uow;
		}

	/**
	 * Returns the last created UOW of this <code>broker</code>.
	 * The logged on user is used.
	**/
	public static UnitofWork doGetLastUOW( Broker broker )
		throws BrokerException
		{
		UnitofWork uow = null;
		try
			{
			//Try with EntireX 7
			uow = UnitofWork.queryLast( broker );
			}

		catch ( IllegalArgumentException e )
			{
			//Try with EntireX 6
			BrokerService service = new BrokerService( broker, "DUMMY/DUMMY/DUMMY" );
			uow = UnitofWork.queryLast( service );
			}

		return uow;
		}


	/**
	 * Purge the status of one UOW.
	**/
	public boolean doPurgeUOWStatus( String uowID )
		throws Throwable
		{
		CmdServiceMessage cmd = new CmdServiceMessage();
		cmd.setInterfaceVersion( InterfaceVersion.VERSION_2 );
		cmd.setObjectType      ( ObjectType.UOW );
		cmd.setCommand         ( Command.PURGE );
		cmd.setUOWID           ( new UOWID( uowID ) );
		cmd.setOption          ( Option.DEFAULT );

		ServiceRequest cmdreq = new ServiceRequest( broker, cmd );
		IServiceResponse cmdres = cmdreq.sendReceive();
		if ( cmdres.getCommonHeader().getErrorCode() == 0 )
			{
			return true;
			}
		else
			{
			return false;
			}
		}

	/**
	 * Implements <code>doShutdown()</code> for shutdown all
	 * server of a services. First parameter is the Broker ID 
	 * and the second parameter is the services name.
	**/
	public static void main( String[] args )
		{
		try
			{
			Broker broker = new Broker( args[ 0 ], args[ 1 ] );
			broker.logon();

			Query query = new Query( broker );
			query.doShutdown( new BrokerService( broker, args[ 2 ] ) );
			}

		catch( Throwable e )
			{
			e.printStackTrace();
			}
		}

	} //End of class