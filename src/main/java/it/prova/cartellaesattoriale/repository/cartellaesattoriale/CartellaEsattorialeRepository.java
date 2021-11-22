package it.prova.cartellaesattoriale.repository.cartellaesattoriale;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends CrudRepository<CartellaEsattoriale, Long>, CustomCartellaEsattorialeRepository {
	
	@Query("from CartellaEsattoriale ce join fetch ce.contribuente where ce.id = ?1")
	CartellaEsattoriale findSingleCartellaEsattorialeEager(Long id);
	
	List<CartellaEsattoriale> findByDescrizioneAndImporto(String descrizione, Integer importo);
	
	@Query("select ce from CartellaEsattoriale ce join fetch ce.contribuente")
	List<CartellaEsattoriale> findAllCartellaEsattorialeEager();
	
	@Query("select sum(ce.importo) from CartellaEsattoriale ce")
	Integer sumOfImporto();
	
	@Query("select sum(ce.importo) from CartellaEsattoriale ce where ce.stato = 'CONCLUSA'")
	Integer sumConclusa();
	
	@Query("select sum(ce.importo) from CartellaEsattoriale ce where ce.stato = 'IN_CONTENZIOSO'")
	Integer sumContenzioso();

}
