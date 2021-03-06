/**********************************************************************
 *
 * Copyright (c) by Olaf Willuhn
 * All rights reserved
 *
 **********************************************************************/

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;

import de.willuhn.jameica.hbci.soap.beans.Konto;
import de.willuhn.jameica.hbci.soap.beans.SepaLastSequenceType;
import de.willuhn.jameica.hbci.soap.beans.SepaLastType;
import de.willuhn.jameica.hbci.soap.beans.SepaSammelLastschrift;
import de.willuhn.jameica.hbci.soap.beans.SepaSammelLastschriftBuchung;
import de.willuhn.jameica.hbci.soap.service.KontoService;
import de.willuhn.jameica.hbci.soap.service.SepaSammelLastschriftService;

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
    Konto konto = null;
    
    {
      final KontoService client = createInstance("https://localhost:8080/soap/Konto",KontoService.class);
      List<Konto> konten = client.findAll();
      for (Konto k:konten)
      {
        System.out.println(k.getId() + ": " + k.getKontonummer() + " - " + k.getName());
        if (k.getIban() != null)
          konto = k;
      }
    }

    {
      final SepaSammelLastschriftService client = createInstance("https://localhost:8080/soap/SepaSammelLastschrift",SepaSammelLastschriftService.class);
      

      SepaSammelLastschrift u = new SepaSammelLastschrift();
      u.setDatum(new Date());
      u.setBezeichnung("Test");
      u.setSequenceType(SepaLastSequenceType.FRST);
      u.setTargetDate(new Date());
      u.setType(SepaLastType.CORE);
      u.setKonto(konto);

      for (int i=0;i<3;++i)
      {
        SepaSammelLastschriftBuchung buchung = new SepaSammelLastschriftBuchung();
        buchung.setBetrag(100d + i);
        buchung.setGegenkontoBlz("GENODEF1P05"); // BIC
        buchung.setGegenkontoName("Max Mustermann");
        buchung.setGegenkontoNummer("DE87123456781234567890"); // IBAN
        buchung.setZweck1("Zeile");
        buchung.setCreditorId("DE98ZZZ09999999999");
        buchung.setEndToEndId("NOTPROVIDED");
        buchung.setMandateId("12345");
        buchung.setSignatureDate(new Date());
        u.add(buchung);
      }
      
      String id = client.store(u);
      
      SepaSammelLastschrift l = client.findById(id);
      System.out.println(l.getBezeichnung());
      System.out.println(l.getId());
      System.out.println(l.getDatum());
      System.out.println(l.getSequenceType());
      System.out.println(l.getType());
      List<SepaSammelLastschriftBuchung> buchungen = l.getBuchungen();
      for (SepaSammelLastschriftBuchung b:buchungen)
      {
        System.out.println(b.getCreditorId());
        System.out.println(b.getEndToEndId());
        System.out.println(b.getPurposeCode());
        System.out.println(b.getBetrag());
      }
    }
  }
  
  private static <T> T createInstance(String url, Class<T> type)
  {
    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    factory.setUsername("admin");
    factory.setPassword("test");
    factory.setServiceClass(type);
    factory.setAddress(url);
    
    T client = (T) factory.create();
    
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

    return client;
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
