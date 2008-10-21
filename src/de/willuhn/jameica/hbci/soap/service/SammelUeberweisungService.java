/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/SammelUeberweisungService.java,v $
 * $Revision: 1.1 $
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

import de.willuhn.jameica.hbci.soap.beans.SammelUeberweisung;


/**
 * Interface fuer den Sammel-Ueberweisung-Webservice.
 */
@WebService(name="SammelUeberweisung")
public interface SammelUeberweisungService extends PaymentService<SammelUeberweisung>
{
}


/**********************************************************************
 * $Log: SammelUeberweisungService.java,v $
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/
