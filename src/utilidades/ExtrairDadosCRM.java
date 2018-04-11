package utilidades;

import java.util.ArrayList;
import java.util.List;

import entidades.InfoMedico;
import entidades.InfoVeiculo;
import enviar.get.EnviarGet_CRM;
import enviar.get.EnviarGet_Detran;

public class ExtrairDadosCRM extends Thread{
	
	private String[] estados;
	
	public ExtrairDadosCRM(String[] list){
		estados = list;
	}
	
		
	public void executar(String[] letrasUF) throws Exception {
		List<InfoMedico> listaInfoMedico = new ArrayList<InfoMedico>();
		EnviarGet_CRM http = new EnviarGet_CRM();

		for(String letras : letrasUF){
			int quant_registros = EnviarGet_CRM.executar(listaInfoMedico, http, letras);
			if(quant_registros>0){
				EnviarGet_CRM.gravarArquivo(listaInfoMedico, letras);
			}
		}
		
	}
	
	@Override
	public void run() {
		try {
			executar(estados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
