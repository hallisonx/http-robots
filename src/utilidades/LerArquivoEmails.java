package utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class LerArquivoEmails {
	
	public static void main(String[] args) {
		
			pesquisar();
		
	}
	

	public static void pesquisar(){
		Set<String> lista = new HashSet<String>();
		BufferedReader br = null;
		try {
		    br = new BufferedReader (new FileReader ("D:\\arquivo1.txt"));
		    for (String linha = br.readLine(); linha != null; linha = br.readLine()) {
		        if (linha.contains("@indracompany.com") || linha.contains("@indra.es")){
		        	lista.add(linha+";");
		        }
		    }
		    System.out.println(lista.size());
		    for(String s : lista){
		    	System.out.println(s);
		    }
		} catch (IOException ex) {
			System.out.println("Arquivo não encontrado: "+ex.toString());
		} finally {
		    try { if (br != null) br.close(); } catch (IOException ex) {}
		}
	}

}
