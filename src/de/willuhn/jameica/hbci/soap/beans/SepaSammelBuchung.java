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
  SepaSammelLastschriftBuchung.class,
  SepaSammelUeberweisungBuchung.class
})
public class SepaSammelBuchung extends PaymentData
{
  private String endToEndId = null;
  private String purposeCode = null;
  
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
  
  /**
   * Liefert den SEPA-Purpose-Code.
   * @return der SEPA-Purpose-Code.
   */
  public String getPurposeCode()
  {
    return purposeCode;
  }
  
  /**
   * Speichert den SEPA-Purpose-Code.
   * @param purposeCode der SEPA-Purpose-Code.
   */
  public void setPurposeCode(String purposeCode)
  {
    this.purposeCode = purposeCode;
  }

}


