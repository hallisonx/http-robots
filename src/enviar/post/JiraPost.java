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

import javax.net.ssl.HttpsURLConnection;


public class JiraPost {

	private static final String  PROXY = "";
	private static final String  PORTA = "";
	private static final String  USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0";
	
	public static void main(String[] args) throws Exception {
		JiraPost teste = new JiraPost();
		
		try{
			if(teste.sendPostLogin()==200){
				if(teste.sendPostRegistro()==200){
					System.out.println("Registrado com sucesso");
				}else{
					System.out.println("Falha ao registrar horas");
				}
			}else{
				System.out.println("Falha ao autenticar");
			}
		} catch (Exception e) {
	        System.out.println("Erro (Exception): "+e.getMessage());
	    }
		
		System.out.println("FIM DA EXECUÇÃO");
	}


	private int sendPostLogin() throws Exception {
		int responseCode = 0;
		try {
			
			String url = "https://jira.indra.es/login.jsp";
			URL obj = new URL(url);
			SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
			Proxy proxy = new Proxy(Type.HTTP, addr);
			//HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
			
			HttpsURLConnection con = (HttpsURLConnection)obj.openConnection(proxy);
			
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", "jira.indra.es");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			con.setRequestProperty("Referer", "https://jira.indra.es/login.jsp");
			con.setRequestProperty("Cookie", "atlassian.xsrf.token=BWCG-RW4C-73PY-FAS3|2284aef899b1aa69f9868ce88477b016371ff76a|lout; JSESSIONID=5E6061639EF534CAA86365F6AB909D42");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Upgrade-Insecure-Requests", "1");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", "97");
			
			String urlParameters = "?os_username=hcarvalhof&os_password=11Hh2645780&os_destination=&user_role=&atl_token=&login=Log+In";
			
			// Send post request
			con.setDoOutput(true);
			OutputStream wr = con.getOutputStream();
			wr.write(urlParameters.getBytes());
			wr.flush();
			wr.close();
			
			responseCode = con.getResponseCode();
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
		return responseCode;
	}
	
	private int sendPostRegistro() throws Exception {
		int responseCode = 0;
		try {
			
			String url = "https://timing.indra.es/HORUS/WorklogInsert.do";
			URL obj = new URL(url);
			SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
			Proxy proxy = new Proxy(Type.HTTP, addr);
			HttpsURLConnection con = (HttpsURLConnection)obj.openConnection(proxy);
			
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", "timing.indra.es");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("Referer", "https://timing.indra.es/HORUS/WorklogAlta.do");
			con.setRequestProperty("Content-Length", "458");
			con.setRequestProperty("Cookie", "JSESSIONID=5E6061639EF534CAA86365F6AB909D42; crowd.token_key=57iXTDy09EKGQXCxrHJvKA00");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("issue", "TTJPB-246");
			con.setRequestProperty("hours", "8");
			con.setRequestProperty("dateIn", "18/11/2016");
			con.setRequestProperty("dateEn", "18/11/2016");
			con.setRequestProperty("userName", "De Carvalho Fonseca, Halisson");
			con.setRequestProperty("userKey", "hcarvalhof");
			con.setRequestProperty("clientReqId", "1479391562473");
			con.setRequestProperty("time", "15:53");
			con.setRequestProperty("remainigEstimate", "620h");
			con.setRequestProperty("phase", "6");
			con.setRequestProperty("minutesForValidate", "480");
			con.setRequestProperty("originalEstimate", "4680000");
			con.setRequestProperty("bndRemainE", "true");
			con.setRequestProperty("bndCangeRE", "false");
			con.setRequestProperty("elementToWorklog", "-1");
			con.setRequestProperty("descElementToLogwork", "None");
			con.setRequestProperty("moodSelected", "happy");
			con.setRequestProperty("token", "559AZFWAUFKNT32DZB2CC141V6WZHOHJ");
			con.setReadTimeout(30000);
			
	
			String urlParameters = "issue=TTJPB-246&comment=desenvolvimento&hours=8&dateIn=18%2F11%2F2016&dateEn=18%2F11%2F2016&time=14%3A6&remainigEstimate=620h&phase=6&userName=De+Carvalho+Fonseca%2C+Halisson&userKey=hcarvalhof&clientReqId=1479391562473&minutesForValidate=480&originalEstimate=4680000&changeOE=false&bndRemainE=true&bndCangeRE=false&beforeEstimadoRest=&elementToWorklog=-1&descElementToLogwork=None&moodSelected=happy&token=559AZFWAUFKNT32DZB2CC141V6WZHOHJ&struts.token.name=";
	
			// Send post request
			con.setDoOutput(true);
			Thread.sleep(1000);
			OutputStream wr = con.getOutputStream();
			Thread.sleep(1000);
			//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(urlParameters.getBytes());
			Thread.sleep(1000);
			wr.flush();
			wr.close();
	
			responseCode = con.getResponseCode();
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
		return responseCode;
	}
}
