package entidades;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Titulo {
	
	private String nome;
	private String vencimento;
	private String taxaCompra;
	private String taxaVenda;
	private String precoUnitCompraDia;
	private String precoUnitVendaDia;
	private Date data;
	
	public Titulo(){}

	public Titulo(String nome, String vencimento, String taxaCompra, String taxaVenda, String precoUnitCompraDia, String precoUnitVendaDia, Date data) {
		this.nome = nome;
		this.vencimento = vencimento;
		this.taxaCompra = taxaCompra;
		this.taxaVenda = taxaVenda;
		this.precoUnitCompraDia = precoUnitCompraDia;
		this.precoUnitVendaDia = precoUnitVendaDia;
		this.data = data;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getTaxaCompra() {
		return taxaCompra;
	}

	public void setTaxaCompra(String taxaCompra) {
		this.taxaCompra = taxaCompra;
	}

	public String getTaxaVenda() {
		return taxaVenda;
	}

	public void setTaxaVenda(String taxaVenda) {
		this.taxaVenda = taxaVenda;
	}

	public String getPrecoUnitCompraDia() {
		return precoUnitCompraDia;
	}

	public void setPrecoUnitCompraDia(String precoUnitCompraDia) {
		this.precoUnitCompraDia = precoUnitCompraDia;
	}

	public String getPrecoUnitVendaDia() {
		return precoUnitVendaDia;
	}

	public void setPrecoUnitVendaDia(String precoUnitVendaDia) {
		this.precoUnitVendaDia = precoUnitVendaDia;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Titulo [nome=" + nome + ", vencimento=" + vencimento
				+ ", taxaCompra=" + taxaCompra + ", taxaVenda=" + taxaVenda
				+ ", precoUnitCompraDia=" + precoUnitCompraDia
				+ ", precoUnitVendaDia=" + precoUnitVendaDia
				+ ", data=" + converteDateToString(data) + "]";
	}
	
	public Date converteStringToDate(String dataSt) {
		Date date = new Date();
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (Date)formatter.parse(dataSt);
		} catch (ParseException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return date;
	}
	
	public static String converteDateToString(Date dataDt) {
		if (dataDt==null) dataDt = new Date();
		String data = "";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		data = formatter.format(dataDt);
		return data;
	}
	
	public static void gravarArquivo(List<Titulo> titulos, Date data) throws IOException {
		
		FileWriter writer = new FileWriter("D:\\tesouro_direto\\titulos_"+converteDateToString(data)+".txt");
		for(Titulo reg : titulos){
			writer.write(reg.toString()+"\n");
		}
		writer.close();
		System.out.println("Finalizado para "+data);
	}
	

}
