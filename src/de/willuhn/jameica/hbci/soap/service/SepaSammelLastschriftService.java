/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.service;

import javax.jws.WebService;

import de.willuhn.jameica.hbci.soap.beans.SepaSammelLastschrift;
import de.willuhn.jameica.soap.AutoService;


/**
 * Interface fuer den Webservice fuer SEPA-Sammellastschriften.
 */
@WebService
public interface SepaSammelLastschriftService extends PaymentService<SepaSammelLastschrift>, AutoService
{
}
