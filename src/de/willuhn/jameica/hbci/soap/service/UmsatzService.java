/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/UmsatzService.java,v $
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
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.Umsatz;
import de.willuhn.jameica.soap.AutoService;


/**
 * Interface fuer die Abfrage von Umsaetzen.
 */
@WebService
public interface UmsatzService extends AutoService
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
   * Die Umsaetze werden in chronologischer Reihenfolge geliefert.
   * Also die alten zuersten, neue zuletzt.
   * @throws RemoteException
   */
  public @WebResult(name="umsaetze") List<Umsatz> find(@WebParam(name="konto") Konto konto,
                                                       @WebParam(name="von") Date von,
                                                       @WebParam(name="bis") Date bis,
                                                       @WebParam(name="text") String text) throws RemoteException;
}


/**********************************************************************
 * $Log: UmsatzService.java,v $
 * Revision 1.3  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 * Revision 1.2  2008/10/27 23:41:43  willuhn
 * @N Umsatz-Service
 *
 * Revision 1.1  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 **********************************************************************/