/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/PaymentService.java,v $
 * $Revision: 1.3 $
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
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.Payment;


/**
 * Basis-Interface fuer Zahlungen.
 * @param <T> Die Zahlungsart.
 */
@WebService
public interface PaymentService<T extends Payment>
{
  /**
   * Liefert alle offenen Auftraege.
   * @return Liefert eine Liste aller offenen Auftraege.
   * @throws RemoteException
   */
  public @WebResult(name="payments") List<T> findAllOpen() throws RemoteException;

  /**
   * Liefert den Auftrag zur angegebenen ID.
   * @param id ID des Auftrags.
   * @return der Auftrag.
   * @throws RemoteException
   */
  public @WebResult(name="payment") T findById(@WebParam(name="id") String id) throws RemoteException;
  
  /**
   * Legt einen neuen Auftrag an.
   * @param payment der Auftrag.
   * @return die ID des erzeugten Auftrages.
   * @throws RemoteException
   */
  public @WebResult(name="id") String store(@WebParam(name="payment") T payment) throws RemoteException;
  
  /**
   * Loescht den Auftrag mit der angegebenen ID.
   * @param id ID.
   * @throws RemoteException
   */
  public void delete(@WebParam(name="id") String id) throws RemoteException;
}


/**********************************************************************
 * $Log: PaymentService.java,v $
 * Revision 1.3  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 * Revision 1.2  2008/10/21 23:14:11  willuhn
 * @N Sammel-Auftraege werden jetzt zwar deployed - CXF wirft aber eine Exception, weil es versucht "Payment" statt "SammelLastschrift" zu deserialisieren
 *
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/
