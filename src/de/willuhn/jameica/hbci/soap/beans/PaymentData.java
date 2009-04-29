/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/PaymentData.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/04/29 21:15:15 $
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


/**
 * Container fuer die Basis-Daten einer Zahlung.
 */
public class PaymentData implements Serializable
{
  private String textschluessel   = null;
  private String gegenkontoNummer = null;
  private String gegenkontoName   = null;
  private String gegenkontoBlz    = null;
  private Double betrag           = null;
  private String zweck1           = null;
  private String zweck2           = null;
  private String[] evz            = null;
  
  /**
   * Liefert den Betrag der Ueberweisung.
   * @return Betrag der Ueberweisung.
   */
  public Double getBetrag()
  {
    return betrag;
  }
  
  /**
   * Speichert den Betrag der Ueberweisung.
   * @param betrag Betrag der Ueberweisung.
   */
  public void setBetrag(Double betrag)
  {
    this.betrag = betrag;
  }
  
  /**
   * Liefert die BLZ des Gegenkontos.
   * @return die BLZ des Gegenkontos.
   */
  public String getGegenkontoBlz()
  {
    return gegenkontoBlz;
  }
  
  /**
   * Speichert die BLZ des Gegenkontos.
   * @param gegenkontoBlz die BLZ des Gegenkontos.
   */
  public void setGegenkontoBlz(String gegenkontoBlz)
  {
    this.gegenkontoBlz = gegenkontoBlz;
  }
  
  /**
   * Liefert den Namen des Gegenkonto-Inhabers.
   * @return der Namen des Gegenkonto-Inhabers.
   */
  public String getGegenkontoName()
  {
    return gegenkontoName;
  }
  
  /**
   * Speichert den Namen des Gegenkonto-Inhabers.
   * @param gegenkontoName der Namen des Gegenkonto-Inhabers.
   */
  public void setGegenkontoName(String gegenkontoName)
  {
    this.gegenkontoName = gegenkontoName;
  }
  
  /**
   * Liefert die Kontonummer des Gegenkontos.
   * @return die Kontonummer des Gegenkontos.
   */
  public String getGegenkontoNummer()
  {
    return gegenkontoNummer;
  }
  
  /**
   * Speichert die Kontonummer des Gegenkontos.
   * @param gegenkontoNummer die Kontonummer des Gegenkontos.
   */
  public void setGegenkontoNummer(String gegenkontoNummer)
  {
    this.gegenkontoNummer = gegenkontoNummer;
  }
  
  /**
   * Liefert einen Textschluessel fuer die Ueberweisung.
   * @return Textschluessel fuer die Ueberweisung.
   */
  public String getTextschluessel()
  {
    return textschluessel;
  }
  
  /**
   * Speichert einen Textschluessel fuer die Ueberweisung.
   * @param textschluessel Textschluessel fuer die Ueberweisung.
   */
  public void setTextschluessel(String textschluessel)
  {
    this.textschluessel = textschluessel;
  }
  
  /**
   * Liefert Verwendungszweck 1.
   * @return Verwendungszweck 1.
   */
  public String getZweck1()
  {
    return zweck1;
  }
  
  /**
   * Speichert Verwendungszweck 1.
   * @param zweck1 Verwendungszweck 1.
   */
  public void setZweck1(String zweck1)
  {
    this.zweck1 = zweck1;
  }
  
  /**
   * Liefert Verwendungszweck 2.
   * @return Verwendungszweck 2.
   */
  public String getZweck2()
  {
    return zweck2;
  }
  
  /**
   * Speichert Verwendungszweck 2.
   * @param zweck2 Verwendungszweck 2.
   */
  public void setZweck2(String zweck2)
  {
    this.zweck2 = zweck2;
  }
  
  /**
   * Liefert eine Liste erweiterter Verwendungszwecke.
   * @return Liste erweiterter Verwendungszwecke.
   */
  public String[] getWeitereVerwendungszwecke()
  {
    return this.evz;
  }
  
  /**
   * Speichert die Liste erweiterter Verwendungszwecke.
   * @param lines Liste erweiterter Verwendungszwecke.
   */
  public void setWeitereVerwendungszwecke(String[] lines)
  {
    this.evz = lines;
  }

}


/**********************************************************************
 * $Log: PaymentData.java,v $
 * Revision 1.2  2009/04/29 21:15:15  willuhn
 * @N Support fuer erweiterte Verwendungszwecke
 *
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/
