/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/UeberweisungService.java,v $
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

package de.willuhn.jameica.hbci.soap.service;

import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.Ueberweisung;


/**
 * Interface fuer den Ueberweisungs-Webservice.
 */
@WebService(name="Ueberweisung")
public interface UeberweisungService extends PaymentService<Ueberweisung>
{
}


/**********************************************************************
 * $Log: UeberweisungService.java,v $
 * Revision 1.2  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/