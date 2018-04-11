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


public class OAB_Post {

	private void sendPost() throws Exception {

		try {
			
			String url = "http://cna.oab.org.br/Home/Search";
			URL obj = new URL(url);
			SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
			Proxy proxy = new Proxy(Type.HTTP, addr);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
			//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/49.0");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			con.setRequestProperty("Content-Length", "1006");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("Referer", "http://cna.oab.org.br/");
			
	
			String urlParameters = "IsMobile=false&NomeAdvo=helton&Insc=&Uf=PB&TipoInsc=&g-recaptcha-response=03AHJ_VusfUhvFRzKjosqu_v2Fq-liNY-Dr06hZKZeTiXX66fnBIsjBXFM_Wt8fNczt19NrDZTUOAb6E6TbVOlNqTgmu0hKO23acFN5zyecLb40W2rB0LjeCSrdmv43sCPMQE-mYt2ObFsK2vG05g1Fq4h7cpp7a6kzZtrnsAApNBdvp7jsDY3Mq50-nAG82VTNIesDjNqFoWfUw71B14C4gRnFWCm_LpX-7wHiCjYGE4XRRkseZoTB9mD5Din5inH-dEHAHGsan9bw3T6REdzgWmqIY9cOdCqXth5BMz-stKgYk4RxhObjSASlbQVY1ReCalinwodrEgvd2QxQEyuM9dvzJrSAGV7rDrpVmgHMjxk-hKT32mv-O0aIJpmiwFsvg__0D7paaBdaeVmhATRqeRE3_KKGI9YHeKknzpd7Z0bdbfLEQohcwI_fq2_NmiZOUZpova7jk0nxogkQ7KlVl7bgTTbhiu9BSzJLUXuewlMxw9Ulq_Xh8cfn_C2eF7pdqNYYAyWzkkm1w8cX2LLScqsBBaxgtOl2qDIT2C0YG-yNocl_PJFt05TFWZRqmHSzr0k72puMxoH6A9NXG0ylTbsUipMunwBP2V_Qyaz2JgD0l-FspNJNq6wUKGmVPl19lZVuj3TnURHMNIw821hSHqIwgk7fSagH7NllG8KyBnxuPO48AtwCI532qWxdlVD7TWDHboO4fuOJcEsWoRCfGu6CrK3ObRqvUDna56gE6EtRuUDW39cVemNVDCZGW7VBp2pVylXTBeGOZh22v31LX6ZCvPevdEFXusEeUmOU2J9rfPK_02O4Z-mUAqf8bcXBVTqLPDRO0kM5ym34HG_YgEW8y6GgSZotGC7I9-xf6fzMvqeGGod5gZaLlLx-rgygWJh1zevupw3oTEqfNvfQTf5r-yzHrAZIg";
	
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
	
	public static void main(String[] args) throws Exception {
		OAB_Post teste = new OAB_Post();
		
		try{
			loop(teste);
		} catch (Exception e) {
	        System.out.println("Erro (Exception): "+e.getMessage());
	        Thread.sleep(1000);
	        loop(teste);
	    }
		
		System.out.println("----- FIM ------");
		
	}

	private static void loop(OAB_Post teste) throws Exception {
		for(int i=0; i<2; i++){
			teste.sendPost();
			System.out.println("Enviada Requisição "+i);
			//Thread.sleep(2000);
		}
	}


}
