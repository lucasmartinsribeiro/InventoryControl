package service;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;


import modelo.Venda;

@Stateless
public class VendaService extends GenericService<Venda>{

	public VendaService() {
		super(Venda.class);
	}
	
	public Venda buscaVendaComItensJoin(Long id) {
		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    	final CriteriaQuery<Venda> criteriaQuery = criteriaBuilder.createQuery(Venda.class);
    	final Root<Venda> vendaRoot = criteriaQuery.from(Venda.class);
    	vendaRoot.fetch("itens", JoinType.INNER);
    	
    	criteriaQuery.select(vendaRoot);
    	criteriaQuery.where(criteriaBuilder.equal(vendaRoot.get("id"), id));
    	
    	Venda result = getEntityManager().createQuery(criteriaQuery).getSingleResult();
    	return result;
	}
}
