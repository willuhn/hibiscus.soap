/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/LastschriftServiceImpl.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/04/29 21:15:15 $
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
import java.util.List;

import javax.jws.WebService;

import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.ObjectNotFoundException;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.Lastschrift;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.service.LastschriftService;
import de.willuhn.util.ApplicationException;


/**
 * Implementierung des Lastschrift-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.LastschriftService")
public class LastschriftServiceImpl extends AbstractService implements LastschriftService
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
      de.willuhn.jameica.hbci.rmi.Lastschrift u = (de.willuhn.jameica.hbci.rmi.Lastschrift) getService().createObject(de.willuhn.jameica.hbci.rmi.Lastschrift.class,id);
      u.delete();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
    catch (ObjectNotFoundException e)
    {
      throw new RemoteException("lastschrift [id: " + id + "] not found");
    }
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findAllOpen()
   */
  public List<Lastschrift> findAllOpen() throws RemoteException
  {
    HBCIDBService service = getService();
    DBIterator list = service.createList(de.willuhn.jameica.hbci.rmi.Lastschrift.class);
    list.addFilter("ausgefuehrt = 0");

    List<Lastschrift> lastschriften = new ArrayList<Lastschrift>();
    while (list.hasNext())
    {
      de.willuhn.jameica.hbci.rmi.Lastschrift uh = (de.willuhn.jameica.hbci.rmi.Lastschrift) list.next();
      lastschriften.add(copy(uh));
    }
    return lastschriften;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findById(java.lang.String)
   */
  public Lastschrift findById(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");

    HBCIDBService service = getService();
    de.willuhn.jameica.hbci.rmi.Lastschrift uh = (de.willuhn.jameica.hbci.rmi.Lastschrift) service.createObject(de.willuhn.jameica.hbci.rmi.Lastschrift.class,id);
    return copy(uh);
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#store(de.willuhn.jameica.hbci.soap.beans.Payment)
   */
  public String store(Lastschrift lastschrift) throws RemoteException
  {
    if (lastschrift == null)
      throw new RemoteException("no lastschrift given");

    PaymentData data = lastschrift.getPaymentData();
    if (data == null)
      throw new RemoteException("no payment data given");

    Konto k = lastschrift.getKonto();
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
    
    de.willuhn.jameica.hbci.rmi.Lastschrift uh = (de.willuhn.jameica.hbci.rmi.Lastschrift) service.createObject(de.willuhn.jameica.hbci.rmi.Lastschrift.class,null);
    uh.setBetrag(data.getBetrag());
    uh.setGegenkontoBLZ(data.getGegenkontoBlz());
    uh.setGegenkontoName(data.getGegenkontoName());
    uh.setGegenkontoNummer(data.getGegenkontoNummer());
    uh.setTextSchluessel(data.getTextschluessel());
    uh.setZweck(data.getZweck1());
    uh.setZweck2(data.getZweck2());
    uh.setWeitereVerwendungszwecke(data.getWeitereVerwendungszwecke());

    uh.setKonto(kh);
    uh.setTermin(lastschrift.getDatum());
    
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
   * Kopiert die Properties der Hibiscus-Lastschrift in die Bean.
   * @param uh Hibiscus-Lastschrift.
   * @return SOAP-taugliche Bean.
   * @throws RemoteException
   */
  static Lastschrift copy(de.willuhn.jameica.hbci.rmi.Lastschrift uh) throws RemoteException
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
    
    Lastschrift u = new Lastschrift();
    u.setId(uh.getID());
    u.setDatum(uh.getTermin());
    u.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    u.setPaymentData(data);
    return u;
  }

}


/**********************************************************************
 * $Log: LastschriftServiceImpl.java,v $
 * Revision 1.3  2009/04/29 21:15:15  willuhn
 * @N Support fuer erweiterte Verwendungszwecke
 *
 * Revision 1.2  2008/10/27 23:41:43  willuhn
 * @N Umsatz-Service
 *
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/