package service;

import javax.ejb.Stateless;

import modelo.Pagamento;

@Stateless
public class PagamentoService extends GenericService<Pagamento> {

	
	public PagamentoService() {
		super(Pagamento.class);
	}
}
