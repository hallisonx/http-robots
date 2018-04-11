package entidades;

public class InfoMedico {
	
	private String crm;
	private String nome;
	private String especialidade;
	private String localidade;
	
	
	public InfoMedico(){
	}


	public InfoMedico(String crm, String nome, String especialidade, String localidade) {
		this.crm = crm;
		this.nome = nome;
		this.especialidade = especialidade;
		this.localidade = localidade;
	}


	public String getCrm() {
		return crm;
	}


	public void setCrm(String crm) {
		this.crm = crm;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEspecialidade() {
		return especialidade;
	}


	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}


	public String getLocalidade() {
		return localidade;
	}


	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	@Override
	public String toString() {
		return "InfoMedico [crm=" + crm + ", nome=" + nome + ", especialidade="
				+ especialidade + ", localidade=" + localidade + "]";
	}

	
	
	
	
}
