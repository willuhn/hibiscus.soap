/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/KontoService.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/10/19 23:08:15 $
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


/**
 * Interface fuer den Konto-Webservice.
 */
@WebService(name="Konto")
public interface KontoService
{

  // public @WebResult(name="properties") HashMap getProperties(@WebParam(name="uuid") String uuid) throws RemoteException;

}


/**********************************************************************
 * $Log: KontoService.java,v $
 * Revision 1.1  2008/10/19 23:08:15  willuhn
 * @N Initial checkin
 *
 **********************************************************************/
