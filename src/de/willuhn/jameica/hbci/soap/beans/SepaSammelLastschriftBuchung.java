/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import java.util.Date;

/**
 * SOAP-Bean fuer eine einzelne Buchung in einer SEPA-Sammellastschrift.
 */
public class SepaSammelLastschriftBuchung extends SepaSammelBuchung
{
  private String mandateId = null;
  private String creditorId = null;
  private Date signatureDate = null;
  
  /**
   * Liefert die Mandats-ID.
   * @return die Mandats-ID.
   */
  public String getMandateId()
  {
    return this.mandateId;
  }
  
  /**
   * Speichert die Mandats-ID.
   * @param id die Mandats-ID.
   */
  public void setMandateId(String id)
  {
    this.mandateId = id;
  }
  
  /**
   * Liefert die Glaeubiger-ID.
   * @return die Glaeubiger-ID.
   */
  public String getCreditorId()
  {
    return this.creditorId;
  }
  
  /**
   * Speichert die Glaeubiger-ID.
   * @param id die Glaeubiger-ID.
   */
  public void setCreditorId(String id)
  {
    this.creditorId = id;
  }

  /**
   * Liefert das Datum der Unterschrift des Mandats.
   * @return das Datum der Unterschrift des Mandats.
   */
  public Date getSignatureDate()
  {
    return this.signatureDate;
  }
  
  /**
   * Speichert das Datum der Unterschrift des Mandats.
   * @param date das Datum der Unterschrift des Mandats.
   */
  public void setSignatureDate(Date date)
  {
    this.signatureDate = date;
  }
  
}


