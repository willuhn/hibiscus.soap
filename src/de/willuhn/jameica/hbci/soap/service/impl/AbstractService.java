/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/AbstractService.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/10/20 00:26:22 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.service.impl;

import java.rmi.RemoteException;

import de.willuhn.jameica.hbci.HBCI;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.system.Application;


/**
 * Abstrakte Basis-Klasse der Services.
 */
public abstract class AbstractService
{
  /**
   * Liefert den DB-Service von Hibiscus.
   * @return der DB-Service von Hibiscus.
   * @throws RemoteException
   */
  HBCIDBService getService() throws RemoteException
  {
    try
    {
      return (HBCIDBService) Application.getServiceFactory().lookup(HBCI.class,"database");
    }
    catch (RemoteException re)
    {
      throw re;
    }
    catch (Exception e)
    {
      throw new RemoteException("unable to open hibiscus db service",e);
    }
  }
}


/**********************************************************************
 * $Log: AbstractService.java,v $
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
