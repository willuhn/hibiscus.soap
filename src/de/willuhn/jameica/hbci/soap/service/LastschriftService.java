/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/LastschriftService.java,v $
 * $Revision: 1.2 $
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

import de.willuhn.jameica.hbci.soap.beans.Lastschrift;
import de.willuhn.jameica.soap.AutoService;


/**
 * Interface fuer den Lastschrift-Webservice.
 */
@WebService
public interface LastschriftService extends PaymentService<Lastschrift>, AutoService
{
}


/**********************************************************************
 * $Log: LastschriftService.java,v $
 * Revision 1.2  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/
