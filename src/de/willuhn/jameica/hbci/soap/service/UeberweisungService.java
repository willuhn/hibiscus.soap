/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/UeberweisungService.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/01/19 00:34:48 $
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
import de.willuhn.jameica.soap.AutoService;


/**
 * Interface fuer den Ueberweisungs-Webservice.
 */
@WebService
public interface UeberweisungService extends PaymentService<Ueberweisung>, AutoService
{
}


/**********************************************************************
 * $Log: UeberweisungService.java,v $
 * Revision 1.3  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 * Revision 1.2  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 * Revision 1.1  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 **********************************************************************/
