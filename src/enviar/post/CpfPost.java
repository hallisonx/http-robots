package enviar.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.net.ssl.SSLSocketFactory;


public class CpfPost {

	private void sendPost(String cpf) throws Exception {

		try {
			
			String url = "https://www.situacaocadastral.com.br/";
			URL obj = new URL(url);
			SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
			Proxy proxy = new Proxy(Type.HTTP, addr);
			//HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
			HttpsURLConnection con = (HttpsURLConnection)obj.openConnection(proxy);
	
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", "www.situacaocadastral.com.br");
	        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
	        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
	        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        con.setRequestProperty("Referer", "https://www.situacaocadastral.com.br/");
	        con.setRequestProperty("Cookie", "__cfduid=dcccf446d70cf9f850a3ea9dca8c08ad31489756794; __utma=180780453.2002100046.1489756800.1493758480.1493834106.9; __utmz=180780453.1493397885.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not provided); _referrer_og=https://www.google.com.br/; _jsuid=2730345620; USID=12f629f9249a5b692095f866a73e491a; __utmc=180780453; __utmb=180780453.3.10.1493834106; __utmt=1; _first_pageview=1; heatmaps_g2g_100536568=yes; _eventqueue={\"heatmap\":[],\"events\":[]};");
	        con.setRequestProperty("Connection", "keep-alive");
	        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
	        con.setRequestProperty("Cache-Control", "max-age=0, no-cache");
	        con.setRequestProperty("Pragma", "no-cache");

			
	
			String urlParameters = "doc="+cpf+"&6f4922f45568161a8cdf4ad2299f6d23=MzY4NDQ3NTkwMXxNb3ppbGxhLzUuMCAoV2luZG93cyBOVCA2LjE7IFdPVzY0OyBydjo1My4wKSBHZWNrby8yMDEwMDEwMSBGaXJlZm94LzUzLjB8aHR0cHM6Ly93d3cuc2l0dWFjYW9jYWRhc3RyYWwuY29tLmJyL3xodHRwczovL3d3dy5zaXR1YWNhb2NhZGFzdHJhbC5jb20uYnIvfHRydWV8MTM2Nng3Njh4MjR8MTM2Nng2OTc=";
	
			// Send post request
			con.setDoOutput(true);
			OutputStream wr = con.getOutputStream();
			wr.write(urlParameters.getBytes());
			wr.flush();
			wr.close();
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "ISO-8859-1"));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println("response: "+ response); 
		
		} catch (UnknownHostException e) {
	        System.out.println("Erro (UnknownHostException): "+e.getMessage());
	        Thread.sleep(1000);
	    }

	}
	
	public void apacheHttpClient() throws HttpException, IOException{
		
		  HttpClient httpclient = new HttpClient();
		  httpclient.getHostConfiguration().setProxy("proxy.tjpb.jus.br", 3128);
		  PostMethod httppost = new PostMethod("https://www.situacaocadastral.com.br");
		  httppost.setRequestHeader("Host", "www.situacaocadastral.com.br");
		  httppost.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		  httppost.setRequestHeader("Accept", "text/plain");
		  httppost.setRequestHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
		  httppost.setRequestHeader("Accept-Encoding", "gzip, deflate, br");
		  httppost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		  httppost.setRequestHeader("Referer", "https://www.situacaocadastral.com.br/");
		  httppost.setRequestHeader("Cookie", "__cfduid=dcccf446d70cf9f850a3ea9dca8c08ad31489756794; __utma=180780453.2002100046.1489756800.1493758480.1493834106.9; __utmz=180780453.1493397885.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not provided); _referrer_og=https://www.google.com.br/; _jsuid=2730345620; USID=12f629f9249a5b692095f866a73e491a; __utmc=180780453; __utmb=180780453.3.10.1493834106; __utmt=1; _first_pageview=1; heatmaps_g2g_100536568=yes; _eventqueue={\"heatmap\":[],\"events\":[]};");
		  httppost.setRequestHeader("Connection", "keep-alive");
		  httppost.setRequestHeader("Upgrade-Insecure-Requests", "1");
		  httppost.setRequestHeader("Cache-Control", "max-age=0, no-cache");
		  httppost.setRequestHeader("Pragma", "no-cache");
		  httppost.setRequestBody("doc=03906332403&1f0e3dad99908345f7439f8ffabdffc4=MzY4NDQ3NTkwMXxNb3ppbGxhLzUuMCAoV2luZG93cyBOVCA2LjE7IFdPVzY0OyBydjo1My4wKSBHZWNrby8yMDEwMDEwMSBGaXJlZm94LzUzLjB8aHR0cHM6Ly93d3cuc2l0dWFjYW9jYWRhc3RyYWwuY29tLmJyL3x8dHJ1ZXwxMzY2eDc2OHgyNHwxMzY2eDI3Mw==");
		  try { 
		    httpclient.executeMethod(httppost);
		    if (httppost.getStatusCode() == HttpStatus.SC_OK) {
		    	System.out.println("Unexpected failure: " + httppost.getStatusLine().toString());
		    	System.out.println(httppost.getResponseCharSet());
				//print result
				System.out.println("response: "+ httppost.getResponseBodyAsString(100)); 
		    }else{
		    	System.out.println("Unexpected failure: " + httppost.getStatusLine().toString());
		    }
		  } finally {
		    httppost.releaseConnection();
		  }
	}
	
	public static void main(String[] args) throws Exception {
		CpfPost teste = new CpfPost();
		
		try{
			//new CpfPost().apacheHttpClient();
			loop(teste);
		} catch (Exception e) {
	        System.out.println("Erro (Exception): "+e.getMessage());
	        Thread.sleep(1000);
	        loop(teste);
	    }
		
		System.out.println("----- FIM ------");
		
	}

	private static void loop(CpfPost teste) throws Exception {
		for(int i=0; i<1; i++){
			teste.sendPost("03906332403");
			System.out.println("Enviada Requisição "+i);
			//Thread.sleep(2000);
		}
	}


}
