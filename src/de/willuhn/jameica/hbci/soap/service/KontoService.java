/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/KontoService.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/10/19 23:50:36 $
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

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;

import de.willuhn.jameica.hbci.soap.beans.Konto;


/**
 * Interface fuer den Konto-Webservice.
 */
@WebService(name="Konto")
public interface KontoService
{
  /**
   * Liefert alle Konten.
   * @return Liefert eine Liste aller Konten.
   * @throws RemoteException
   */
  public @WebResult(name="konten") List<Konto> findAll() throws RemoteException;

  /**
   * Liefert das Konto zur angegebenen ID.
   * @param id ID des Kontos.
   * @return das Konto.
   * @throws RemoteException
   */
  public @WebResult(name="konto") Konto findById(@WebParam(name="id") String id) throws RemoteException;
}


/**********************************************************************
 * $Log: KontoService.java,v $
 * Revision 1.2  2008/10/19 23:50:36  willuhn
 * @N Erste funktionierende Version
 *
 * Revision 1.1  2008/10/19 23:08:15  willuhn
 * @N Initial checkin
 *
 **********************************************************************/
