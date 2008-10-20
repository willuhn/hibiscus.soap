/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/UeberweisungService.java,v $
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

package de.willuhn.jameica.hbci.soap.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.Ueberweisung;


/**
 * Interface fuer den Ueberweisungs-Webservice.
 */
@WebService(name="Ueberweisung")
public interface UeberweisungService
{
  /**
   * Liefert alle offenen Ueberweisungen.
   * @return Liefert eine Liste aller offenen Ueberweisungen.
   * @throws RemoteException
   */
  public @WebResult(name="ueberweisungen") List<Ueberweisung> findAllOpen() throws RemoteException;

  /**
   * Liefert die Ueberweisung zur angegebenen ID.
   * @param id ID der Ueberweisung.
   * @return die Ueberweisung.
   * @throws RemoteException
   */
  public @WebResult(name="ueberweisung") Ueberweisung findById(@WebParam(name="id") String id) throws RemoteException;
  
  /**
   * Legt eine neue Ueberweisung an.
   * @param ueberweisung die Ueberweisung.
   * @return die ID der erzeugten Ueberweisung.
   * @throws RemoteException
   */
  public @WebResult(name="id") String store(@WebParam(name="ueberweisung") Ueberweisung ueberweisung) throws RemoteException;
  
  /**
   * Loescht die Ueberweisung mit der angegebenen ID.
   * @param id ID.
   * @throws RemoteException
   */
  public void delete(@WebParam(name="id") String id) throws RemoteException;
}


/**********************************************************************
 * $Log: UeberweisungService.java,v $
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
