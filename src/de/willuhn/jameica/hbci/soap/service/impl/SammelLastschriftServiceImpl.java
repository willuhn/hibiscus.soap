/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/SammelLastschriftServiceImpl.java,v $
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

package de.willuhn.jameica.hbci.soap.service.impl;

import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.SammelLastschrift;
import de.willuhn.jameica.hbci.soap.service.SammelLastschriftService;


/**
 * Implementierung des SammelLastschrift-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.SammelLastschriftService")
public class SammelLastschriftServiceImpl extends AbstractBundlePaymentService<SammelLastschrift> implements SammelLastschriftService
{
  /**
   * @see de.willuhn.jameica.hbci.soap.service.impl.AbstractBundlePaymentService#getType()
   */
  Class getType()
  {
    return de.willuhn.jameica.hbci.rmi.SammelLastschrift.class;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.impl.AbstractBundlePaymentService#create()
   */
  SammelLastschrift create()
  {
    return new SammelLastschrift();
  }
}


/**********************************************************************
 * $Log: SammelLastschriftServiceImpl.java,v $
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/