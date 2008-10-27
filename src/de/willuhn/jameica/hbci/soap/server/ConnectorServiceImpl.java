/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/server/Attic/ConnectorServiceImpl.java,v $
 * $Revision: 1.4 $
 * $Date: 2008/10/27 23:41:43 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.server;

import java.rmi.RemoteException;

import de.willuhn.jameica.hbci.soap.rmi.ConnectorService;
import de.willuhn.jameica.hbci.soap.service.impl.KontoServiceImpl;
import de.willuhn.jameica.hbci.soap.service.impl.LastschriftServiceImpl;
import de.willuhn.jameica.hbci.soap.service.impl.SammelLastschriftServiceImpl;
import de.willuhn.jameica.hbci.soap.service.impl.SammelUeberweisungServiceImpl;
import de.willuhn.jameica.hbci.soap.service.impl.UeberweisungServiceImpl;
import de.willuhn.jameica.hbci.soap.service.impl.UmsatzServiceImpl;
import de.willuhn.jameica.messaging.Message;
import de.willuhn.jameica.messaging.MessageConsumer;
import de.willuhn.jameica.messaging.QueryMessage;
import de.willuhn.jameica.messaging.SystemMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;


/**
 * Implementiert den Connector-Service.
 */
public class ConnectorServiceImpl implements ConnectorService
{
  private boolean started = false;

  /**
   * @see de.willuhn.datasource.Service#getName()
   */
  public String getName() throws RemoteException
  {
    return "soap connector";
  }

  /**
   * @see de.willuhn.datasource.Service#isStartable()
   */
  public boolean isStartable() throws RemoteException
  {
    return !this.isStarted();
  }

  /**
   * @see de.willuhn.datasource.Service#isStarted()
   */
  public boolean isStarted() throws RemoteException
  {
    return this.started;
  }

  /**
   * @see de.willuhn.datasource.Service#start()
   */
  public void start() throws RemoteException
  {
    if(this.isStarted())
    {
      Logger.warn("service allready started, skipping request");
      return;
    }
    // wir lassen uns benachrichtigen, wenn wir den SOAP-Service deployen koennen.
    Application.getMessagingFactory().registerMessageConsumer(new SoapConsumer());
    this.started = true;
  }

  /**
   * @see de.willuhn.datasource.Service#stop(boolean)
   */
  public void stop(boolean arg0) throws RemoteException
  {
    if(!this.isStarted())
    {
      Logger.warn("service not started, skipping request");
      return;
    }
    this.started = false;
  }

  /**
   * Hilfsklasse zum Registrieren des SOAP-Services.
   */
  private class SoapConsumer implements MessageConsumer
  {

    /**
     * @see de.willuhn.jameica.messaging.MessageConsumer#autoRegister()
     */
    public boolean autoRegister()
    {
      return false;
    }

    /**
     * @see de.willuhn.jameica.messaging.MessageConsumer#getExpectedMessageTypes()
     */
    public Class[] getExpectedMessageTypes()
    {
      return new Class[]{SystemMessage.class};
    }

    /**
     * @see de.willuhn.jameica.messaging.MessageConsumer#handleMessage(de.willuhn.jameica.messaging.Message)
     */
    public void handleMessage(Message message) throws Exception
    {
      SystemMessage msg = (SystemMessage) message;
      if (msg.getStatusCode() == SystemMessage.SYSTEM_STARTED)
      {
        Application.getMessagingFactory().getMessagingQueue("jameica.soap.publish").sendMessage(new QueryMessage("/Konto",new KontoServiceImpl()));
        Application.getMessagingFactory().getMessagingQueue("jameica.soap.publish").sendMessage(new QueryMessage("/Ueberweisung",new UeberweisungServiceImpl()));
        Application.getMessagingFactory().getMessagingQueue("jameica.soap.publish").sendMessage(new QueryMessage("/Lastschrift",new LastschriftServiceImpl()));
        Application.getMessagingFactory().getMessagingQueue("jameica.soap.publish").sendMessage(new QueryMessage("/SammelUeberweisung",new SammelUeberweisungServiceImpl()));
        Application.getMessagingFactory().getMessagingQueue("jameica.soap.publish").sendMessage(new QueryMessage("/SammelLastschrift",new SammelLastschriftServiceImpl()));
        Application.getMessagingFactory().getMessagingQueue("jameica.soap.publish").sendMessage(new QueryMessage("/Umsatz",new UmsatzServiceImpl()));
      }
    }
  }

}


/**********************************************************************
 * $Log: ConnectorServiceImpl.java,v $
 * Revision 1.4  2008/10/27 23:41:43  willuhn
 * @N Umsatz-Service
 *
 * Revision 1.3  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 * Revision 1.2  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 * Revision 1.1  2008/10/19 23:08:15  willuhn
 * @N Initial checkin
 *
 **********************************************************************/
