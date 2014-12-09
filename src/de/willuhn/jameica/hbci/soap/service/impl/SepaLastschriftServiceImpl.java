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
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.rmi.SepaLastschrift;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.beans.SepaLastSequenceType;
import de.willuhn.jameica.hbci.soap.beans.SepaLastType;
import de.willuhn.jameica.hbci.soap.service.SepaLastschriftService;
import de.willuhn.util.ApplicationException;


/**
 * Implementierung des Services fuer SEPA-Lastschriften.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.SepaLastschriftService",name="SepaLastschrift")
public class SepaLastschriftServiceImpl extends AbstractService implements SepaLastschriftService
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
      SepaLastschrift l = (SepaLastschrift) getService().createObject(SepaLastschrift.class,id);
      l.delete();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
    catch (ObjectNotFoundException e)
    {
      throw new RemoteException("sepa lastschrift [id: " + id + "] not found");
    }
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findAllOpen()
   */
  public List<de.willuhn.jameica.hbci.soap.beans.SepaLastschrift> findAllOpen() throws RemoteException
  {
    HBCIDBService service = getService();
    DBIterator list = service.createList(SepaLastschrift.class);
    list.addFilter("ausgefuehrt = 0");

    List<de.willuhn.jameica.hbci.soap.beans.SepaLastschrift> result = new ArrayList<de.willuhn.jameica.hbci.soap.beans.SepaLastschrift>();
    while (list.hasNext())
    {
      SepaLastschrift uh = (SepaLastschrift) list.next();
      result.add(copy(uh));
    }
    return result;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findById(java.lang.String)
   */
  public de.willuhn.jameica.hbci.soap.beans.SepaLastschrift findById(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");

    HBCIDBService service = getService();
    SepaLastschrift lh = (SepaLastschrift) service.createObject(SepaLastschrift.class,id);
    return copy(lh);
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#store(de.willuhn.jameica.hbci.soap.beans.Payment)
   */
  public String store(de.willuhn.jameica.hbci.soap.beans.SepaLastschrift lastschrift) throws RemoteException
  {
    if (lastschrift == null)
      throw new RemoteException("no sepa lastschrift given");

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
    
    SepaLastschrift lh = (SepaLastschrift) service.createObject(SepaLastschrift.class,null);
    lh.setBetrag(data.getBetrag());
    lh.setGegenkontoBLZ(data.getGegenkontoBlz());
    lh.setGegenkontoName(data.getGegenkontoName());
    lh.setGegenkontoNummer(data.getGegenkontoNummer());
    lh.setZweck(data.getZweck1());
    lh.setEndtoEndId(lastschrift.getEndToEndId());
    lh.setPurposeCode(lastschrift.getPurposeCode());
    lh.setPmtInfId(lastschrift.getPmtInfId());
    lh.setMandateId(lastschrift.getMandateId());
    lh.setCreditorId(lastschrift.getCreditorId());
    lh.setSignatureDate(lastschrift.getSignatureDate());
    lh.setSequenceType(de.willuhn.jameica.hbci.rmi.SepaLastSequenceType.valueOf(lastschrift.getSequenceType().name()));
    lh.setTargetDate(lastschrift.getTargetDate());
    lh.setType(de.willuhn.jameica.hbci.rmi.SepaLastType.valueOf(lastschrift.getType().name()));
    
    lh.setTermin(lastschrift.getDatum());

    lh.setKonto(kh);
    
    try
    {
      lh.store();
      return lh.getID();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
  }

  /**
   * Kopiert die Properties der Hibiscus-SEPA-Lastschrift in die Bean.
   * @param uh Hibiscus-SEPA-Lastschrift.
   * @return SOAP-taugliche Bean.
   * @throws RemoteException
   */
  static de.willuhn.jameica.hbci.soap.beans.SepaLastschrift copy(SepaLastschrift uh) throws RemoteException
  {
    PaymentData data = new PaymentData();
    data.setBetrag(uh.getBetrag());
    data.setGegenkontoBlz(uh.getGegenkontoBLZ());
    data.setGegenkontoName(uh.getGegenkontoName());
    data.setGegenkontoNummer(uh.getGegenkontoNummer());
    data.setZweck1(uh.getZweck());

    de.willuhn.jameica.hbci.soap.beans.SepaLastschrift l = new de.willuhn.jameica.hbci.soap.beans.SepaLastschrift();
    l.setId(uh.getID());
    l.setDatum(uh.getTermin());
    l.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    l.setEndToEndId(uh.getEndtoEndId());
    l.setPurposeCode(uh.getPurposeCode());
    l.setPmtInfId(uh.getPmtInfId());
    l.setMandateId(uh.getMandateId());
    l.setCreditorId(uh.getCreditorId());
    l.setSignatureDate(uh.getSignatureDate());
    l.setSequenceType(SepaLastSequenceType.valueOf(uh.getSequenceType().name()));
    l.setTargetDate(uh.getTargetDate());
    l.setType(SepaLastType.valueOf(uh.getType().name()));
    
    l.setPaymentData(data);
    return l;
  }

}
