package it.prova.cartellaesattoriale.service;

import java.util.List;

import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.repository.contribuente.ContribuenteRepository;

public class ContribuenteServiceImpl implements ContribuenteService {
	
	private ContribuenteRepository repository;

	@Override
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>)repository.findAll();
	}
	
	@Override
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Contribuente> listAllElementsEager() {
		return (List<Contribuente>)repository.findAllEager();
	}

	@Override
	public Contribuente caricaSingoloElementoConCartelleEsattoriali(Long id) {
		return repository.findByIdEager(id);
	}

	@Override
	public Contribuente aggiorna(Contribuente contribuenteInstance) {
		return repository.save(contribuenteInstance);
	}

	@Override
	public Contribuente inserisciNuovo(Contribuente contribuenteInstance) {
		return repository.save(contribuenteInstance);
	}

	@Override
	public void rimuovi(Contribuente contribuenteInstance) {
		repository.delete(contribuenteInstance);
	}

	@Override
	public List<Contribuente> findByExample(Contribuente example) {
		return repository.findByExample(example);
	}

	@Override
	public List<Contribuente> cercaByCognomeENomeILike(String term) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}

	@Override
	public Contribuente findByNomeAndCognome(String nome, String cognome) {
		return repository.findByNomeAndCognome(nome, cognome);
	}

}
