package it.prova.cartellaesattoriale.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.model.Contribuente;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContribuenteDTO {
	
private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotNull(message = "{dataNascita.notnull}")
	private Date dataNascita;
	
	@NotNull(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	
	@NotNull(message = "{indirizzo.notblank}")
	private String indirizzo;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private Boolean daAttenzionare; 
	
	@JsonIgnoreProperties(value = { "contribuente" })
	private Set<CartellaEsattorialeDTO> cartelleEsattoriali = new HashSet<CartellaEsattorialeDTO>(0);

	public ContribuenteDTO() {
	}

	public ContribuenteDTO(Long id) {
		this.id = id;
	}

	public ContribuenteDTO(Long id, String nome, String cognome, Date dataNascita, String codiceFiscale, String indirizzo) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteDTO(String nome, String cognome, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public Set<CartellaEsattorialeDTO> getCartelleEsattoriali() {
		return cartelleEsattoriali;
	}

	public void setCartelleEsattoriali(Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public Boolean getDaAttenzionare() {
		return daAttenzionare;
	}

	public void setDaAttenzionare(Boolean daAttenzionare) {
		this.daAttenzionare = daAttenzionare;
	}

	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataNascita, this.codiceFiscale, this.indirizzo);
	}

	public static ContribuenteDTO buildContribuenteDTOFromModel(Contribuente contribuenteModel, boolean includeCartelleEsattoriali, boolean verifica) {
		ContribuenteDTO contribuenteDTO = new ContribuenteDTO(contribuenteModel.getId(), contribuenteModel.getNome(), contribuenteModel.getCognome(),
				contribuenteModel.getDataNascita(), contribuenteModel.getCodiceFiscale(), contribuenteModel.getIndirizzo());
		if(verifica) {
			Set<CartellaEsattoriale> listaCartelle = contribuenteModel.getCartelleEsattoriali();
			for (CartellaEsattoriale item : listaCartelle) {
				if (item.getStato().getDescrizione() == "IN_CONTENZIOSO") {
					contribuenteDTO.setDaAttenzionare(true);
				}
			}
		}
		return contribuenteDTO;
	}

	public static List<ContribuenteDTO> createContribuenteDTOListFromModelList(List<Contribuente> modelListInput, boolean includeCartelleEsattoriali, boolean verifica) {
		return modelListInput.stream().map(contribuenteEntity -> {
			ContribuenteDTO result = ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteEntity, includeCartelleEsattoriali, verifica);
			if(includeCartelleEsattoriali)
				result.setCartelleEsattoriali(CartellaEsattorialeDTO.createCartellaEsattorialeDTOSetFromModelSet(contribuenteEntity.getCartelleEsattoriali(), false));
			return result;
		}).collect(Collectors.toList());
	}

}
