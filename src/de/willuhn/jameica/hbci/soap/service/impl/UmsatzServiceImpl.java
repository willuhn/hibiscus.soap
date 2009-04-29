/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/UmsatzServiceImpl.java,v $
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
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.hbci.HBCIProperties;
import de.willuhn.jameica.hbci.rmi.UmsatzTyp;
import de.willuhn.jameica.hbci.server.UmsatzUtil;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.beans.Umsatz;
import de.willuhn.jameica.hbci.soap.service.UmsatzService;

/**
 * Implementierung des Umsatz-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.UmsatzService")
public class UmsatzServiceImpl implements UmsatzService
{

  /**
   * @see de.willuhn.jameica.hbci.soap.service.UmsatzService#find(de.willuhn.jameica.hbci.soap.beans.Konto, java.util.Date, java.util.Date, java.lang.String)
   */
  public List<Umsatz> find(Konto konto, Date von, Date bis, String text)
      throws RemoteException
  {
    DBIterator list = UmsatzUtil.getUmsaetze();
    
    if (konto != null && konto.getId() != null && konto.getId().length() > 0)
      list.addFilter("konto_id = ?",new Object[]{konto.getId()});
    
    if (von != null) list.addFilter("valuta >= ?", new Object[] {new java.sql.Date(HBCIProperties.startOfDay(von).getTime())});
    if (bis != null) list.addFilter("valuta <= ?", new Object[] {new java.sql.Date(HBCIProperties.endOfDay(bis).getTime())});

    if (text != null && text.length() > 0)
    {
      String s = "%" + text.toLowerCase() + "%";
      list.addFilter("(LOWER(zweck) LIKE ? OR " +
          "LOWER(zweck2) LIKE ? OR " +
          "LOWER(zweck3) LIKE ? OR " +
          "LOWER(empfaenger_name) LIKE ? OR " +
          "empfaenger_konto LIKE ? OR " +
          "empfaenger_blz LIKE ? OR " +
          "LOWER(primanota) LIKE ? OR " +
          "LOWER(art) LIKE ? OR " +
          "LOWER(customerref) LIKE ? OR " +
          "LOWER(kommentar) LIKE ?)",
          new String[]{s,s,s,s,s,s,s,s,s,s});
    }


    List<Umsatz> results = new ArrayList<Umsatz>();
    while (list.hasNext())
    {
      de.willuhn.jameica.hbci.rmi.Umsatz uh = (de.willuhn.jameica.hbci.rmi.Umsatz) list.next();
      Umsatz u = new Umsatz();
      u.setId(uh.getID());
      u.setDatum(uh.getDatum());
      u.setKommentar(uh.getKommentar());
      u.setKundenreferenz(uh.getCustomerRef());
      u.setPrimanota(uh.getPrimanota());
      u.setSaldo(uh.getSaldo());
      u.setValuta(uh.getValuta());
      u.setKonto(KontoServiceImpl.copy(uh.getKonto()));

      UmsatzTyp ut = uh.getUmsatzTyp();
      if (ut != null)
        u.setKategorie(ut.getName());
      
      PaymentData data = new PaymentData();
      data.setBetrag(uh.getBetrag());
      data.setGegenkontoBlz(uh.getGegenkontoBLZ());
      data.setGegenkontoName(uh.getGegenkontoName());
      data.setGegenkontoNummer(uh.getGegenkontoNummer());
      data.setTextschluessel(uh.getArt());
      data.setZweck1(uh.getZweck());
      data.setZweck2(uh.getZweck2());
      data.setWeitereVerwendungszwecke(uh.getWeitereVerwendungszwecke());
      u.setPaymentData(data);

      results.add(u);
    }
    return results;
  }

}


/*********************************************************************
 * $Log: UmsatzServiceImpl.java,v $
 * Revision 1.3  2009/04/29 21:15:15  willuhn
 * @N Support fuer erweiterte Verwendungszwecke
 *
 * Revision 1.2  2008/10/27 23:41:43  willuhn
 * @N Umsatz-Service
 *
 * Revision 1.1  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 **********************************************************************/