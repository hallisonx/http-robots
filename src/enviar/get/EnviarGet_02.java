package enviar.get;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.Proxy.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EnviarGet_02 {

	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0";
	
	public static void main(String[] args) throws Exception {
		EnviarGet_02 http = new EnviarGet_02();
		
		for(int i = 0; i < 1000; i++){
			System.out.println("Teste "+i+" - Send Http GET request");
			//http.sendGet("http://display.vitrines.in/clk?znid=99966719&dvrtsrd=1000066&prdd=132-523d0ff2a41fd4220ad5ef2a483e6e94&cid=1059629&r=http%253A%252F%252Ftracker.buscape.com.br%252Ftr%252Frd%253Fo%253DZ2UfEjwmZQ8KbDQ4aw4xDwQbZiAZDyltLThoKTlzc2VsZ2tsZ25tb2VvaG1lbmU2KiouLWRxcSosPz01OyxwLjswLTs8NzlwPTEzcDwscS4_OTthKywyYzYqKi57bT97bDh7bDgpKSlwMz85PyQ3MDsyKzckP3A9MTNwPCx7bDg9PzM7LD9zOjc5Nyo_MnM1MTo_NXMuNyYuLDFzPyRsa29zb2hwbzMucy07MzcuLDE4Ny0tNzEwPzJzKDctMSxzbXMkMTEzczEuKjc9MXNsayZ7bDgue2w4bG9ob2pubHtsOD04e2w4PTguLntsOC4_LD07Nywxe2w4Zm9tbntsOHttOCsqMwEtMSssPTt7bTo8Ky09Py47e2xoKyozATM7OjcrM3ttOj0xMy4_LD86MSw7LXtsaCsqMwE9MTAqOzAqe206PTh7bGgrKjMBPT8zLj83OTB7bTo8Ky0qe2xoKjUNMSssPTt7bTo8Ky09Py47e2xoKjURODg7LHttOmdtPztrbmk4c2ptaWtzamdsZ3M8PGhnc2pvaGk6bz9ubmpuantsaDoSMTl7bTpsbm9obmdtbm5tamlramUwMSw7OGVvZRwMZWVlbmVuZW5lbmVlb2ppa2xqZmloaW1ub2Vlb2lpcGlvcG9nbHBsbGtlbmUwKzIyZWptZ2VpZ2VnbWVra2Zpb2xlbG9rbGluZmtsZW5waGZlbnBuZWxlZW5lbmVu&undefined");
			//http.sendGet("http://arduinandojp.blogspot.com.br/2014/01/iniciando-projeto-de-automacao.html");
			//http.sendGet("http://www.estadao.com.br/");
			//http.sendGet("http://dolarhoje.com/");
			http.sendGet("http://pb.olx.com.br/paraiba/celulares/capa-case-celular-j5-motomo-275241834?xtor=EPR-9-[A]" );
			//Thread.sleep(100);
		}
	}
	
	
	
	private void sendGet(String url) throws Exception {
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
		//System.out.println(response.toString());
		
		

		

	}
	
	
}
