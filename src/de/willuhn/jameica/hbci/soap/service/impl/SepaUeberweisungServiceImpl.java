/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/SepaUeberweisungServiceImpl.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/01/19 11:36:10 $
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
import de.willuhn.jameica.hbci.rmi.AuslandsUeberweisung;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.beans.SepaUeberweisung;
import de.willuhn.jameica.hbci.soap.service.SepaUeberweisungService;
import de.willuhn.util.ApplicationException;


/**
 * Implementierung des Services fuer SEPA-Ueberweisungen.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.SepaUeberweisungService",name="SepaUeberweisung")
public class SepaUeberweisungServiceImpl extends AbstractService implements SepaUeberweisungService
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
      AuslandsUeberweisung u = (AuslandsUeberweisung) getService().createObject(AuslandsUeberweisung.class,id);
      u.delete();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
    catch (ObjectNotFoundException e)
    {
      throw new RemoteException("sepa ueberweisung [id: " + id + "] not found");
    }
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findAllOpen()
   */
  public List<SepaUeberweisung> findAllOpen() throws RemoteException
  {
    HBCIDBService service = getService();
    DBIterator list = service.createList(AuslandsUeberweisung.class);
    list.addFilter("ausgefuehrt = 0");

    List<SepaUeberweisung> ueberweisungen = new ArrayList<SepaUeberweisung>();
    while (list.hasNext())
    {
      AuslandsUeberweisung uh = (AuslandsUeberweisung) list.next();
      ueberweisungen.add(copy(uh));
    }
    return ueberweisungen;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findById(java.lang.String)
   */
  public SepaUeberweisung findById(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");

    HBCIDBService service = getService();
    AuslandsUeberweisung uh = (AuslandsUeberweisung) service.createObject(AuslandsUeberweisung.class,id);
    return copy(uh);
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#store(de.willuhn.jameica.hbci.soap.beans.Payment)
   */
  public String store(SepaUeberweisung ueberweisung) throws RemoteException
  {
    if (ueberweisung == null)
      throw new RemoteException("no sepa ueberweisung given");

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
    
    AuslandsUeberweisung uh = (AuslandsUeberweisung) service.createObject(AuslandsUeberweisung.class,null);
    uh.setBetrag(data.getBetrag());
    uh.setGegenkontoBLZ(data.getGegenkontoBlz());
    uh.setGegenkontoName(data.getGegenkontoName());
    uh.setGegenkontoNummer(data.getGegenkontoNummer());
    uh.setZweck(data.getZweck1());
    uh.setEndtoEndId(ueberweisung.getEndToEndId());
    uh.setPmtInfId(ueberweisung.getPmtInfId());
    uh.setPurposeCode(ueberweisung.getPurposeCode());
    
    uh.setTermin(ueberweisung.getDatum());

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
   * Kopiert die Properties der Hibiscus-AuslandsUeberweisung in die Bean.
   * @param uh Hibiscus-Ueberweisung.
   * @return SOAP-taugliche Bean.
   * @throws RemoteException
   */
  static SepaUeberweisung copy(AuslandsUeberweisung uh) throws RemoteException
  {
    PaymentData data = new PaymentData();
    data.setBetrag(uh.getBetrag());
    data.setGegenkontoBlz(uh.getGegenkontoBLZ());
    data.setGegenkontoName(uh.getGegenkontoName());
    data.setGegenkontoNummer(uh.getGegenkontoNummer());
    data.setTextschluessel(uh.getTextSchluessel());
    data.setZweck1(uh.getZweck());

    SepaUeberweisung u = new SepaUeberweisung();
    u.setId(uh.getID());
    u.setDatum(uh.getTermin());
    u.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    u.setEndToEndId(uh.getEndtoEndId());
    u.setPmtInfId(uh.getPmtInfId());
    u.setPurposeCode(uh.getPurposeCode());
    u.setPaymentData(data);
    return u;
  }

}
