package utilidades;

import java.util.ArrayList;
import java.util.List;

public class Executar {

	//"MMN","MMO","MMP","MMQ","MMR","MMS","MMT","MMU","MMV","MMX","MMW","MNN","MNO","MNP","MNQ","MNR","MNS","MNT","MNU","MNV","MNX","MNW","MON","MOO","MOP","MOQ","MOR","MOS","MOT","MOU","MOV","MOX","MOW","NPR","NPK","NQR","NQK","OET","OEU","OEV","OEW","OEX","OEZ","OFA","OFB","OFC","OFD","OFE","OFF","OFG","OFH","OFX","OFY","OFZ","OGA","OGB","OGC","OGD","OGE","OGF","OGG","OXO","QFA","QFB","QFC","QFD","QFE","QFF","QFG","QFH","QFI","QFK","QFJ","QFL","QFM","QFN","QFO","QFP","QFQ","QFR","QFS","QFT","QFW","QFV","QFU","QFX","QFY","QFZ","MNM","NQF","NQB"
	private static final String[] letrasComb1 = {"MMN","MMO","MMP","MMQ","MMR","MMS","MMT","MMU","MMV","MMX","MMW","MNN","MNO","MNP","MNQ","MNR","MNS","MNT","MNU","MNV","MNX"};
	private static final String[] letrasComb2 = {"MNM"};
	private static final String[] letrasComb3 = {"QFJ","QFL","QFM","QFN","QFO","QFP","QFQ","QFR","OGA","MOU","OFC","OFD","OFE","OFF","OFG","OFH","OFX","OFY","OFZ"};
	private static final String[] letrasComb4 = {"QFI","QFK","OGB","OGC","OGD","OGE","OGF","OGG","OXO"};
	private static final String[] letrasComb5 = {"QFS","QFT","QFW","QFV","QFU","QFX","QFY","QFZ","NQF"};
	private static final String[] executados = {"MNW","NPR","OET","QFA","MON","MOO","NPK","NQR","OEU","OEV","QFB","QFC","OEW","OEX","OEZ","OFA","OFB","MOP","MOQ","MOR","MOS","MOT","NQK","NQB","MOV","MOX","MOW","QFD","QFE","QFF","QFG","QFH"};
		
	/*public static void main(String[] args) {
		
		List<String[]> lista = new ArrayList<String[]>();
		
		//lista.add(letrasComb1);
		lista.add(letrasComb2);
		//lista.add(letrasComb3);
		//lista.add(letrasComb4);
		//lista.add(letrasComb5);
		
		lista.forEach(l -> {
			new ExtrairDadosDetran(l).start();
		});
		
	}
	*/
	/*public static void main(String[] args) {
		
		List<String[]> lista = new ArrayList<String[]>();
		
		lista.add(estados);
		
		lista.forEach(l -> {
			new ExtrairDadosCRM(l).start();
		});
		
	}	*/
	
}
