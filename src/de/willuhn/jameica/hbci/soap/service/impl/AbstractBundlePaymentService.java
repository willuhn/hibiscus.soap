/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/AbstractBundlePaymentService.java,v $
 * $Revision: 1.4 $
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

import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.ObjectNotFoundException;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.rmi.SammelTransfer;
import de.willuhn.jameica.hbci.rmi.SammelTransferBuchung;
import de.willuhn.jameica.hbci.soap.beans.BundlePayment;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.service.PaymentService;
import de.willuhn.util.ApplicationException;


/**
 * Abstrakte Basis-Implementierung des Sammelauftrags-Service.
 * @param <T> Die Zahlungsart.
 */
public abstract class AbstractBundlePaymentService<T extends BundlePayment> extends AbstractService implements PaymentService<T>
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
      SammelTransfer t = (SammelTransfer) getService().createObject(getType(),id);
      t.delete();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
    catch (ObjectNotFoundException e)
    {
      throw new RemoteException("sammel-transfer [id: " + id + "] not found");
    }
  }
  
  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findAllOpen()
   */
  public List<T> findAllOpen() throws RemoteException
  {
    HBCIDBService service = getService();
    DBIterator list = service.createList(getType());
    list.addFilter("ausgefuehrt = 0");

    List<T> payments = new ArrayList<T>();
    while (list.hasNext())
    {
      SammelTransfer uh = (SammelTransfer) list.next();
      payments.add(copy(uh));
    }
    return payments;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findById(java.lang.String)
   */
  public T findById(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");

    HBCIDBService service = getService();
    SammelTransfer uh = (SammelTransfer) service.createObject(getType(),id);
    return copy(uh);
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#store(de.willuhn.jameica.hbci.soap.beans.Payment)
   */
  public String store(T bundle) throws RemoteException
  {
    if (bundle == null)
      throw new RemoteException("no sammel-transfer given");

    List<PaymentData> payments = bundle.getBuchungen();
    if (payments == null || payments.size() == 0)
      throw new RemoteException("sammel-transfer contains no payments");

    Konto k = bundle.getKonto();
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
    
    SammelTransfer uh = null;
    boolean error = false;
    try
    {
      uh = (SammelTransfer) service.createObject(getType(),null);
      
      uh.transactionBegin();

      uh.setBezeichnung(bundle.getBezeichnung());
      uh.setKonto(kh);
      uh.setTermin(bundle.getDatum());
      uh.store();
      
      for (PaymentData data:payments)
      {
        SammelTransferBuchung b = uh.createBuchung();
        b.setBetrag(data.getBetrag());
        b.setGegenkontoBLZ(data.getGegenkontoBlz());
        b.setGegenkontoName(data.getGegenkontoName());
        b.setGegenkontoNummer(data.getGegenkontoNummer());
        b.setTextSchluessel(data.getTextschluessel());
        b.setZweck(data.getZweck1());
        b.setZweck2(data.getZweck2());
        b.setWeitereVerwendungszwecke(data.getWeitereVerwendungszwecke());
        b.store();
      }
      
      uh.transactionCommit();
      return uh.getID();
    }
    catch (RemoteException re)
    {
      error = true;
      throw re;
    }
    catch (ApplicationException ae)
    {
      error = true;
      throw new RemoteException(ae.getMessage());
    }
    catch (Exception e)
    {
      error = true;
      throw new RemoteException("unable to create sammel-transfer",e);
    }
    finally
    {
      if (error && uh != null)
        uh.transactionRollback();
    }
  }

  /**
   * Kopiert die Properties der Hibiscus-Sammeltransfers in die Bean.
   * @param uh Hibiscus-Sammeltransfer.
   * @return SOAP-taugliche Bean.
   * @throws RemoteException
   */
  T copy(SammelTransfer uh) throws RemoteException
  {
    T t = create();
    t.setId(uh.getID());
    t.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    t.setDatum(uh.getTermin());
    
    DBIterator buchungen = uh.getBuchungen();
    while (buchungen.hasNext())
    {
      SammelTransferBuchung b = (SammelTransferBuchung) buchungen.next();
      PaymentData data = new PaymentData();
      data.setBetrag(b.getBetrag());
      data.setGegenkontoBlz(b.getGegenkontoBLZ());
      data.setGegenkontoName(b.getGegenkontoName());
      data.setGegenkontoNummer(b.getGegenkontoNummer());
      data.setTextschluessel(b.getTextSchluessel());
      data.setZweck1(b.getZweck());
      data.setZweck2(b.getZweck2());
      data.setWeitereVerwendungszwecke(b.getWeitereVerwendungszwecke());
      t.add(data);
    }
    return t;
  }
  
  /**
   * Erzeugt ein neues Objekt des konkreten Typs.
   * @return Objekt.
   */
  abstract T create();

  /**
   * Liefert den konkreten Hibiscus-Datentyp.
   * @return der konkrete Hibiscus-Datentyp.
   */
  abstract Class getType();

}


/**********************************************************************
 * $Log: AbstractBundlePaymentService.java,v $
 * Revision 1.4  2009/04/29 21:15:15  willuhn
 * @N Support fuer erweiterte Verwendungszwecke
 *
 * Revision 1.3  2008/10/27 23:41:43  willuhn
 * @N Umsatz-Service
 *
 * Revision 1.2  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/