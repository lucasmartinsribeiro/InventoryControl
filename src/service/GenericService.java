package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Criteria;
import org.hibernate.ejb.QueryHints;

import com.mysql.cj.Session;

import modelo.ItemVenda;
import modelo.TipoPagamento;
import modelo.Venda;


@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class GenericService<T> {
	
	@PersistenceContext(unitName="punit")
    private EntityManager entityManager;
	
	private final Class<T> classe;

	public GenericService(Class<T> classe) {
		this.classe = classe;        
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void create(T entity ) {
        getEntityManager().persist(entity); 
    }

    public void merge(T entity){
			entity = getEntityManager().merge(entity);			
    }

    //verificar
    public void remove(T entity) {
	        getEntityManager().remove(getEntityManager().merge(entity));		  
    }
    
    public final T obtemPorId(Long id ){
		T entity = getEntityManager().find(classe, id);		
		return entity;
	}
    
    public List<T> listAll(){
    	final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();	
    	final CriteriaQuery<T> cQuery = cb.createQuery(classe);
	
    	cQuery.select(cQuery.from(classe));

    	List<T> list = getEntityManager().createQuery(cQuery).getResultList();
    	return list;
    }	
    
    
	/*
	 * public List<T> ObtemPorVenda(Long id) { CriteriaBuilder builder =
	 * entityManager.getCriteriaBuilder(); CriteriaQuery<T> criteria =
	 * builder.createQuery(classe); Root<T> tRoot = criteria.from(classe);
	 * criteria.select(tRoot);
	 * 
	 * criteria.where(builder.equal( (Expression) tRoot.get("venda_id"), id));
	 * List<T> result = entityManager.createQuery(criteria).getResultList(); return
	 * result; }
	 */
    
    

    
    public List<T> ObtemPorCodigo(String codigo) {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder(); 
        CriteriaQuery<T> criteria = builder.createQuery(classe);
        Root<T> tRoot = criteria.from(classe);        
        criteria.select(tRoot);
        
        criteria.where(builder.like( (Expression) tRoot.get("codigo"), new String(codigo)));
        List<T> result = entityManager.createQuery(criteria).getResultList();        
        return result;
    }
    
    public List<T> BuscaProdutos(Integer valor){
    	 CriteriaBuilder builder = entityManager.getCriteriaBuilder(); 
         CriteriaQuery<T> criteria = builder.createQuery(classe);
         Root<T> tRoot = criteria.from(classe);        
         criteria.select(tRoot);
    	 
         criteria.where(builder.lt( (Expression) tRoot.get("quantidade"), new Integer(valor)));
         List<T> result = entityManager.createQuery(criteria).getResultList(); 
         return result;
    }
    
    
    public List<T> PesquisaRelatorio(Date inicio, Date Final){
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder(); 
        CriteriaQuery<T> criteria = builder.createQuery(classe);
        Root<T> tRoot = criteria.from(classe);        
        criteria.select(tRoot);
        
        criteria.where(builder.between(tRoot.<Date>get("dataVenda"), inicio, Final));
        List<T> result = entityManager.createQuery(criteria).getResultList(); 
        
        return result;
    }
    
    
    
}


