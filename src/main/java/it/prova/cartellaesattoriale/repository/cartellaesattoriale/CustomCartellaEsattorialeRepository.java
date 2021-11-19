package it.prova.cartellaesattoriale.repository.cartellaesattoriale;

import java.util.List;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;

public interface CustomCartellaEsattorialeRepository {
	
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);

}
