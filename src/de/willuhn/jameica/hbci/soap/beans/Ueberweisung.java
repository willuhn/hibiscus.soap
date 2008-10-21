/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/beans/Ueberweisung.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/10/21 00:17:58 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;



/**
 * SOAP-Bean fuer eine Ueberweisung.
 */
public class Ueberweisung extends SinglePayment
{
  private boolean isTerminauftrag = false;
  
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
 * Revision 1.2  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
