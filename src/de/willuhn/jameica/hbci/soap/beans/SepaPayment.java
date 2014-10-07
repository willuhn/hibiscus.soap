/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import javax.xml.bind.annotation.XmlSeeAlso;



/**
 * Abstracte SOAP-Bean fuer einen SEPA-Auftrag.
 */
@XmlSeeAlso({
  SepaUeberweisung.class,
  SepaLastschrift.class
})
public abstract class SepaPayment extends SinglePayment
{
  private String endToEndId = null;
  private String pmtInfId = null;
  
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
   * Liefert die Payment-Information-ID.
   * @return pmtInfId die Payment-Information-ID.
   */
  public String getPmtInfId()
  {
    return pmtInfId;
  }
  
  /**
   * Speichert die Payment-Information-ID.
   * @param pmtInfId pmtInfId
   */
  public void setPmtInfId(String pmtInfId)
  {
    this.pmtInfId = pmtInfId;
  }

}
