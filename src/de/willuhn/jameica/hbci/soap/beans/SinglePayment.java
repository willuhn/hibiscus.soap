/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import javax.xml.bind.annotation.XmlSeeAlso;



/**
 * Abstracte SOAP-Bean fuer einen Einzelauftrag.
 */
@XmlSeeAlso({
  Umsatz.class,
  SepaUeberweisung.class,
  SepaLastschrift.class
})
public abstract class SinglePayment extends Payment
{
  private PaymentData paymentData = null;

  
  /**
   * Liefert die Zahlungsdaten.
   * @return die Zahlungsdaten.
   */
  public PaymentData getPaymentData()
  {
    return paymentData;
  }

  /**
   * Speichert die Zahlungsdaten.
   * @param paymentData
   */
  public void setPaymentData(PaymentData paymentData)
  {
    this.paymentData = paymentData;
  }
  
  
}
