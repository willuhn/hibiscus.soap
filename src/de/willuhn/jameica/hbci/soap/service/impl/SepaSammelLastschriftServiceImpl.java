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
import de.willuhn.jameica.hbci.MetaKey;
import de.willuhn.jameica.hbci.rmi.BatchBookType;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.rmi.SepaSammelLastBuchung;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.SepaLastSequenceType;
import de.willuhn.jameica.hbci.soap.beans.SepaLastType;
import de.willuhn.jameica.hbci.soap.beans.SepaSammelLastschrift;
import de.willuhn.jameica.hbci.soap.beans.SepaSammelLastschriftBuchung;
import de.willuhn.jameica.hbci.soap.service.SepaSammelLastschriftService;
import de.willuhn.util.ApplicationException;


/**
 * Implementierung des Services fuer SEPA-Sammellastschriften.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.SepaSammelLastschriftService",name="SepaSammelLastschrift")
public class SepaSammelLastschriftServiceImpl extends AbstractSepaBundlePaymentService<SepaSammelLastschrift, de.willuhn.jameica.hbci.rmi.SepaSammelLastschrift> implements SepaSammelLastschriftService
{
  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#store(de.willuhn.jameica.hbci.soap.beans.Payment)
   */
  public String store(SepaSammelLastschrift bundle) throws RemoteException
  {
    if (bundle == null)
      throw new RemoteException("no sepa sammel-transfer given");

    List<SepaSammelLastschriftBuchung> payments = bundle.getBuchungen();
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
    
    de.willuhn.jameica.hbci.rmi.SepaSammelLastschrift uh = null;
    boolean error = false;
    try
    {
      uh = (de.willuhn.jameica.hbci.rmi.SepaSammelLastschrift) service.createObject(getType(),null);
      
      uh.transactionBegin();

      uh.setBezeichnung(bundle.getBezeichnung());
      uh.setKonto(kh);
      uh.setTermin(bundle.getDatum());
      uh.setSequenceType(de.willuhn.jameica.hbci.rmi.SepaLastSequenceType.valueOf(bundle.getSequenceType().name()));
      uh.setType(de.willuhn.jameica.hbci.rmi.SepaLastType.valueOf(bundle.getType().name()));
      uh.setTargetDate(bundle.getTargetDate());
      uh.setPmtInfId(bundle.getPmtInfId());
      uh.store();
      
      BatchBookType batch = BatchBookType.byValue(bundle.getBatchBooking());
      if (batch != null)
        MetaKey.SEPA_BATCHBOOK.set(uh,batch.getValue());
      
      for (SepaSammelLastschriftBuchung data:payments)
      {
        SepaSammelLastBuchung b = uh.createBuchung();
        b.setBetrag(data.getBetrag());
        b.setGegenkontoBLZ(data.getGegenkontoBlz());
        b.setGegenkontoName(data.getGegenkontoName());
        b.setGegenkontoNummer(data.getGegenkontoNummer());
        b.setZweck(data.getZweck1());
        
        b.setEndtoEndId(data.getEndToEndId());
        b.setPurposeCode(data.getPurposeCode());
        b.setMandateId(data.getMandateId());
        b.setCreditorId(data.getCreditorId());
        b.setSignatureDate(data.getSignatureDate());
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
  SepaSammelLastschrift copy(de.willuhn.jameica.hbci.rmi.SepaSammelLastschrift uh) throws RemoteException
  {
    SepaSammelLastschrift t = new SepaSammelLastschrift();
    t.setId(uh.getID());
    t.setBezeichnung(uh.getBezeichnung());
    t.setKonto(KontoServiceImpl.copy(uh.getKonto()));
    t.setDatum(uh.getTermin());
    t.setSequenceType(SepaLastSequenceType.valueOf(uh.getSequenceType().name()));
    t.setTargetDate(uh.getTargetDate());
    t.setType(SepaLastType.valueOf(uh.getType().name()));
    t.setPmtInfId(uh.getPmtInfId());
    
    BatchBookType batch = BatchBookType.byValue(MetaKey.SEPA_BATCHBOOK.get(uh));
    if (batch != null)
      t.setBatchBooking(batch.getBooleanValue());
    
    List<SepaSammelLastBuchung> buchungen = uh.getBuchungen();
    for (SepaSammelLastBuchung b:buchungen)
    {
      SepaSammelLastschriftBuchung data = new SepaSammelLastschriftBuchung();
      data.setBetrag(b.getBetrag());
      data.setGegenkontoBlz(b.getGegenkontoBLZ());
      data.setGegenkontoName(b.getGegenkontoName());
      data.setGegenkontoNummer(b.getGegenkontoNummer());
      data.setZweck1(b.getZweck());
      data.setEndToEndId(b.getEndtoEndId());
      data.setPurposeCode(b.getPurposeCode());
      data.setMandateId(b.getMandateId());
      data.setCreditorId(b.getCreditorId());
      data.setSignatureDate(b.getSignatureDate());
      
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
    return de.willuhn.jameica.hbci.rmi.SepaSammelLastschrift.class;
  }

  

}
