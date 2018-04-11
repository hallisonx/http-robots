package enviar.post;

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





public class Teste02 {

	private void sendPost() throws Exception {

		try {
			
			String url = "https://www.situacaocadastral.com.br";
			URL obj = new URL(url);
			SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
			Proxy proxy = new Proxy(Type.HTTP, addr);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
			//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", "253");
			con.setRequestProperty("Referer", "https://www.situacaocadastral.com.br/");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Upgrade-Insecure-Requests", "1");
			
	
			String urlParameters = "doc=03906332403";  //"nid=70357";   
	
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
	
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println("response: "+ response.toString()); 
		
		} catch (UnknownHostException e) {
	        System.out.println("Erro (UnknownHostException): "+e.getMessage());
	        Thread.sleep(1000);
	    }

	}
	
	public static void main(String[] args) throws Exception {
		Teste02 teste = new Teste02();
		
		for(int i=0; i<2; i++){
			teste.sendPost();
			System.out.println("Enviada Requisi��o "+i);
			/*int time = 1000+i;
			System.out.println("Time: "+time);
			Thread.sleep(time);*/
		}
		
		System.out.println("----- FIM ------ CPF ");
		
	}


}
