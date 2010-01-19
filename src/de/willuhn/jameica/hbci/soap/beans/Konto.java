/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/Konto.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/01/19 12:16:37 $
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

import javax.jws.WebMethod;


/**
 * Bean fuer ein Konto
 */
public class Konto implements Serializable
{
  private String id           = null;
  private String kontonummer  = null;
  private String unterkonto   = null;
  private String blz          = null;
  private String bic          = null;
  private String iban         = null;
  private String bezeichnung  = null;
  private String name         = null;
  private String kundennummer = null;
  private Double saldo        = null;
  private Date saldoDatum     = null;
  
  /**
   * Liefert die Bezeichnung des Kontos.
   * @return Bezeichnung des Kontos.
   */
  public String getBezeichnung()
  {
    return bezeichnung;
  }
  
  /**
   * Speichert die Bezeichnung des Kontos.
   * @param bezeichnung
   */
  public void setBezeichnung(String bezeichnung)
  {
    this.bezeichnung = bezeichnung;
  }
  
  /**
   * Liefert die BLZ des Kontos.
   * @return BLZ des Kontos.
   */
  public String getBlz()
  {
    return blz;
  }
  
  /**
   * Speichert die BLZ des Kontos.
   * @param blz BLZ des Kontos.
   */
  public void setBlz(String blz)
  {
    this.blz = blz;
  }
  
  /**
   * Liefert die Kontonummer des Kontos.
   * @return die Kontonummer des Kontos.
   */
  public String getKontonummer()
  {
    return kontonummer;
  }
  
  /**
   * Speichert die Kontonummer des Kontos.
   * @param kontonummer die Kontonummer des Kontos.
   */
  public void setKontonummer(String kontonummer)
  {
    this.kontonummer = kontonummer;
  }
  
  /**
   * Liefert die Kundennummer.
   * @return die Kundennummer.
   */
  public String getKundennummer()
  {
    return kundennummer;
  }
  
  /**
   * Liefert die BIC.
   * @return bic die BIC.
   */
  public String getBic()
  {
    return bic;
  }

  /**
   * Speichert die BIC.
   * @param bic bic die BIC.
   */
  public void setBic(String bic)
  {
    this.bic = bic;
  }

  /**
   * Liefert die IBAN.
   * @return iban die IBAN.
   */
  public String getIban()
  {
    return iban;
  }

  /**
   * Speichert die IBAN.
   * @param iban iban die IBAN.
   */
  public void setIban(String iban)
  {
    this.iban = iban;
  }

  /**
   * Speichert die Kundennummer.
   * @param kundennummer die Kundennummer.
   */
  public void setKundennummer(String kundennummer)
  {
    this.kundennummer = kundennummer;
  }
  
  /**
   * Liefert den Namen des Kontoinhabers.
   * @return Name des Kontoinhabers.
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * Speichert den Namen des Kontoinhabers.
   * @param name Name des Kontoinhabers.
   */
  public void setName(String name)
  {
    this.name = name;
  }
  
  /**
   * Liefert eine optionale Unterkontonummer.
   * @return optionale Unterkontonummer.
   */
  public String getUnterkonto()
  {
    return unterkonto;
  }
  
  /**
   * Speichert eine optionale Unterkontonummer.
   * @param unterkonto Unterkontonummer.
   */
  public void setUnterkonto(String unterkonto)
  {
    this.unterkonto = unterkonto;
  }
  
  /**
   * Liefert den aktuellen Saldo.
   * @return der aktuelle Saldo.
   */
  public Double getSaldo()
  {
    return saldo;
  }
  
  /**
   * Speichert den Saldo.
   * @param saldo
   */
  @WebMethod(exclude=true)
  public void setSaldo(Double saldo)
  {
    this.saldo = saldo;
  }
  
  /**
   * Liefert das Datum des aktuellen Saldo.
   * @return das Datum des Saldos.
   */
  public Date getSaldoDatum()
  {
    return saldoDatum;
  }
  
  /**
   * Liefert das Datum des aktuellen Saldo.
   * @param saldoDatum das Datum des Saldos. 
   */
  @WebMethod(exclude=true)
  public void setSaldoDatum(Date saldoDatum)
  {
    this.saldoDatum = saldoDatum;
  }

  /**
   * Liefert die ID des Kontos.
   * @return die ID des Kontos.
   */
  public String getId()
  {
    return id;
  }
  
  /**
   * Speichert die ID des Kontos.
   * @param id die ID des Kontos.
   */
  public void setId(String id)
  {
    this.id = id;
  }
  
}


/**********************************************************************
 * $Log: Konto.java,v $
 * Revision 1.3  2010/01/19 12:16:37  willuhn
 * @N BIC/IBAN
 *
 * Revision 1.2  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 * Revision 1.1  2008/10/19 23:50:36  willuhn
 * @N Erste funktionierende Version
 *
 **********************************************************************/
