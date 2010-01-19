/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/SepaUeberweisungService.java,v $
 * $Revision: 1.1 $
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

import de.willuhn.jameica.hbci.soap.beans.SepaUeberweisung;
import de.willuhn.jameica.soap.AutoService;


/**
 * Interface fuer den Webservice fuer SEPA-Ueberweisungen.
 */
@WebService
public interface SepaUeberweisungService extends PaymentService<SepaUeberweisung>, AutoService
{
}


/**********************************************************************
 * $Log: SepaUeberweisungService.java,v $
 * Revision 1.1  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 **********************************************************************/
