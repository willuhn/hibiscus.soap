/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/UeberweisungServiceImpl.java,v $
 * $Revision: 1.6 $
 * $Date: 2010/01/19 00:34:48 $
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.ObjectNotFoundException;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.beans.Ueberweisung;
import de.willuhn.jameica.hbci.soap.service.UeberweisungService;
import de.willuhn.util.ApplicationException;


/**
 * Implementierung des Ueberweisung-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.UeberweisungService",name="Ueberweisung")
public class UeberweisungServiceImpl extends AbstractService implements UeberweisungService
{
  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#delete(java.lang.String)
   */
  public void delete(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");
    
    try
    {
      de.willuhn.jameica.hbci.rmi.Ueberweisung u = (de.willuhn.jameica.hbci.rmi.Ueberweisung) getService().createObject(de.willuhn.jameica.hbci.rmi.Ueberweisung.class,id);
      u.delete();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
    catch (ObjectNotFoundException e)
    {
      throw new RemoteException("ueberweisung [id: " + id + "] not found");
    }
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findAllOpen()
   */
  public List<Ueberweisung> findAllOpen() throws RemoteException
  {
    HBCIDBService service = getService();
    DBIterator list = service.createList(de.willuhn.jameica.hbci.rmi.Ueberweisung.class);
    list.addFilter("ausgefuehrt = 0");
    list.addFilter("(banktermin = 1 OR termin <= ?)", new Object[]{new java.sql.Date(new Date().getTime())});

    List<Ueberweisung> ueberweisungen = new ArrayList<Ueberweisung>();
    while (list.hasNext())
    {
      de.willuhn.jameica.hbci.rmi.Ueberweisung uh = (de.willuhn.jameica.hbci.rmi.Ueberweisung) list.next();
      ueberweisungen.add(copy(uh));
    }
    return ueberweisungen;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findById(java.lang.String)
   */
  public Ueberweisung findById(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");

    HBCIDBService service = getService();
    de.willuhn.jameica.hbci.rmi.Ueberweisung uh = (de.willuhn.jameica.hbci.rmi.Ueberweisung) service.createObject(de.willuhn.jameica.hbci.rmi.Ueberweisung.class,id);
    return copy(uh);
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#store(de.willuhn.jameica.hbci.soap.beans.Payment)
   */
  public String store(Ueberweisung ueberweisung) throws RemoteException
  {
    if (ueberweisung == null)
      throw new RemoteException("no ueberweisung given");

    PaymentData data = ueberweisung.getPaymentData();
    if (data == null)
      throw new RemoteException("no payment data given");

    Konto k = ueberweisung.getKonto();
    if (k == null || k.getId() == null || k.getId().length() == 0)
      throw new RemoteException("no konto given");

    HBCIDBService service = getService();

    de.willuhn.jameica.hbci.rmi.Konto kh = null;
    try
    {
      kh = (de.willuhn.jameica.hbci.rmi.Konto) service.createObject(de.willuhn.jameica.hbci.rmi.Konto.class,k.getId());
    }
    catch (ObjectNotFoundException e)
    {
      throw new RemoteException("konto [id: " + k.getId() + "] not found");
    }
    
    de.willuhn.jameica.hbci.rmi.Ueberweisung uh = (de.willuhn.jameica.hbci.rmi.Ueberweisung) service.createObject(de.willuhn.jameica.hbci.rmi.Ueberweisung.class,null);
    uh.setBetrag(data.getBetrag());
    uh.setGegenkontoBLZ(data.getGegenkontoBlz());
    uh.setGegenkontoName(data.getGegenkontoName());
    uh.setGegenkontoNummer(data.getGegenkontoNummer());
    uh.setTextSchluessel(data.getTextschluessel());
    uh.setZweck(data.getZweck1());
    uh.setZweck2(data.getZweck2());
    uh.setWeitereVerwendungszwecke(data.getWeitereVerwendungszwecke());
    
    uh.setTermin(ueberweisung.getDatum());
    uh.setTerminUeberweisung(ueberweisung.isTerminauftrag());

    uh.setKonto(kh);
    
    try
    {
      uh.store();
      return uh.getID();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
  }

  /**
   * Kopiert die Properties der Hibiscus-Ueberweisung in die Bean.
   * @param uh Hibiscus-Ueberweisung.
   * @return SOAP-taugliche Bean.
   * @throws RemoteException
   */
  static Ueberweisung copy(de.willuhn.jameica.hbci.rmi.Ueberweisung uh) throws RemoteException
  {
    PaymentData data = new PaymentData();
    data.setBetrag(uh.getBetrag());
    data.setGegenkontoBlz(uh.getGegenkontoBLZ());
    data.setGegenkontoName(uh.getGegenkontoName());
    data.setGegenkontoNummer(uh.getGegenkontoNummer());
    data.setTextschluessel(uh.getTextSchluessel());
    data.setZweck1(uh.getZweck());
    data.setZweck2(uh.getZweck2());
    data.setWeitereVerwendungszwecke(uh.getWeitereVerwendungszwecke());

    Ueberweisung u = new Ueberweisung();
    u.setId(uh.getID());
    u.setDatum(uh.getTermin());
    u.setTerminauftrag(uh.isTerminUeberweisung());
    u.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    u.setPaymentData(data);
    return u;
  }

}


/**********************************************************************
 * $Log: UeberweisungServiceImpl.java,v $
 * Revision 1.6  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 * Revision 1.5  2009/04/29 21:15:15  willuhn
 * @N Support fuer erweiterte Verwendungszwecke
 *
 * Revision 1.4  2008/10/27 23:41:43  willuhn
 * @N Umsatz-Service
 *
 * Revision 1.3  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 * Revision 1.2  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
