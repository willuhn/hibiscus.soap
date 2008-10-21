/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/SammelUeberweisungServiceImpl.java,v $
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

import de.willuhn.jameica.hbci.soap.beans.SammelUeberweisung;
import de.willuhn.jameica.hbci.soap.service.SammelUeberweisungService;


/**
 * Implementierung des SammelUeberweisung-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.SammelUeberweisungService")
public class SammelUeberweisungServiceImpl extends AbstractBundlePaymentService<SammelUeberweisung> implements SammelUeberweisungService
{
  /**
   * @see de.willuhn.jameica.hbci.soap.service.impl.AbstractBundlePaymentService#getType()
   */
  Class getType()
  {
    return de.willuhn.jameica.hbci.rmi.SammelUeberweisung.class;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.impl.AbstractBundlePaymentService#create()
   */
  SammelUeberweisung create()
  {
    return new SammelUeberweisung();
  }
  
}


/**********************************************************************
 * $Log: SammelUeberweisungServiceImpl.java,v $
 * Revision 1.1  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 **********************************************************************/