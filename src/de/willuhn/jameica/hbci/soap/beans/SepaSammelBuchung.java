/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * SOAP-Bean fuer eine einzelne Buchung in einem SEPA-Sammelauftrag.
 */
@XmlSeeAlso({
  SepaSammelLastschriftBuchung.class
})
public class SepaSammelBuchung extends PaymentData
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


