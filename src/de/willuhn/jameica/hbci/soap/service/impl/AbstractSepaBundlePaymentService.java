/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.ObjectNotFoundException;
import de.willuhn.jameica.hbci.rmi.HBCIDBService;
import de.willuhn.jameica.hbci.rmi.SepaSammelTransfer;
import de.willuhn.jameica.hbci.soap.beans.BundlePayment;
import de.willuhn.jameica.hbci.soap.service.PaymentService;
import de.willuhn.util.ApplicationException;


/**
 * Abstrakte Basis-Implementierung des SEPA-Sammelauftrags-Service.
 * @param <T> der konkrete SOAP-Typ.
 * @param <R> der konkrete Hibiscus-Typ.
 */
public abstract class AbstractSepaBundlePaymentService<T extends BundlePayment, R extends SepaSammelTransfer> extends AbstractService implements PaymentService<T>
{
  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#delete(java.lang.String)
   */
  public void delete(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");
    
    try
    {
      R r = (R) getService().createObject(getType(),id);
      r.delete();
    }
    catch (ApplicationException ae)
    {
      throw new RemoteException(ae.getMessage());
    }
    catch (ObjectNotFoundException e)
    {
      throw new RemoteException("sepa sammel-transfer [id: " + id + "] not found");
    }
  }
  
  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findAllOpen()
   */
  public List<T> findAllOpen() throws RemoteException
  {
    HBCIDBService service = getService();
    DBIterator list = service.createList(getType());
    list.addFilter("ausgefuehrt = 0");

    List<T> payments = new ArrayList<T>();
    while (list.hasNext())
    {
      R uh = (R) list.next();
      payments.add(copy(uh));
    }
    return payments;
  }

  /**
   * @see de.willuhn.jameica.hbci.soap.service.PaymentService#findById(java.lang.String)
   */
  public T findById(String id) throws RemoteException
  {
    if (id == null || id.length() == 0)
      throw new RemoteException("no id given");

    HBCIDBService service = getService();
    R uh = (R) service.createObject(getType(),id);
    return this.copy(uh);
  }

  /**
   * Kopiert die Properties der Hibiscus-Sammeltransfers in die Bean.
   * @param uh Hibiscus-Sammeltransfer.
   * @return SOAP-taugliche Bean.
   * @throws RemoteException
   */
  abstract T copy(R uh) throws RemoteException;
  
  /**
   * Liefert den konkreten Hibiscus-Datentyp.
   * @return der konkrete Hibiscus-Datentyp.
   */
  abstract Class getType();

}
