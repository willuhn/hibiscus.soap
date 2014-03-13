/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * SOAP-Bean fuer einen Sammel-Auftrag.
 * @param <T> der konkrete Typ der Buchungen.
 */
@XmlSeeAlso({
  SammelUeberweisung.class,
  SammelLastschrift.class,
  SepaSammelLastschrift.class,
  SepaSammelUeberweisung.class
})
public abstract class BundlePayment<T extends PaymentData> extends Payment
{
  private String bezeichnung = null;
  private List<T> payments   = null;
  
  /**
   * Fuegt eine Buchung hinzu.
   * @param payment neue Buchung.
   */
  public void add(T payment)
  {
    if (this.payments == null)
      this.payments = new ArrayList<T>();
    this.payments.add(payment);
  }

  /**
   * Liefert die Liste der Buchungen.
   * @return Liste der Buchungen.
   */
  public List<T> getBuchungen()
  {
    return payments;
  }

  /**
   * Speichert die Liste der Buchungen.
   * @param payments Liste der Buchungen.
   */
  public void setBuchungen(List<T> payments)
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
