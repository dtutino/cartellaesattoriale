package it.prova.cartellaesattoriale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.cartellaesattoriale.service.CartellaEsattorialeService;
import it.prova.cartellaesattoriale.service.ContribuenteService;

@SpringBootApplication
public class CartellaesattorialeApplication {
	
	@Autowired
	private ContribuenteService contribuenteService;
	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	public static void main(String[] args) {
		SpringApplication.run(CartellaesattorialeApplication.class, args);
	}
	
//	@Override
//	public void run(String... args) throws Exception {
//
//		String mario = "Mario";
//		String rossi = "Rossi";
//		Contribuente contribuenteRossi = contribuenteService.findByNomeAndCognome(mario, rossi);
//
//		if (contribuenteRossi == null) {
//			contribuenteRossi = new Contribuente(mario, rossi,
//					new SimpleDateFormat("dd/MM/yyyy").parse("18/12/1946"), "mrskdosd49sjnd", "Via Mola");
//			contribuenteService.inserisciNuovo(contribuenteRossi);
//		}
//
//		CartellaEsattoriale cartellaMRossi1 = new CartellaEsattoriale("Mutuo non pagato", 120000, Stato.IN_CONTENZIOSO, contribuenteRossi);
//		if (cartellaEsattorialeService.findByDescrizioneAndImporto(cartellaMRossi1.getDescrizione(), cartellaMRossi1.getImporto()).isEmpty())
//			cartellaEsattorialeService.inserisciNuovo(cartellaMRossi1);
//
//		String giovanni = "Giovanni";
//		String verdi = "Verdi";
//		Contribuente contribuenteVerdi = contribuenteService.findByNomeAndCognome(giovanni, verdi);
//
//		if (contribuenteVerdi == null) {
//			contribuenteVerdi = new Contribuente(giovanni, verdi,
//					new SimpleDateFormat("dd/MM/yyyy").parse("22/08/1988"), "gvlkjd839nskdj", "Via Ischia");
//			contribuenteService.inserisciNuovo(contribuenteVerdi);
//		}
//
//		CartellaEsattoriale cartellaGVerdi1 = new CartellaEsattoriale("Multa arretrata", 800, Stato.CREATA, contribuenteVerdi);
//		if (cartellaEsattorialeService.findByDescrizioneAndImporto(cartellaGVerdi1.getDescrizione(), cartellaGVerdi1.getImporto()).isEmpty())
//			cartellaEsattorialeService.inserisciNuovo(cartellaGVerdi1);
//
//	}

}
