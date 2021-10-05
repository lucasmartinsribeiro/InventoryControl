package service;

import javax.ejb.Stateless;
import modelo.Produto;

@Stateless
public class ProdutoService extends GenericService<Produto>{
	
	public ProdutoService() {
		super(Produto.class);
	}
}
