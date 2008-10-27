/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/UmsatzServiceImpl.java,v $
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

package de.willuhn.jameica.hbci.soap.service.impl;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.Umsatz;
import de.willuhn.jameica.hbci.soap.service.UmsatzService;

/**
 * Implementierung des Umsatz-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.UmsatzService")
public class UmsatzServiceImpl implements UmsatzService
{

  /**
   * @see de.willuhn.jameica.hbci.soap.service.UmsatzService#find(de.willuhn.jameica.hbci.soap.beans.Konto, java.util.Date, java.util.Date, java.lang.String)
   */
  public List<Umsatz> find(Konto konto, Date von, Date bis, String text)
      throws RemoteException
  {
    // TODO Auto-generated method stub
    return null;
  }

}


/*********************************************************************
 * $Log: UmsatzServiceImpl.java,v $
 * Revision 1.1  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 **********************************************************************/