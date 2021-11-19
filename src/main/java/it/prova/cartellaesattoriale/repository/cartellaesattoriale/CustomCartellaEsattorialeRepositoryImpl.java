package it.prova.cartellaesattoriale.repository.cartellaesattoriale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;

public class CustomCartellaEsattorialeRepositoryImpl implements CustomCartellaEsattorialeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select ce from CartellaEsattoriale ce join ce.contribuente c where ce.id = ce.id ");

		if (StringUtils.isNotBlank(example.getDescrizione())) {
			whereClauses.add(" ce.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getImporto() != null && example.getImporto() > 0) {
			whereClauses.add(" ce.importo >= :importo ");
			paramaterMap.put("imorto", example.getImporto());
		}
		if (example.getStato() != null) {
			whereClauses.add(" ce.stato =:stato ");
			paramaterMap.put("stato", example.getStato());
		}	
		if (example.getContribuente().getId() != null) {
			whereClauses.add(" c.id =:id ");
			paramaterMap.put("id", example.getContribuente().getId());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<CartellaEsattoriale> typedQuery = entityManager.createQuery(queryBuilder.toString(), CartellaEsattoriale.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
