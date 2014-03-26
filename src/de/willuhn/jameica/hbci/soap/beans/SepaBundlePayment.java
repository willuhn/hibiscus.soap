/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * SOAP-Bean fuer einen SEPA-Sammel-Auftrag.
 * @param <T> der konkrete Typ der Buchungen.
 */
@XmlSeeAlso({
  SepaSammelLastschrift.class,
  SepaSammelUeberweisung.class
})
public abstract class SepaBundlePayment<T extends PaymentData> extends BundlePayment<T>
{
  private Boolean batchBooking = null;
  
  /**
   * Liefert das optionale Batchbooking-Flag.
   * @return das optionale Batchbooking-Flag.
   */
  public Boolean getBatchBooking()
  {
    return this.batchBooking;
  }
  
  /**
   * Speichert das optionale Batchbooking-Flag.
   * @param b das optionale Batchbooking-Flag.
   */
  public void setBatchBooking(Boolean b)
  {
    this.batchBooking = b;
  }
}
