/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/Payment.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/10/27 14:21:19 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * Eine Zahlung.
 */
@XmlSeeAlso({
  Ueberweisung.class,
  Lastschrift.class,
  SammelUeberweisung.class,
  SammelLastschrift.class
})
public abstract class Payment implements Serializable
{
  private Konto konto = null;
  private String id   = null;
  private Date termin = null;

  /**
   * Liefert das Konto, ueber das der Auftrag gesendet werden soll.
   * @return das Konto, ueber das der Auftrag gesendet werden soll.
   */
  public Konto getKonto()
  {
    return konto;
  }
  
  /**
   * Speichert das Konto, ueber das der Auftrag gesendet werden soll.
   * @param konto das Konto, ueber das der Auftrag gesendet werden soll.
   */
  public void setKonto(Konto konto)
  {
    this.konto = konto;
  }
  
  /**
   * Liefert die ID.
   * @return die ID.
   */
  public String getId()
  {
    return id;
  }
  
  /**
   * Speichert die ID.
   * @param id die ID.
   */
  public void setId(String id)
  {
    this.id = id;
  }
  
  /**
   * Liefert einen optionalen Zieltermin.
   * @return ein optionaler Zieltermin.
   */
  public Date getTermin()
  {
    return termin;
  }
  
  /**
   * Speichert einen optionalen Zieltermin.
   * @param termin der Zieltermin.
   */
  public void setTermin(Date termin)
  {
    this.termin = termin;
  }
}


/**********************************************************************
 * $Log: Payment.java,v $
 * Revision 1.2  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/
