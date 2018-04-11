package htmlunit;

import java.net.URL;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

import entidades.InfoPessoa;

public class VerificarCPF {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		
		String[] docs = {"06255670481", "07285076414", "05006743450", "06450256400", "06051607404", "01295990423"};
		
		for(String s : docs){
			InfoPessoa pessoa = new InfoPessoa(s, null, null, null);
			System.out.println(new VerificarCPF().getInfoPessoa(pessoa, "proxy.tjpb.jus.br", 3128));
		}
		
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Tempo: "+elapsed+"ms");

	}
	
	/**Return a Json String. Params ex (00000000000, proxy.mydomain.com, 9999)*/
	public String getInfoPessoa(InfoPessoa pessoa, String proxy, int porta) throws Exception {
        return getInfoPessoa(pessoa.getCpf(), proxy, porta).toString();
	}
	
	/**Return a Json String. Params ex (00000000000)*/
	public String getInfoPessoa(InfoPessoa pessoa) throws Exception {
        return getInfoPessoa(pessoa.getCpf(), null, 0).toString();
	}

	/**Return a InfoPessoa. Params ex (00000000000)*/
	public InfoPessoa getInfoPessoa(String cpf) throws Exception {
		return getInfoPessoa(cpf, null, 0);
	}
	
	/**Return a InfoPessoa. Params ex (00000000000, proxy.mydomain.com, 9999)*/
	public InfoPessoa getInfoPessoa(String cpf, String proxy, int porta) throws Exception {
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	    java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
	    
	    WebClient webClient = null;
	    if((proxy!=null && proxy.length()>0) && porta>0){
	    	webClient = new WebClient(BrowserVersion.FIREFOX_52, proxy, porta);
	    }else{
	    	webClient = new WebClient(BrowserVersion.FIREFOX_52);
	    }
	    webClient.getOptions().setThrowExceptionOnScriptError(false);
	    webClient.getOptions().setJavaScriptEnabled(false);
        
        URL url = new URL("https://www.situacaocadastral.com.br");
        WebRequest requestSettings = new WebRequest(url, HttpMethod.POST);
        requestSettings.setAdditionalHeader("Host", "www.situacaocadastral.com.br");
        requestSettings.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
        requestSettings.setAdditionalHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        requestSettings.setAdditionalHeader("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.5,en;q=0.3");
        requestSettings.setAdditionalHeader("Accept-Encoding", "gzip, deflate, br");
        requestSettings.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded");
        requestSettings.setAdditionalHeader("Referer", "https://www.situacaocadastral.com.br/");
        requestSettings.setAdditionalHeader("Cookie", "__cfduid=dcccf446d70cf9f850a3ea9dca8c08ad31489756794; __utma=180780453.2002100046.1489756800.1493758480.1493834106.9; __utmz=180780453.1493397885.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not provided); _referrer_og=https://www.google.com.br/; _jsuid=2730345620; USID=12f629f9249a5b692095f866a73e491a; __utmc=180780453; __utmb=180780453.3.10.1493834106; __utmt=1; _first_pageview=1; heatmaps_g2g_100536568=yes; _eventqueue={\"heatmap\":[],\"events\":[]};");
        requestSettings.setAdditionalHeader("Connection", "keep-alive");
        requestSettings.setAdditionalHeader("Upgrade-Insecure-Requests", "1");
        requestSettings.setAdditionalHeader("Cache-Control", "max-age=0, no-cache");
        requestSettings.setAdditionalHeader("Pragma", "no-cache");
        requestSettings.setRequestParameters(new ArrayList());
        requestSettings.getRequestParameters().add(new NameValuePair("doc", cpf));
        //requestSettings.setRequestBody("doc="+cpf+"&6f4922f45568161a8cdf4ad2299f6d23=MzY4NDQ3NTkwMXxNb3ppbGxhLzUuMCAoV2luZG93cyBOVCA2LjE7IFdPVzY0OyBydjo1My4wKSBHZWNrby8yMDEwMDEwMSBGaXJlZm94LzUzLjB8aHR0cHM6Ly93d3cuc2l0dWFjYW9jYWRhc3RyYWwuY29tLmJyL3xodHRwczovL3d3dy5zaXR1YWNhb2NhZGFzdHJhbC5jb20uYnIvfHRydWV8MTM2Nng3Njh4MjR8MTM2Nng2OTc=");
        HtmlPage redirectPage = webClient.getPage(requestSettings);
        
        //System.out.println(redirectPage.getWebResponse().getContentAsString());
        InfoPessoa pessoa = null;
        if(redirectPage.getWebResponse().getStatusCode()==200){
        	DomElement nome = redirectPage.getFirstByXPath("//span[@class='dados']");
        	DomElement situacao = redirectPage.getFirstByXPath("//span[@class='dados situacao']");
        	DomElement obs = redirectPage.getFirstByXPath("//span[@class='dados texto']");
        	if(nome!=null){
        		pessoa = new InfoPessoa(cpf, nome.asText(), situacao.asText(), obs.asText());
        	}
        	return pessoa;
        }
        return new InfoPessoa(cpf, null, null, null);
        
	}

}
