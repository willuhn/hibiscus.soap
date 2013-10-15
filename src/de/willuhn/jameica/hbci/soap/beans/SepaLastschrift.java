/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import java.util.Date;

import de.willuhn.jameica.hbci.rmi.SepaLastSequenceType;
import de.willuhn.jameica.hbci.rmi.SepaLastType;



/**
 * SOAP-Bean fuer eine SEPA-Lastschrift.
 */
public class SepaLastschrift extends SepaPayment
{
  private String mandateId = null;
  private String creditorId = null;
  private Date signatureDate = null;
  private SepaLastSequenceType sequenceType = null;
  private Date targetDate = null;
  private SepaLastType type = null;
  
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
  
  /**
   * Liefert den Sequenz-Typ der Lastschrft.
   * @return der Sequenz-Typ der Lastschrift.
   */
  public SepaLastSequenceType getSequenceType()
  {
    return this.sequenceType;
  }
  
  /**
   * Speichert den Sequenz-Typ der Lastschrift.
   * @param type der Sequenz-Typ der Lastschrift.
   */
  public void setSequenceType(SepaLastSequenceType type)
  {
    this.sequenceType = type;
  }
  
  /**
   * Liefert das Ziel-Ausfuehrungsdatum bei der Bank.
   * @return das Ziel-Ausfuehrungsdatum bei der Bank.
   */
  public Date getTargetDate()
  {
    return this.targetDate;
  }
  
  /**
   * Speichert das Ziel-Ausfuehrungsdatum bei der Bank.
   * @param date das Ziel-Ausfuehrungsdatum bei der Bank.
   */
  public void setTargetDate(Date date)
  {
    this.targetDate = date;
  }
  
  /**
   * Liefert den Typ der Lastschrft.
   * @return der Typ der Lastschrift.
   */
  public SepaLastType getType()
  {
    return this.type;
  }
  
  /**
   * Speichert den Typ der Lastschrift.
   * @param type der Typ der Lastschrift.
   */
  public void setType(SepaLastType type)
  {
    this.type = type;
  }
  

}
