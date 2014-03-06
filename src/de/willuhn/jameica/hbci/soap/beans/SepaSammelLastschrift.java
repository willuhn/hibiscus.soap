/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import java.util.Date;


/**
 * SEPA-Sammel-Lastschrift.
 */
public class SepaSammelLastschrift extends BundlePayment<SepaSammelLastschriftBuchung>
{
  private SepaLastSequenceType sequenceType = null;
  private Date targetDate = null;
  private SepaLastType type = null;
  
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
