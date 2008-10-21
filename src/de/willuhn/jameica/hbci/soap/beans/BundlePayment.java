/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/BundlePayment.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/10/21 00:17:58 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import java.util.ArrayList;
import java.util.List;


/**
 * SOAP-Bean fuer einen Sammel-Auftrag.
 */
public abstract class BundlePayment extends Payment
{
  private String bezeichnung         = null;
  private List<PaymentData> payments = null;
  
  /**
   * Fuegt eine Buchung hinzu.
   * @param buchung neue Buchung.
   */
  public void add(PaymentData payment)
  {
    if (this.payments == null)
      this.payments = new ArrayList<PaymentData>();
    this.payments.add(payment);
  }

  /**
   * Liefert die Liste der Buchungen.
   * @return Liste der Buchungen.
   */
  public List<PaymentData> getBuchungen()
  {
    return payments;
  }

  /**
   * Speichert die Liste der Buchungen.
   * @param buchungen Liste der Buchungen.
   */
  public void setBuchungen(List<PaymentData> payments)
  {
    this.payments = payments;
  }

  /**
   * Liefert die Bezeichnung des Sammelauftrages.
   * @return Bezeichnung des Sammelauftrages.
   */
  public String getBezeichnung()
  {
    return bezeichnung;
  }
  
  /**
   * Speichert die Bezeichnung des Sammelauftrages.
   * @param bezeichnung
   */
  public void setBezeichnung(String bezeichnung)
  {
    this.bezeichnung = bezeichnung;
  }
  
  
}


/**********************************************************************
 * $Log: BundlePayment.java,v $
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/