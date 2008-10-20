/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/UeberweisungServiceImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/10/20 00:26:22 $
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
import de.willuhn.jameica.hbci.soap.beans.Ueberweisung;
import de.willuhn.jameica.hbci.soap.service.UeberweisungService;
import de.willuhn.util.ApplicationException;


/**
 * Implementierung des Ueberweisung-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.UeberweisungService")
public class UeberweisungServiceImpl extends AbstractService implements UeberweisungService
{
  /**
   * @see de.willuhn.jameica.hbci.soap.service.UeberweisungService#delete(java.lang.String)
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
   * @see de.willuhn.jameica.hbci.soap.service.UeberweisungService#findAllOpen()
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
   * @see de.willuhn.jameica.hbci.soap.service.UeberweisungService#findById(java.lang.String)
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
   * @see de.willuhn.jameica.hbci.soap.service.UeberweisungService#store(de.willuhn.jameica.hbci.soap.beans.Ueberweisung)
   */
  public String store(Ueberweisung ueberweisung) throws RemoteException
  {
    if (ueberweisung == null)
      throw new RemoteException("no ueberweisung given");

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
    uh.setBetrag(ueberweisung.getBetrag());
    uh.setGegenkontoBLZ(ueberweisung.getGegenkontoBlz());
    uh.setGegenkontoName(ueberweisung.getGegenkontoName());
    uh.setGegenkontoNummer(ueberweisung.getGegenkontoNummer());
    uh.setTermin(ueberweisung.getTermin());
    uh.setTerminUeberweisung(ueberweisung.isTerminauftrag());
    uh.setTextSchluessel(ueberweisung.getTextschluessel());
    uh.setZweck(ueberweisung.getZweck1());
    uh.setZweck2(ueberweisung.getZweck2());

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
    Ueberweisung u = new Ueberweisung();
    u.setBetrag(uh.getBetrag());
    u.setGegenkontoBlz(uh.getGegenkontoBLZ());
    u.setGegenkontoName(uh.getGegenkontoName());
    u.setGegenkontoNummer(uh.getGegenkontoNummer());
    u.setId(uh.getID());
    u.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    u.setTermin(uh.getTermin());
    u.setTerminauftrag(uh.isTerminUeberweisung());
    u.setTextschluessel(uh.getTextSchluessel());
    u.setZweck1(uh.getZweck());
    u.setZweck2(uh.getZweck2());
    return u;
  }

}


/**********************************************************************
 * $Log: UeberweisungServiceImpl.java,v $
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
