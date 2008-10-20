/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/Ueberweisung.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/10/20 00:26:22 $
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


/**
 * SOAP-Bean fuer eine Ueberweisung.
 */
public class Ueberweisung implements Serializable
{
  private Date termin             = null;
  private String textschluessel   = null;
  private Konto konto             = null;
  private String gegenkontoNummer = null;
  private String gegenkontoName   = null;
  private String gegenkontoBlz    = null;
  private Double betrag           = null;
  private String zweck1           = null;
  private String zweck2           = null;
  private String id               = null;
  private boolean isTerminauftrag = false;
  
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
   * True, wenn es sich um eine Termin-Ueberweisung handelt.
   * @return True, wenn es sich um eine Termin-Ueberweisung handelt.
   */
  public boolean isTerminauftrag()
  {
    return isTerminauftrag;
  }

  /**
   * Legt fest, ob es sich um eine Termin-Ueberweisung handelt.
   * @param isTerminauftrag true, wenn es sich um eine Termin-Ueberweisung handelt.
   */
  public void setTerminauftrag(boolean isTerminauftrag)
  {
    this.isTerminauftrag = isTerminauftrag;
  }
  
  
  
}


/**********************************************************************
 * $Log: Ueberweisung.java,v $
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
