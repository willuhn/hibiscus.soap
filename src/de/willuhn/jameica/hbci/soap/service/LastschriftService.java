/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/LastschriftService.java,v $
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

import de.willuhn.jameica.hbci.soap.beans.Lastschrift;


/**
 * Interface fuer den Lastschrift-Webservice.
 */
@WebService(name="Lastschrift")
public interface LastschriftService extends PaymentService<Lastschrift>
{
}


/**********************************************************************
 * $Log: LastschriftService.java,v $
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/
