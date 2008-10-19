/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus.soap/src/Test.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/10/19 23:50:36 $
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
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;

import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.service.KontoService;

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
    String url = "https://localhost:8080/soap/Konto";
    
    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    factory.setServiceClass(KontoService.class);
    factory.setAddress(url);
    
    KontoService client = (KontoService) factory.create();
    
    
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
    
    List<Konto> konten = client.findAll();
    for (int i=0;i<konten.size();++i)
    {
      Konto k = konten.get(i);
      System.out.println(k.getId() + ": " + k.getKontonummer());
    }
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
 * Revision 1.1  2008/10/19 23:50:36  willuhn
 * @N Erste funktionierende Version
 *
 **********************************************************************/