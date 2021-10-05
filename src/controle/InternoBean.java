package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Produto;
import service.ProdutoService;

@ViewScoped
@ManagedBean
public class InternoBean {

	
	@EJB
	private ProdutoService produtoService;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@PostConstruct
	public void init() {
		ListaProdutos();
	}
	
	
	
	public void ListaProdutos() {
		Integer valor = 10;	
		produtos = produtoService.BuscaProdutos(valor);		
		System.out.print(produtos);
	}



	public List<Produto> getProdutos() {
		return produtos;
	}



	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
