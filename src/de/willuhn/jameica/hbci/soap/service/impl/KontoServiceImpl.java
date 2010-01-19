/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/de/willuhn/jameica/hbci/soap/service/impl/KontoServiceImpl.java,v $
 * $Revision: 1.5 $
 * $Date: 2010/01/19 12:16:37 $
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
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.service.KontoService;


/**
 * Implementierung des Konto-Service.
 */
@WebService(endpointInterface="de.willuhn.jameica.hbci.soap.service.KontoService",name="Konto")
public class KontoServiceImpl extends AbstractService implements KontoService
{

  /**
   * @see de.willuhn.jameica.hbci.soap.service.KontoService#findAll()
   */
  public List<Konto> findAll() throws RemoteException
  {
    HBCIDBService service = getService();
    DBIterator list = service.createList(de.willuhn.jameica.hbci.rmi.Konto.class);
    List<Konto> konten = new ArrayList<Konto>();
    while (list.hasNext())
    {
      de.willuhn.jameica.hbci.rmi.Konto kh = (de.willuhn.jameica.hbci.rmi.Konto) list.next();
      konten.add(copy(kh));
    }
    return konten;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.KontoService#findById(java.lang.String)
   */
  public Konto findById(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");

    HBCIDBService service = getService();
    de.willuhn.jameica.hbci.rmi.Konto kh = (de.willuhn.jameica.hbci.rmi.Konto) service.createObject(de.willuhn.jameica.hbci.rmi.Konto.class,id);
    return copy(kh);
  }
  
  /**
   * Kopiert die Properties des Hibiscus-Kontos in die Bean.
   * @param kh Hibiscus-Konto.
   * @return SOAP-taugliche Bean.
   * @throws RemoteException
   */
  static Konto copy(de.willuhn.jameica.hbci.rmi.Konto kh) throws RemoteException
  {
    Konto k = new Konto();
    k.setBezeichnung(kh.getBezeichnung());
    k.setBlz(kh.getBLZ());
    k.setId(kh.getID());
    k.setKontonummer(kh.getKontonummer());
    k.setKundennummer(kh.getKundennummer());
    k.setIban(kh.getIban());
    k.setBic(kh.getBic());
    k.setName(kh.getName());
    k.setUnterkonto(kh.getUnterkonto());
    k.setSaldo(kh.getSaldo());
    k.setSaldoDatum(kh.getSaldoDatum());
    return k;
  }
}


/**********************************************************************
 * $Log: KontoServiceImpl.java,v $
 * Revision 1.5  2010/01/19 12:16:37  willuhn
 * @N BIC/IBAN
 *
 * Revision 1.4  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 * Revision 1.3  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 * Revision 1.2  2008/10/19 23:50:36  willuhn
 * @N Erste funktionierende Version
 *
 * Revision 1.1  2008/10/19 23:08:15  willuhn
 * @N Initial checkin
 *
 **********************************************************************/
