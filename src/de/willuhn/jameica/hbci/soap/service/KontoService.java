/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/KontoService.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/01/19 00:34:48 $
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
import de.willuhn.jameica.soap.AutoService;


/**
 * Interface fuer den Konto-Webservice.
 */
@WebService
public interface KontoService extends AutoService
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
 * Revision 1.3  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 * Revision 1.2  2008/10/19 23:50:36  willuhn
 * @N Erste funktionierende Version
 *
 * Revision 1.1  2008/10/19 23:08:15  willuhn
 * @N Initial checkin
 *
 **********************************************************************/
