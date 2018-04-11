package entidades;

import java.text.Normalizer;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class InfoPessoa {
	
	private String cpf;
	private String nome;
	private String situacao;
	private String observacao;
	
	public InfoPessoa(String cpf, String nome, String situacao,
			String observacao) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.situacao = situacao;
		this.observacao = observacao;
	}
	
	public InfoPessoa() {
		super();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao!=null?situacao.replaceAll("Situação: ", ""):"";
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return normalizarTexto("{\"cpf\":\"" + formataCpf(cpf) + "\", \"nome\":\"" + nome
				+ "\", \"situacao\":\"" + this.getSituacao() + "\", \"observacao\":\""
				+ observacao + "\"}");
	}
	
	/** Remove os acentos e caracteres especiais do string*/
	public static String normalizarTexto(String str) {
		if(str!=null){
			str = str.toUpperCase();
			str = str.replace("º", "o").replace("ª", "a").replace("* ", "");
			return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		}
		return "";
	}
	
	private String formataCpf(String cpf) {
		if(cpf!=null && cpf.length()==11){
			return cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9,11);
		}
		return cpf;  
   }


	
	

}
