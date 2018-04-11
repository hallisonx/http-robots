package enviar.get;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entidades.InfoMedico;

public class EnviarGet_CRM{

	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0";
	
	public static int executar(List<InfoMedico> listaInfoMedico, EnviarGet_CRM http, String estado) throws Exception, InterruptedException {
		System.out.println("Executando para estados "+estado);
		for(int i = 1; i < 78; i++){
			
			InfoMedico infoMedico = http.sendGet("http://www.consultacrm.com.br/?q_tipo=crm&uf="+estado+"&q="+i );
			if(infoMedico.getNome()!=null && infoMedico.getNome().length()>1){
				listaInfoMedico.add(infoMedico);
			}
			Thread.sleep(3000);
		}
		return listaInfoMedico.size();
	}


	public static void gravarArquivo(List<InfoMedico> listaInfoMedico, String estados) throws IOException {
		FileWriter writer = new FileWriter("D:\\dados_medicos\\medicos_"+estados+".txt");
		for(InfoMedico reg : listaInfoMedico){
			writer.write(reg.toString());
		}
		writer.close();
		System.out.println("Finalizado para a combinacao "+estados);
	}


	
	private InfoMedico sendGet(String url) throws Exception {
		SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
		Proxy proxy = new Proxy(Type.HTTP, addr);


		if(url==null){
			url = "http://www.indracompany.com/universidad/voting/ajax?nid=69987";
		}

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
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
		

		Document doc = Jsoup.parse(response.toString());
		Elements contents = doc.getElementsByTag("span"); 
		int idx = 0;
		boolean registroValido = false;
		InfoMedico infoMedico = new InfoMedico();
		
		for (Element elemento : contents) {
			if(idx>0){
				String texto = elemento.text();
				
				if(idx>=1 && !"".equals(texto)){
					registroValido = true;
				}
				
				if(registroValido){
					switch(idx){
						case 16:
							infoMedico.setCrm(texto);
							break;
						case 17:
							infoMedico.setNome(texto);
							break;
						case 18:
							infoMedico.setEspecialidade(texto);
							break;
						case 19:
							infoMedico.setLocalidade(texto);
							break;
						default:
					}
				}
			}
			idx++;
		}
		System.out.println(infoMedico!=null&&infoMedico.getNome()!=null?infoMedico:"");	
		return infoMedico;
		

	}
	
	
}
