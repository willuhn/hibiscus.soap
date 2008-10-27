/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/UmsatzService.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/10/27 14:21:19 $
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
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.Umsatz;


/**
 * Interface fuer die Abfrage von Umsaetzen.
 */
@WebService(name="Umsatz")
public interface UmsatzService
{
  /**
   * Liefert eine Liste von Umsaetzen.
   * @param konto optionale Angabe des Kontos, von dem die Umsaetze stammen sollen.
   * Wird kein Konto angegeben, wird in allen Konten gescuht.
   * @param von Start-Datum.
   * Optionale zeitliche Einschraenkung. 
   * @param bis End-Datum.
   * Optionale zeitliche Einschraenkung. 
   * @param text Suchbegriff.
   * Optionale Angabe eines Suchbegriffes.
   * @return Liste der gefundenen Umsaetze.
   * @throws RemoteException
   */
  public @WebResult(name="umsaetze") List<Umsatz> find(@WebParam(name="konto") Konto konto,
                                                       @WebParam(name="von") Date von,
                                                       @WebParam(name="bis") Date bis,
                                                       @WebParam(name="text") String text) throws RemoteException;
}


/**********************************************************************
 * $Log: UmsatzService.java,v $
 * Revision 1.1  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 **********************************************************************/