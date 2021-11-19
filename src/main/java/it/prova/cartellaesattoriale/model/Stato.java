package it.prova.cartellaesattoriale.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Stato {
	CREATA("CREATA"), IN_VERIFICA("IN_VERIFICA"), CONCLUSA("CONCLUSA"), IN_CONTENZIOSO("IN_CONTENZIOSO");
	
	private String descrizione;

	Stato(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	@JsonCreator
	public static Stato getStatoFromCode(String input) {
		if(StringUtils.isBlank(input))
			return null;
		
		for (Stato statoItem : Stato.values()) {
			if (statoItem.equals(Stato.valueOf(input))) {
				return statoItem;
			}
		}
		return null;
	}

}
