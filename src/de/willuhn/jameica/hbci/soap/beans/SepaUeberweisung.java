/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/SepaUeberweisung.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/01/19 00:34:48 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;



/**
 * SOAP-Bean fuer eine SEPA-Ueberweisung.
 */
public class SepaUeberweisung extends SinglePayment
{
  private String endToEndId = null;
  
  /**
   * Liefert die EndtoEnd-ID.
   * @return die EndtoEnd-ID.
   */
  public String getEndToEndId()
  {
    return this.endToEndId;
  }
  
  /**
   * Speichert die EndtoEnd-ID.
   * @param id die EndtoEnd-ID.
   */
  public void setEndToEndId(String id)
  {
    this.endToEndId = id;
  }

}
