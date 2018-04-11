package utilidades;

import java.util.ArrayList;
import java.util.List;

import entidades.InfoVeiculo;
import enviar.get.EnviarGet_Detran;

public class ExtrairDadosDetran extends Thread{
	
	private String[] placas;
	
	public ExtrairDadosDetran(String[] list){
		placas = list;
	}
	
		
	public void executar(String[] letrasComb) throws Exception {
		List<InfoVeiculo> listaInfoVeiculo = new ArrayList<InfoVeiculo>();
		EnviarGet_Detran http = new EnviarGet_Detran();

		for(String letras : letrasComb){
			int quant_registros = EnviarGet_Detran.executar(listaInfoVeiculo, http, letras);
			if(quant_registros>0){
				EnviarGet_Detran.gravarArquivo(listaInfoVeiculo, letras);
			}
		}
		
	}
	
	@Override
	public void run() {
		try {
			executar(placas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
