package controle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ItemVendaService;


@ViewScoped
@ManagedBean
public class ItemVendaBean {
	@EJB
	private ItemVendaService itemVendaService;
	
	
	
	
	
	
}
