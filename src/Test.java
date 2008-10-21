/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/Test.java,v $
 * $Revision: 1.3 $
 * $Date: 2008/10/21 00:17:58 $
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
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;

import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.PaymentData;
import de.willuhn.jameica.hbci.soap.beans.SammelUeberweisung;
import de.willuhn.jameica.hbci.soap.service.SammelUeberweisungService;

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
    String url = "https://localhost:8080/soap/SammelUeberweisung";
    
    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    factory.setServiceClass(SammelUeberweisungService.class);
    factory.setAddress(url);
    
    SammelUeberweisungService client = (SammelUeberweisungService) factory.create();
    
    
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
      
      // Authentifizierung noetig?
      AuthorizationPolicy auth = conduit.getAuthorization();
      if (auth == null) auth = new AuthorizationPolicy();
      auth.setUserName("admin");
      auth.setPassword("test");
    }
    ////////////////////////////////////////////////////////////////////////////

    Konto k = new Konto();
    k.setId("8");
    
    SammelUeberweisung u = new SammelUeberweisung();
    u.setKonto(k);
    u.setTermin(new Date());
    
    for (int i=0;i<10;++i)
    {
      PaymentData data = new PaymentData();
      data.setBetrag((double) 100 + i);
      data.setGegenkontoBlz("12345678");
      data.setGegenkontoName("Max Mustermann");
      data.setGegenkontoNummer("123457890");
      data.setZweck1("SOAP 1");
      data.setZweck2("SOAP 2");
      u.add(data);
    }
    
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