package enviar.get;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entidades.Titulo;

public class EnviarGet_TesouroDireto {

	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0";
	
	public static void main(String[] args) throws Exception {
		EnviarGet_TesouroDireto http = new EnviarGet_TesouroDireto();
		//System.out.println("Teste "+i+" - Send Http GET request");
		http.sendGet("http://www.tesouro.fazenda.gov.br/tesouro-direto-calculadora");
		Thread.sleep(250);
	}
	
	
	
	private void sendGet(String url) throws Exception {
		SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
		Proxy proxy = new Proxy(Type.HTTP, addr);


		if(url==null){
			url = "http://www.tesouro.fazenda.gov.br/tesouro-direto-calculadora";
		}

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		

		Date hoje = new Date();
		List<Titulo> titulos = new ArrayList<Titulo>();
		Document doc = Jsoup.parse(response.toString());
		Elements links = doc.getElementsByClass("camposTesouroDireto");
		for (Element elemento : links) {
			Elements tds = elemento.getElementsByTag("td");
			int i = 1;
			Titulo t = new Titulo();
			for(Element e : tds){
				String texto = e.text();
				if(i==1) t.setNome(texto);
				if(i==2) t.setVencimento(texto);
				if(i==3) t.setTaxaCompra(texto);
				if(i==4) t.setTaxaVenda(texto);
				if(i==5) t.setPrecoUnitCompraDia(texto);
				if(i==6) t.setPrecoUnitVendaDia(texto);
				i++;
			}
			System.out.println(t);
			t.setData(Calendar.getInstance().getTime());
			titulos.add(t);
		}
		
		Titulo.gravarArquivo(titulos, hoje);
			
		

		

	}
	
	
}
