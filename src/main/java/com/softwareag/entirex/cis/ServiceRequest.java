/*
 * Copyright (c) SAG Systemhaus GmbH
 *
 * Projekt: EntireX Broker Command and Info Services Interface
 *
 * Change history:
 *
 * Nr ! Datum    ! Name            ! Änderungsgrund
 * -----------------------------------------------------------------------------
 *  1 ! 03.04.03 ! Rupp            ! created
 *
 */

package com.softwareag.entirex.cis;

import com.softwareag.entirex.aci.*;
import com.softwareag.entirex.cis.params.*;
import com.softwareag.entirex.cis.objects.*;

/**
 * This is the class you can use to create and send service requests to
 * the entirex broker. It takes a broker object and a service message, sends
 * the message to the broker and provides you an object which encapsulates
 * the response.<br>
 * <br>
 * <u>Example: list all serves registered at the broker</u><br>
 * <p><pre>
 *      // Create a broker object
 *      Broker broker = new Broker("127.0.0.1:7000", "test");
 *      broker.logon();
 *
 *      // Create an InfoServiceMessage and set some values
 *      InfoServiceMessage info = new InfoServiceMessage();
 *      info.setInterfaceVersion(InterfaceVersion.VERSION_2);
 *      info.setBlockLength(new BlockLength(7200));
 *      info.setObjectType(ObjectType.SERVER);
 *
 *      // Now we can create and send the request
 *      ServiceRequest req = new ServiceRequest(broker, info);
 *      IServiceResponse res = req.sendReceive();
 *
 *      // We want to display the result to STDOUT
 *      for (int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++) {
 *          System.out.println("SERVER [" + i + "] : " + res.getServiceResponseObject(i));
 *      }
 * </pre></p>
 *
 * That's it. You get a list of objects which encapsulates all informations
 * about every server registered at the broker. You can access one object via
 * it's index (res.getServiceResponseObject(i)) and if you know what object type
 * you've used, you can cast the object to the right type:
 *
 * <p><pre>
 *      ClientServerObject server = (ClientServerObject)res.getServiceResponseObject(0);
 *      System.out.println(server.getUserID());
 *      ...
 * </pre></p>
 *
 * Please read the entirex documentation to understand what all theses classes
 * are wrapping. If you have any questions, feel free to contact me at my
 * Software AG's <a href="mailto:andreas.hitzbleck@softwareag.com">mail address</a>.
 *
 * @author Andreas Hitzbleck
 */
public class ServiceRequest
{
    private Broker oBroker;
    private IServiceMessage oMsg;
    private BrokerService   oInfoBService;
    private Conversation    oInfoConversation;

    /**
     * Default constructor.
     *
     * @param oBroker The broker to talk with.
     * @param oMsg The message to send to the broker.
     */
    public ServiceRequest(Broker oBroker, IServiceMessage oMsg)
    {
        this.oBroker       = oBroker;
        this.oMsg          = oMsg;
        this.oInfoBService = new BrokerService(oBroker, "SAG/ETBCIS/INFO");
    }

    /**
     * Sends and receives the defined message to the defined broker.
     *
     * @return The response of this request.
     * @throws ServiceResponseException If something went wrong.
     */
    public IServiceResponse sendReceive()
        throws Throwable
    {
        try {
            if (oMsg instanceof CmdServiceMessage) {
                return sendCmdServiceRequest();
            } else if (oMsg instanceof InfoServiceMessage) {
                return sendInfoServiceRequest();
            } else {
                throw new Exception("Unknown message type");
            }
        } catch(Exception e) {
            throw new ServiceResponseException("Unable to send/receive request", e);
        }
    }

    /**
     * Sends and receives the defined message to the defined broker.
     *
     * @return The response of this request.
     * @throws ServiceResponseException If something went wrong.
     */
    public IServiceResponse receive()
        throws Throwable
    {
        try {
            if (oMsg instanceof InfoServiceMessage) {
                return receiveInfoServiceRequest();
            } else {
                throw new Exception("Unknown message type");
            }
        } catch(Exception e) {
            throw new ServiceResponseException("Unable to send/receive request", e);
        }
    }

    /**
     * Sends and receives an InfoServiceRequestMessage to the broker.
     *
     * @return The response of this request.
     * @throws ServiceResponseException If something went wrong.
     * @throws ServiceMessageException If something went wrong.
     * @throws BrokerException If something went wrong.
     */
    private IServiceResponse sendInfoServiceRequest()
        throws Throwable
    {
        oInfoConversation        = new Conversation(oInfoBService);
        BrokerMessage bmResponse = oInfoConversation.sendReceive(new BrokerMessage(oMsg.getMessage()));
        //oConv.end();
        return new InfoServiceResponse(bmResponse.getMessage(), oMsg.getObjectType());
    }

    /**
     * Receives an InfoServiceRequestMessage to the broker.
     *
     * @return The response of this request.
     * @throws ServiceResponseException If something went wrong.
     * @throws ServiceMessageException If something went wrong.
     * @throws BrokerException If something went wrong.
     */
    private IServiceResponse receiveInfoServiceRequest()
        throws Throwable
    	{
        BrokerMessage bmResponse = oInfoConversation.receive();
        if ( bmResponse != null )
        	{
        	return new InfoServiceResponse(bmResponse.getMessage(), oMsg.getObjectType());
        	}
        else
        	{
			try
				{
        		oInfoConversation.end();
        		}
        	catch ( BrokerException e )
        		{
        		if ( e.getErrorClass() == 3 )
        			return null;
        		else
        			throw e;
        		}
        	return null;
        	}
    	}

    /**
     * Sends an CmdServiceRequestMessage to the broker.
     *
     * @return The response of this request.
     * @throws ServiceResponseException If something went wrong.
     * @throws ServiceMessageException If something went wrong.
     * @throws BrokerException If something went wrong.
     */
    private IServiceResponse sendCmdServiceRequest()
        throws ServiceResponseException, ServiceMessageException, BrokerException
    {
        BrokerService oBService = new BrokerService(oBroker, "SAG/ETBCIS/CMD");
        BrokerMessage bmResponse = oBService.sendReceive(new BrokerMessage(oMsg.getMessage()));
        return new CmdServiceResponse(bmResponse.getMessage());
    }


    /**
     * Used for internet tests only.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args)
        throws Throwable
    {
        try {
            Broker broker = new Broker("localhost:1971", "thr");
            //broker.setSecurity(new EntireXSecurity(), 0);
            //broker.useEntireXSecurity(1);
            broker.logon("sagensag");

            InfoServiceMessage info = new InfoServiceMessage();
            info.setInterfaceVersion(InterfaceVersion.VERSION_2);
            //info.setBlockLength(new BlockLength(7200));
            info.setBlockLength(new BlockLength(600));
            info.setObjectType(ObjectType.UOW);
            //info.setObjectType(ObjectType.CONV);

            ServiceRequest req = new ServiceRequest(broker, info);
            IServiceResponse res = req.sendReceive();

            CmdServiceMessage cmd = new CmdServiceMessage();
            cmd.setInterfaceVersion(InterfaceVersion.VERSION_1);
            cmd.setObjectType(ObjectType.SERVER);
            cmd.setCommand(Command.SHUTDOWN);
            cmd.setOption(Option.IMMED);

			do
				{
	            for (int i = 0; i < res.getCommonHeader().getCurrentNumObjects(); i++)
	            	{
	                System.out.println("SERVER [" + i + "] : " + res.getServiceResponseObject(i));
	                /*
	                if (res.getServiceResponseObject(i).getUserID().equalsIgnoreCase("SAGENX")) {
	                    System.out.print("shutting down server [" + i + "]...");
	                    cmd.setPUserID(new PUserID(res.getServiceResponseObject(i).getPUserID()));
	                    ServiceRequest cmdreq = new ServiceRequest(broker, cmd);
	                    IServiceResponse cmdres = cmdreq.sendReceive();
	
	                    if (cmdres.getCommonHeader().getErrorCode() == 0) {
	                        System.out.println("done.");
	                    } else {
	                        System.out.println("failed.");
	                    }
	                }
	                */
		            }

                res = req.receive();
				} 
				//while( false ); 
				while ( res != null && res.getCommonHeader().getCurrentNumObjects() > 0 );

            broker.logoff();
            broker.disconnect();
        } catch(ServiceResponseException e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("Nested exception: " + e.getThrowable());
            e.getThrowable().printStackTrace();
        }
    }
}