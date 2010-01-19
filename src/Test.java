/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/Test.java,v $
 * $Revision: 1.8 $
 * $Date: 2010/01/19 12:14:11 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 *
 **********************************************************************/

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;

import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.beans.SepaUeberweisung;
import de.willuhn.jameica.hbci.soap.service.SepaUeberweisungService;

/**
 * Test-Client fuer den Zugriff via SOAP.
 */
public class Test
{
  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception
  {
    // URL
    String url = "https://localhost:8080/soap/SepaUeberweisung";
    
    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    factory.setUsername("admin");
    factory.setPassword("test");
    factory.setServiceClass(SepaUeberweisungService.class);
    factory.setAddress(url);
    
    SepaUeberweisungService client = (SepaUeberweisungService) factory.create();
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Ggf SSL initialisieren
    if (url.startsWith("https://"))
    {
      org.apache.cxf.endpoint.Client proxy = ClientProxy.getClient(client);

      HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
      
      TLSClientParameters tcp = new TLSClientParameters();
      tcp.setDisableCNCheck(true);
      tcp.setTrustManagers(new TrustManager[]{new DummyTrustManager()});
      conduit.setTlsClientParameters(tcp);
    }
    ////////////////////////////////////////////////////////////////////////////

    Konto k = new Konto();
    k.setId("2");

    SepaUeberweisung u = new SepaUeberweisung();
    u.setDatum(new Date());
    u.setKonto(k);
    
    PaymentData pd = new PaymentData();
    pd.setBetrag(100d);
    pd.setGegenkontoBlz("GENODEF1P05");
    pd.setGegenkontoName("Max Mustermann");
    pd.setGegenkontoNummer("DE87123456781234567890");
    pd.setZweck1("Zeile 1");
    u.setPaymentData(pd);
    
    System.out.println(client.store(u));
  }
  
  /**
   * Dummy-Trustmanager.
   * ACHTUNG: Hier findet keine Zertifikatspruefung statt. Nur zum Testen nutzen!
   */
  private static class DummyTrustManager implements X509TrustManager
  {
    /**
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

    /**
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

    /**
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public X509Certificate[] getAcceptedIssuers()
    {
      return null;
    }
    
  }
}


/*********************************************************************
 * $Log: Test.java,v $
 * Revision 1.8  2010/01/19 12:14:11  willuhn
 * *** empty log message ***
 *
 * Revision 1.7  2010/01/19 12:11:37  willuhn
 * *** empty log message ***
 *
 * Revision 1.6  2010/01/19 00:34:48  willuhn
 * @N Webservice fuer SEPA-Ueberweisungen
 * @C implizites Webservice-Deployment via AutoService
 * @C Build-Script mit Versionsnummer und Plugin-Name aus plugin.xml
 *
 * Revision 1.5  2008/10/27 23:41:43  willuhn
 * @N Umsatz-Service
 *
 * Revision 1.4  2008/10/27 14:21:19  willuhn
 * @N XmlSeeAlso-Tags
 *
 * Revision 1.3  2008/10/21 00:17:58  willuhn
 * @N Sammel-Auftraege. Geht noch nicht - CXF kommt wohl mit der Vererbung nicht klar
 *
 * Revision 1.2  2008/10/20 00:26:22  willuhn
 * @N Ueberweisung-Service
 *
 * Revision 1.1  2008/10/19 23:50:36  willuhn
 * @N Erste funktionierende Version
 *
 **********************************************************************/