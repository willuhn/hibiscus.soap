/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/SinglePayment.java,v $
 * $Revision: 1.5 $
 * $Date: 2010/01/20 10:39:26 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import javax.xml.bind.annotation.XmlSeeAlso;



/**
 * Abstracte SOAP-Bean fuer einen Einzelauftrag.
 */
@XmlSeeAlso({
  Ueberweisung.class,
  Lastschrift.class,
  Umsatz.class,
  SepaUeberweisung.class
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


/**********************************************************************
 * $Log: SinglePayment.java,v $
 * Revision 1.5  2010/01/20 10:39:26  willuhn
 * @B javac stoert sich offensichtlich am letzten Komma der Annotation
 *
 * Revision 1.4  2010/01/19 11:36:10  willuhn
 * @B Fehlende Annotations
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
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
