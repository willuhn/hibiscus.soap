/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.hbci.soap.beans;



/**
 * Die verschiedenen Sequence-Typen bei SEPA-Lastschriften.
 */
public enum SepaLastSequenceType
{
  /**
   * Sequenz-Typ FRST fuer Erst-Einzug.
   */
  FRST,
  
  /**
   * Sequenz-Typ RCUR fuer Folge-Einzug.
   */
  RCUR,
  
  /**
   * Sequenz-Typ OOFF fuer Einmal-Einzug.
   */
  OOFF,
  
  /**
   * Sequenz-Typ FNAL fuer letztmaligen Einzug.
   */
  FNAL,
  
  ;
}


