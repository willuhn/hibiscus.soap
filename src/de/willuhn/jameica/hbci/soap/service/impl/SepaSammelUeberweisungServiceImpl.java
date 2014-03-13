/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.service.impl;

import java.rmi.RemoteException;
import java.util.List;

import javax.jws.WebService;

import de.willuhn.datasource.rmi.ObjectNotFoundException;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.SepaSammelUeberweisung;
import de.willuhn.jameica.hbci.soap.beans.SepaSammelUeberweisungBuchung;
import de.willuhn.jameica.hbci.soap.service.SepaSammelUeberweisungService;
import de.willuhn.util.ApplicationException;


/**
 * Implementierung des Services fuer SEPA-Sammelueberweisungen.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.SepaSammelUeberweisungService",name="SepaSammelUeberweisung")
public class SepaSammelUeberweisungServiceImpl extends AbstractSepaBundlePaymentService<SepaSammelUeberweisung, de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisung> implements SepaSammelUeberweisungService
{
  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#store(de.willuhn.jameica.hbci.soap.beans.Payment)
   */
  public String store(SepaSammelUeberweisung bundle) throws RemoteException
  {
    if (bundle == null)
      throw new RemoteException("no sepa sammel-transfer given");

    List<SepaSammelUeberweisungBuchung> payments = bundle.getBuchungen();
    if (payments == null || payments.size() == 0)
      throw new RemoteException("sepa sammel-transfer contains no payments");

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
    
    de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisung uh = null;
    boolean error = false;
    try
    {
      uh = (de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisung) service.createObject(getType(),null);
      
      uh.transactionBegin();

      uh.setBezeichnung(bundle.getBezeichnung());
      uh.setKonto(kh);
      uh.setTermin(bundle.getDatum());
      uh.store();
      
      for (SepaSammelUeberweisungBuchung data:payments)
      {
        de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisungBuchung b = uh.createBuchung();
        b.setBetrag(data.getBetrag());
        b.setGegenkontoBLZ(data.getGegenkontoBlz());
        b.setGegenkontoName(data.getGegenkontoName());
        b.setGegenkontoNummer(data.getGegenkontoNummer());
        b.setZweck(data.getZweck1());
        
        b.setEndtoEndId(data.getEndToEndId());
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
      throw new RemoteException("unable to create sepa sammel-transfer",e);
    }
    finally
    {
      if (error && uh != null)
        uh.transactionRollback();
    }
  }
  
  /**
   * @see de.willuhn.jameica.hbci.soap.service.impl.AbstractSepaBundlePaymentService#copy(de.willuhn.jameica.hbci.rmi.SepaSammelTransfer)
   */
  SepaSammelUeberweisung copy(de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisung uh) throws RemoteException
  {
    SepaSammelUeberweisung t = new SepaSammelUeberweisung();
    t.setId(uh.getID());
    t.setBezeichnung(uh.getBezeichnung());
    t.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    t.setDatum(uh.getTermin());
    
    List<de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisungBuchung> buchungen = uh.getBuchungen();
    for (de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisungBuchung b:buchungen)
    {
      SepaSammelUeberweisungBuchung data = new SepaSammelUeberweisungBuchung();
      data.setBetrag(b.getBetrag());
      data.setGegenkontoBlz(b.getGegenkontoBLZ());
      data.setGegenkontoName(b.getGegenkontoName());
      data.setGegenkontoNummer(b.getGegenkontoNummer());
      data.setZweck1(b.getZweck());
      data.setEndToEndId(b.getEndtoEndId());
      
      t.add(data);
    }
    return t;
  }
  
  /**
   * @see de.willuhn.jameica.hbci.soap.service.impl.AbstractSepaBundlePaymentService#getType()
   */
  @Override
  Class getType()
  {
    return de.willuhn.jameica.hbci.rmi.SepaSammelUeberweisung.class;
  }

  

}
