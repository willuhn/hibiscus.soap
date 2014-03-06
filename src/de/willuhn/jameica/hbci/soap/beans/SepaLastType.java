/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;



/**
 * Die verschiedenen Typen bei SEPA-Lastschriften.
 */
public enum SepaLastType
{

  /**
   * Basis-Lastschrift
   */
  CORE,
  
  /**
   * Basis-Lastschrift mit verkuerztem Vorlauf.
   */
  COR1,
  
  /**
   * B2B-Lastschrift
   */
  B2B,
  
  ;
  
}


