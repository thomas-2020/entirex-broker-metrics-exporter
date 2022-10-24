/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Projekt: Systemmanagement
 *
 * Änderungshistorie:
 *
 * Nr ! Datum    ! Name            ! Änderungsgrund
 * -----------------------------------------------------------------------------
 *  1 ! 11.05.04 ! Rupp            ! neu erstellt
 *
 */

package com.softwareag.entirex.cis.params;

import com.softwareag.entirex.cis.*;
import com.softwareag.entirex.cis.objects.*;
import com.softwareag.entirex.cis.params.*;

public class ObjectType
    extends AbstractBinaryRequestParam
	{
    public static final ObjectType SERVER  = new ObjectType( new byte[] { (byte)0x00, (byte)0x01 } );
    public static final ObjectType CLIENT  = new ObjectType( new byte[] { (byte)0x00, (byte)0x02 } );
    public static final ObjectType CONV    = new ObjectType( new byte[] { (byte)0x00, (byte)0x04 } );
    public static final ObjectType SERVICE = new ObjectType( new byte[] { (byte)0x00, (byte)0x06 } );
    public static final ObjectType BROKER  = new ObjectType( new byte[] { (byte)0x00, (byte)0x07 } );
    public static final ObjectType WORKER  = new ObjectType( new byte[] { (byte)0x00, (byte)0x08 } );
    public static final ObjectType UOW     = new ObjectType( new byte[] { (byte)0x00, (byte)0x09 } );
    public static final ObjectType PSFDIV  = new ObjectType( new byte[] { (byte)0x00, (byte)0x0b } );
    public static final ObjectType PSFADA  = new ObjectType( new byte[] { (byte)0x00, (byte)0x0c } );

    public static final ObjectType PSFFILE     = new ObjectType( new byte[] { (byte)0x00, (byte) 13 } );
    public static final ObjectType SUBSCRIBER  = new ObjectType( new byte[] { (byte)0x00, (byte) 14 } );
    public static final ObjectType PUBLISHER   = new ObjectType( new byte[] { (byte)0x00, (byte) 15 } );
    public static final ObjectType PUBLICATION = new ObjectType( new byte[] { (byte)0x00, (byte) 16 } );
    public static final ObjectType TOPIC       = new ObjectType( new byte[] { (byte)0x00, (byte) 17 } );
    public static final ObjectType PARTICIPANT = new ObjectType( new byte[] { (byte)0x00, (byte) 18 } );

    private ObjectType(byte[] abSetting)
    	{
		super(abSetting);
    	}

	public Class getClassFor()
		{
		return getClassFor( this );
		}

	public InterfaceVersion getInterfaceVersion()
		throws Throwable
		{
		return ( (AbstractServiceResponseObject) getClassFor().newInstance() ).getInterfaceVersion();
		}

	public static Class getClassFor( ObjectType objectType )
		{
		if ( objectType == ObjectType.BROKER )
			return BrokerObject.class;
		else if ( objectType == ObjectType.CLIENT )
			return ClientServerObject.class;
		else if ( objectType == ObjectType.CONV )
			return ConvObject.class;
		else if ( objectType == ObjectType.UOW )
			return UOWObject.class;
		else if ( objectType == ObjectType.SERVER )
			return ClientServerObject.class;
		else if ( objectType == ObjectType.SERVICE )
			return ServiceObject.class;
		else if ( objectType == ObjectType.WORKER )
			return WorkerObject.class;
		else if ( objectType == ObjectType.PSFDIV )
			return PSFDIVObject.class;
		else if ( objectType == ObjectType.PSFADA )
			return PSFADAObject.class;
		else if ( objectType == ObjectType.SUBSCRIBER )
			return SubscriberObject.class;
		else if ( objectType == ObjectType.PUBLISHER )
			return PublisherObject.class;
		else if ( objectType == ObjectType.PUBLICATION )
			return PublicationObject.class;
		else if ( objectType == ObjectType.TOPIC )
			return TopicObject.class;

		throw new RuntimeException( "EXXCIS object type is not implemented" );
        }
	}
