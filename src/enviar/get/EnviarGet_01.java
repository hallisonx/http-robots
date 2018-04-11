package enviar.get;

import java.io.BufferedReader;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EnviarGet_01 {

	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0";
	
	public static void main(String[] args) throws Exception {
		EnviarGet_01 http = new EnviarGet_01();
		//http.login();
		
		int intervalo = 10;
		for(int i = 0; i < 1; i++ ){
			http.sendGet("https://access.indraweb.net/cgi/tm/?q="+(intervalo+1) );
			intervalo = intervalo + 10;
			Thread.sleep(100);
		}
	}
	
	
	
	private void sendGet(String url) throws Exception {
		SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
		Proxy proxy = new Proxy(Type.HTTP, addr);

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection(proxy);

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		

		/*Document doc = Jsoup.parse(response.toString());
		Elements content = doc.getElementsByClass("ms-srch-result-groups"); 
		for(Element e : content){
			Element email_elem = e.getElementById("WorkEmailValue");
			String email = email_elem.text();
			System.out.println(email+";");
		}
*/
		
		/*Document doc = Jsoup.parse(response.toString());
		Element content = doc.getElementById("cotacao"); 
		Elements links = content.getElementsByTag("input");
		for (Element elemento : links) {
			String linkText = elemento.id();
			System.out.println("[MOEDA] "+linkText);
			
		String linkHref = elemento.attr("value");
			System.out.println("[VALOR] "+linkHref);
		}*/
			
		
	}
	
	
	
	
	
	
	private void login() throws Exception {
		try {
			
			String url = "https://access.indraweb.net/cgi/login";
			URL obj = new URL(url);
			SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
			Proxy proxy = new Proxy(Type.HTTP, addr);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection(proxy);
			//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", "access.indraweb.net");
			con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
            con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            con.setRequestProperty("Referer", "https://access.indraweb.net/vpn/tmindex.html");
            con.setRequestProperty("Cookie", "NSC_TMAA=2146886ed2adb479cc3f5a3f030a8f1f; NSC_TMAS=467e182a59ed6cf240df918b10e09c53; NSC_TEMP=762d9dbf318ac6109ea10bcd53f641be; NSC_TASS=aHR0cHM6Ly93d3cuaW5kcmF3ZWIubmV0L1Byb2Zlc3Npb25hbE5ldHdvcmsvUGFnZXMvUHJvZmVzc2lvbmFsU2VhcmNoZXIuYXNweD9rPXJvZHJpZ3VlcyZzPTEx");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Upgrade-Insecure-Requests", "1");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length", "35");
            con.setRequestProperty("login", "hcarvalhof");
            con.setRequestProperty("passwd", "11Hh2645780");
			
			String urlParameters = "login=hcarvalhof&passwd=11Hh2645780";  //"nid=70357";   
	
			// Send post request
			con.setDoOutput(true);
			OutputStream wr = con.getOutputStream();
			//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
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
	
}
