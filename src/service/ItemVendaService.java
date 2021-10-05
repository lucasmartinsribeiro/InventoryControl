package service;

import javax.ejb.Stateless;

import modelo.ItemVenda;

@Stateless
public class ItemVendaService extends GenericService<ItemVenda>{

	public ItemVendaService() {
		super(ItemVenda.class);
	}
	
	
}
