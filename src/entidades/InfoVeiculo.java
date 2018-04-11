package entidades;

public class InfoVeiculo {
	
	private String placa;
	private String proprietario;
	private String ultimoLicenciamento;
	private String combustivel;
	private String marcaModelo;
	private String tipo;
	private String anoFabricacao;
	private String anoModelo;
	private String categoria;
	private String cor;
	private String vencimentoLicenciamento;
	private String observacao;
	private String restricao;
	private String financeira;
	private String municipio;
	private String situacao;
	private String dataConsulta;
	
	public InfoVeiculo(){
	}

	public InfoVeiculo(String placa, String proprietario,
			String ultimoLicenciamento, String combustivel, String marcaModelo,
			String tipo, String anoFabricacao, String anoModelo,
			String categoria, String cor, String vencimentoLicenciamento,
			String observacao, String restricao, String financeira,
			String municipio, String situacao, String dataConsulta) {
		this.placa = placa;
		this.proprietario = proprietario;
		this.ultimoLicenciamento = ultimoLicenciamento;
		this.combustivel = combustivel;
		this.marcaModelo = marcaModelo;
		this.tipo = tipo;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.categoria = categoria;
		this.cor = cor;
		this.vencimentoLicenciamento = vencimentoLicenciamento;
		this.observacao = observacao;
		this.restricao = restricao;
		this.financeira = financeira;
		this.municipio = municipio;
		this.situacao = situacao;
		this.dataConsulta = dataConsulta;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getUltimoLicenciamento() {
		return ultimoLicenciamento;
	}

	public void setUltimoLicenciamento(String ultimoLicenciamento) {
		this.ultimoLicenciamento = ultimoLicenciamento;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getMarcaModelo() {
		return marcaModelo;
	}

	public void setMarcaModelo(String marcaModelo) {
		this.marcaModelo = marcaModelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getVencimentoLicenciamento() {
		return vencimentoLicenciamento;
	}

	public void setVencimentoLicenciamento(String vencimentoLicenciamento) {
		this.vencimentoLicenciamento = vencimentoLicenciamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getRestricao() {
		return restricao;
	}

	public void setRestricao(String restricao) {
		this.restricao = restricao;
	}

	public String getFinanceira() {
		return financeira;
	}

	public void setFinanceira(String financeira) {
		this.financeira = financeira;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@Override
	public String toString() {
		return "InfoVeiculo [placa=" + placa + ", proprietario=" + proprietario
				+ ", ultimoLicenciamento=" + ultimoLicenciamento
				+ ", combustivel=" + combustivel + ", marcaModelo="
				+ marcaModelo + ", tipo=" + tipo + ", anoFabricacao="
				+ anoFabricacao + ", anoModelo=" + anoModelo + ", categoria="
				+ categoria + ", cor=" + cor + ", vencimentoLicenciamento="
				+ vencimentoLicenciamento + ", observacao=" + observacao
				+ ", restricao=" + restricao + ", financeira=" + financeira
				+ ", municipio=" + municipio + ", situacao=" + situacao
				+ ", dataConsulta=" + dataConsulta + "] \n";
	}
	
	
	
}
