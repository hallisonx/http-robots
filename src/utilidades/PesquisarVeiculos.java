package utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import entidades.InfoVeiculo;

public class PesquisarVeiculos {
	
	private static final String[] letrasComb = {"MMN","MMO","MMP","MMQ","MMR","MMS","MMT","MMU","MMV","MMX","MMW","MNN","MNO","MNP","MNQ","MNR","MNS","MNT","MNU","MNV","MNX","MNW","MON","MOO","MOP","MOQ","MOR","MOS","MOT","MOU","MOV","MOX","MOW","NPR","NPK","NQR","NQK","OET","OEU","OEV","OEW","OEX","OEZ","OFA","OFB","OFC","OFD","OFE","OFF","OFG","OFH","OFX","OFY","OFZ","OGA","OGB","OGC","OGD","OGE","OGF","OGG","OXO","QFA","QFB","QFC","QFD","QFE","QFF","QFG","QFH","QFI","QFK","QFJ","QFL","QFM","QFN","QFO","QFP","QFQ","QFR","QFS","QFT","QFW","QFV","QFU","QFX","QFY","QFZ","MNM","NQB","NQF"};
	
	public static void main(String[] args) {
		
		String nome = "WILLY RIBEIRO";
		for(String letras : letrasComb){
			if(nome.length()>15) 
				nome = nome.substring(0, 15);
			pesquisar(letras, nome);
		}
		
	}

	public static void pesquisar(String letras, String proprietario){
		BufferedReader br = null;
		InfoVeiculo veiculo = null;
		try {
		    br = new BufferedReader (new FileReader ("D:\\dados_veiculos\\veiculos_"+letras+".txt"));
		    for (String linha = br.readLine(); linha != null; linha = br.readLine()) {
		        if (linha.contains(proprietario.toUpperCase())){
		        	linha = replaceToJson(linha);
		        	veiculo = transformaDeJsonParaObjeto(linha);
		        	System.out.println("(LETRA PESQUISADA: "+letras +") "+ veiculo.getPlaca() +" - "+veiculo.getProprietario() + " - "+veiculo.getMarcaModelo() + " - "+veiculo.getMunicipio());
		        }
		    }
		} catch (IOException ex) {
			System.out.println("Arquivo não encontrado: "+ex.toString());
		} finally {
		    try { if (br != null) br.close(); } catch (IOException ex) {}
		}
	}

	private static String replaceToJson(String linha) {
		linha = linha.replace("InfoVeiculo [",   "{\"");
		linha = linha.replace("=",    "\":\"");
		linha = linha.replace(", ",    "\",\"");
		linha = linha.replace("]",    "\"}");
		return linha;
	}
	
	

	private static InfoVeiculo transformaDeJsonParaObjeto(String jsonString) {
	    Gson gson = new Gson();
	    InfoVeiculo info = gson.fromJson(jsonString, InfoVeiculo.class);
	    return info;
	}

	
}
