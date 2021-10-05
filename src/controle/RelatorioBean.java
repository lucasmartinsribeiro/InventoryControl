package controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modelo.TipoPagamento;
import modelo.Venda;
import service.ItemVendaService;
import service.VendaService;

@ViewScoped
@ManagedBean
public class RelatorioBean {
	
	@PersistenceContext(unitName="punit")
    private EntityManager entityManager;
	
	@EJB
	private VendaService vendaService;
	@EJB
	private ItemVendaService itemVendaService;
	
	private Date dataInicial;
	private Date dataFinal;
	private String tPag;
	
	private Venda venda;
	
	private Venda vendaDetalhes = new Venda();
	
	private List<Venda> vendas = new ArrayList<Venda>();
	private List<Venda> vendasDetalhes = new ArrayList<Venda>();
	
	@PostConstruct
	public void init() {
		
	}
	
	public void Pesquisar() {
		System.out.println(dataInicial);
		System.out.println(dataFinal);		
		System.out.println();
		
		
		if(tPag.equals("0")){
			vendas = vendaService.PesquisaRelatorio(dataInicial, dataFinal);
		}else {
			vendas = PesquisaPorTPag(dataInicial, dataFinal, TipoPagamento.valueOf(tPag));
		}
	}
	
	
	public void detalhes(Venda v){		
		vendaDetalhes = vendaService.buscaVendaComItensJoin(v.getId());
	}	
	
	
    	
    public List<Venda> PesquisaPorTPag(Date inicio, Date Final, TipoPagamento tPag) {
    	final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    	final CriteriaQuery<Venda> criteriaQuery = criteriaBuilder.createQuery(Venda.class);
    	final Root<Venda> vendaRoot = criteriaQuery.from(Venda.class);
    	
    	criteriaQuery.where(criteriaBuilder.and(
    			criteriaBuilder.equal(vendaRoot.get("pagamento").get("tipo"), tPag),
    			criteriaBuilder.between(vendaRoot.<Date>get("dataVenda"), inicio, Final)
    			));
    	
    	List<Venda> venda = getEntityManager().createQuery(criteriaQuery).getResultList();
    	return venda;
    }
    
    
    
    
    public Venda getVendaDetalhes() {
		return vendaDetalhes;
	}

	public void setVendaDetalhes(Venda vendaDetalhes) {
		this.vendaDetalhes = vendaDetalhes;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getVendasDetalhes() {
		return vendasDetalhes;
	}

	public void setVendasDetalhes(List<Venda> vendasDetalhes) {
		this.vendasDetalhes = vendasDetalhes;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String gettPag() {
		return tPag;
	}

	public void settPag(String tPag) {
		this.tPag = tPag;
	}

	
	
}
