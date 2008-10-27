/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/Umsatz.java,v $
 * $Revision: 1.1 $
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

import javax.jws.WebMethod;

/**
 * Bean fuer eine Umsatzzeile.
 */
public class Umsatz implements Serializable
{
  private PaymentData paymentData = null;
  private Date datum              = null;
  private Date valuta             = null;
  private Double saldo            = null;
  private String primanota        = null;
  private String kundenreferenz   = null;
  private String kommentar        = null;
  private String kategorie        = null;
  
  /**
   * Liefert die Zahlungsdaten.
   * @return die Zahlungsdaten.
   */
  public PaymentData getPaymentData()
  {
    return paymentData;
  }

  /**
   * Speichert die Zahlungsdaten.
   * @param paymentData
   */
  @WebMethod(exclude=true)
  public void setPaymentData(PaymentData paymentData)
  {
    this.paymentData = paymentData;
  }

  /**
   * Liefert die Kundenreferenz.
   * @return die Kundenreferenz.
   */
  public String getKundenreferenz()
  {
    return this.kundenreferenz;
  }

  /**
   * Speichert die Kundenreferenz.
   * @param kundenreferenz Kundenreferenz.
   */
  @WebMethod(exclude=true)
  public void setKundenreferenz(String kundenreferenz)
  {
    this.kundenreferenz = kundenreferenz;
  }

  /**
   * Liefert das Datum.
   * @return das Datum.
   */
  public Date getDatum()
  {
    return this.datum;
  }

  /**
   * Speichert das Datum.
   * @param datum
   */
  @WebMethod(exclude=true)
  public void setDatum(Date datum)
  {
    this.datum = datum;
  }

  /**
   * Liefert den Namen der Kategorie.
   * @return Name der Kategorie.
   */
  public String getKategorie()
  {
    return this.kategorie;
  }

  /**
   * Speichert den Namen der Kategorie.
   * @param kategorie Name der Kategorie.
   */
  @WebMethod(exclude=true)
  public void setKategorie(String kategorie)
  {
    this.kategorie = kategorie;
  }

  /**
   * Liefert den Kommentar.
   * @return der Kommentar.
   */
  public String getKommentar()
  {
    return this.kommentar;
  }

  /**
   * Speichert den Kommentar.
   * @param kommentar
   */
  @WebMethod(exclude=true)
  public void setKommentar(String kommentar)
  {
    this.kommentar = kommentar;
  }

  /**
   * Liefert das Primanota-Kennzeichen.
   * @return Primanota.
   */
  public String getPrimanota()
  {
    return this.primanota;
  }

  /**
   * Speichert das Primanota-Kennzeichen.
   * @param primanota Primanota.
   */
  @WebMethod(exclude=true)
  public void setPrimanota(String primanota)
  {
    this.primanota = primanota;
  }

  /**
   * Liefert die Zwischensumme (Saldo).
   * @return Zwischensumme.
   */
  public Double getSaldo()
  {
    return this.saldo;
  }

  /**
   * Speichert die Zwischensumme.
   * @param saldo Zwischensumme.
   */
  @WebMethod(exclude=true)
  public void setSaldo(Double saldo)
  {
    this.saldo = saldo;
  }

  /**
   * Liefert das Valuta-Datum.
   * @return Valuta-Datum.
   */
  public Date getValuta()
  {
    return this.valuta;
  }

  /**
   * Speichert das Valuta-Datum.
   * @param valuta Valuta-Datum.
   */
  @WebMethod(exclude=true)
  public void setValuta(Date valuta)
  {
    this.valuta = valuta;
  }
  
}


/*********************************************************************
 * $Log: Umsatz.java,v $
 * Revision 1.1  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 **********************************************************************/