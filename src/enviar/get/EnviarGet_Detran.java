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
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entidades.InfoVeiculo;

public class EnviarGet_Detran{

	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0";
	
	public static void main(String[] args) {
		InfoVeiculo infoVeiculo;
		try {
			infoVeiculo = new EnviarGet_Detran().sendGet("http://wsdetran.pb.gov.br/DT_DUT_Cliente/ConsultaDUT?placaMask=OFF-6183&display=web&placa=OFF6183&display=web&st=" );
			if(infoVeiculo.getPlaca()!=null && infoVeiculo.getPlaca().length()==7){
				System.out.println(infoVeiculo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int executar(List<InfoVeiculo> listaInfoVeiculo, EnviarGet_Detran http, String letras) throws Exception, InterruptedException {
		String numeros = "";
		System.out.println("Executando para letras "+letras);
		for(int i = 0; i < 9999; i++){
			
			numeros = i+"";
			if(numeros.length()==1){
				numeros = "000"+i;
			}else if(numeros.length()==2){
				numeros = "00"+i;
			}else if(numeros.length()==3){
				numeros = "0"+i;
			}else numeros = ""+i;
			
			InfoVeiculo infoVeiculo = http.sendGet("http://wsdetran.pb.gov.br/DT_DUT_Cliente/ConsultaDUT?placaMask="+letras+"-"+numeros+"&display=web&placa="+letras+numeros+"&display=web&st=" );
			if(infoVeiculo.getPlaca()!=null && infoVeiculo.getPlaca().length()==7){
				listaInfoVeiculo.add(infoVeiculo);
			}
			Thread.sleep(100);
		}
		return listaInfoVeiculo.size();
	}


	public static void gravarArquivo(List<InfoVeiculo> listaInfoVeiculo, String letras) throws IOException {
		FileWriter writer = new FileWriter("D:\\dados_veiculos\\veiculos_"+letras+".txt");
		for(InfoVeiculo reg : listaInfoVeiculo){
			writer.write(reg.toString());
		}
		writer.close();
		System.out.println("Finalizado para a combinacao "+letras);
	}


	
	public InfoVeiculo sendGet(String url) throws Exception {
		SocketAddress addr = new InetSocketAddress("proxy.tjpb.jus.br", 3128);
		Proxy proxy = new Proxy(Type.HTTP, addr);

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
		//System.out.println(response.toString());
		

		Document doc = Jsoup.parse(response.toString());
		Elements contents = doc.getElementsByTag("b"); 
		int idx = 0;
		boolean registroValido = false;
		InfoVeiculo infoVeiculo = new InfoVeiculo();
		
		for (Element elemento : contents) {
			if(idx>0){
				String texto = elemento.text();
				
				if(idx==1 && !"0".equals(texto)){
					registroValido = true;
				}
				
				if(registroValido){
					switch(idx){
						case 16:
							infoVeiculo.setUltimoLicenciamento(texto);
							break;
						case 17:
							infoVeiculo.setProprietario(texto);
							break;
						case 18:
							infoVeiculo.setPlaca(texto);
							break;
						case 19:
							infoVeiculo.setCombustivel(texto);
							break;
						case 20:
							infoVeiculo.setMarcaModelo(texto);
							break;
						case 21:
							infoVeiculo.setTipo(texto);
							break;
						case 22:
							infoVeiculo.setAnoFabricacao(texto);
							break;
						case 23:
							infoVeiculo.setAnoModelo(texto);
							break;
						case 24:
							infoVeiculo.setCategoria(texto);
							break;
						case 25:
							infoVeiculo.setCor(texto);
							break;
						case 26:
							infoVeiculo.setVencimentoLicenciamento(texto);
							break;
						case 27:
							infoVeiculo.setObservacao(texto);
							break;
						case 28:
							infoVeiculo.setRestricao(texto);
							break;
						case 29:
							infoVeiculo.setFinanceira(texto);
							break;
						case 30:
							infoVeiculo.setMunicipio(texto);
							break;
						case 31:
							infoVeiculo.setSituacao(texto);
							break;
						case 32:
							infoVeiculo.setDataConsulta(texto);
							break;
						default:
					}
				}
			}
			idx++;
		}
		System.out.println(infoVeiculo!=null&&infoVeiculo.getPlaca()!=null?infoVeiculo:"");	
		return infoVeiculo;
		

	}
	
	
}
