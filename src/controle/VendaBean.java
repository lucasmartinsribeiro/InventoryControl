package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.inject.Named;

import com.sun.xml.internal.ws.client.RequestContext;

import modelo.ItemVenda;
import modelo.Pagamento;
import modelo.Produto;
import modelo.TipoPagamento;
import modelo.Venda;
import service.ItemVendaService;
import service.PagamentoService;
import service.ProdutoService;
import service.VendaService;


@ViewScoped
@ManagedBean
public class VendaBean {

	@EJB
	private VendaService vendaService;
	@EJB
	private ProdutoService produtoService;
	@EJB 
	private PagamentoService pagamentoService;
	@EJB
	private ItemVendaService itemVendaService;
	
	private Produto proAux = new Produto();
	private Venda venda = new Venda();
	private ItemVenda iVenda = new ItemVenda();
	private Pagamento pagamento = new Pagamento();
	private String tPag = null;
	private TipoPagamento teste = null;
	private Produto produto;
	private Produto produtoSelecionado;
	private String codProduto = null;
	private List<Produto> produtos = new ArrayList<Produto>();
	private String valor = null;
	private Boolean statusCompra = false;
	private String quantidade = "";
	
	private int OpcaoSelectParcela = 0;
	
	
	private Double valorParcialCompra = 0.0;
 
	private ItemVenda itemVenda = new ItemVenda();
	private List<ItemVenda> itemsVenda = new ArrayList<ItemVenda>();
	
	private List<Produto> produtosSelecionado = new ArrayList<Produto>();
	
	@PostConstruct
	public void init() {
		produtosSelecionado.clear();
		itemsVenda.clear();
		statusCompra = false;
		valorParcialCompra = 0.0;
	}
	
	
	
	public void PesquisaProduto(){
		if(!codProduto.isEmpty()){
			produtos = produtoService.ObtemPorCodigo(codProduto);
		}
	}

	
	public void teste() {
		if(TipoPagamento.valueOf(tPag).equals(TipoPagamento.valueOf("DINHEIRO"))) {
			valor = Double.toString(valorParcialCompra);
			OpcaoSelectParcela = 1;
			statusCompra = true;
		}else {
			valor = Double.toString(valorParcialCompra/OpcaoSelectParcela);
			statusCompra = false;
		}
				
	}
	
	
	
	
	
	public void selecionaProduto(Produto p) {
		produtoSelecionado = p;
		produto = p;
	}

	
	public void GuardaProduto() {
		itemVenda.setProduto(produtoSelecionado);
		itemVenda.setQuantidade(produtoSelecionado.getQuantidade());
		itemVenda.setValorUnitario(produtoSelecionado.getPreco());
		
		System.out.println(itemVenda.toString());
		venda.AdicionaItem(itemVenda);
		
		itemsVenda = venda.getItens();
		
		
		Double auxValCompra1 = 0.0;
		Integer totalProdutos = 0;
		auxValCompra1 = produtoSelecionado.getPreco();
		totalProdutos = produtoSelecionado.getQuantidade();
		
		valorParcialCompra += auxValCompra1*totalProdutos;
		
		itemVenda = new ItemVenda();
	}
	
	
	public void ConfirmaCompra() throws IOException {		
		pagamento.setTipo(TipoPagamento.valueOf(tPag));
		pagamento.setQuantidadeParcela(OpcaoSelectParcela);
		pagamento.setValorTotal(valorParcialCompra);
		
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		
		venda.setDataVenda(data);
		venda.setPagamento(pagamento);
		venda.setItens(itemsVenda);
		iVenda.setVenda(venda);	
		
		try{	
			vendaService.create(venda);		
			for(int i=0; i < venda.getItens().size(); i++) {
				proAux = produtoService.obtemPorId(venda.getItens().get(i).getProduto().getId());				
				proAux.setQuantidade(proAux.getQuantidade()-venda.getItens().get(i).getQuantidade());
				produtoService.merge(proAux);				
			}			
			System.out.println("Compra Efetuada com Sucesso");
			displayMensagem("Sucesso", "Registro Efetuado com Sucesso!");
		}catch (Exception e) {
			displayMensagem("Error", "Ocorreu um erro ao fazer o registro!");
			System.out.println(e);
		}
		
		produto = new Produto();
		proAux = new Produto();
		venda = new Venda();
		pagamento = new Pagamento();
		iVenda = new ItemVenda();
		
	}
	
	
	public void cancelar() {
		produto = new Produto();
		produtoSelecionado = new Produto();
		produtos.clear();
		produtosSelecionado.clear();
		itemsVenda.clear();
		valorParcialCompra = 0.0;
		
		produto = new Produto();
		proAux = new Produto();
		venda = new Venda();
		pagamento = new Pagamento();
		iVenda = new ItemVenda();
	}
	
	
	



	
	public void displayMensagem(String title, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();  
		context.addMessage(null, new FacesMessage(title, mensagem)); 
	}
	


	



	public String gettPag() {
		return tPag;
	}



	public void settPag(String tPag) {
		this.tPag = tPag;
	}



	public Pagamento getPagamento() {
		return pagamento;
	}



	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}



	public String getCodProduto() {
		return codProduto;
	}



	public Venda getVenda() {
		return venda;
	}



	public void setVenda(Venda venda) {
		this.venda = venda;
	}



	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}



	public List<Produto> getProdutos() {
		return produtos;
	}



	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}



	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}



	public List<Produto> getProdutosSelecionado() {
		return produtosSelecionado;
	}



	public void setProdutosSelecionado(List<Produto> produtosSelecionado) {
		this.produtosSelecionado = produtosSelecionado;
	}



	public String getValor() {
		return valor;
	}



	public void setValor(String valor) {
		this.valor = valor;
	}



	




	public Boolean getStatusCompra() {
		return statusCompra;
	}



	public void setStatusCompra(Boolean statusCompra) {
		this.statusCompra = statusCompra;
	}



	public String getQuantidade() {
		return quantidade;
	}



	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}



	public List<ItemVenda> getItemsVenda() {
		return itemsVenda;
	}



	public void setItemsVenda(List<ItemVenda> itemsVenda) {
		this.itemsVenda = itemsVenda;
	}



	public Double getValorParcialCompra() {
		return valorParcialCompra;
	}



	public void setValorParcialCompra(Double valorParcialCompra) {
		this.valorParcialCompra = valorParcialCompra;
	}



	public int getOpcaoSelectParcela() {
		return OpcaoSelectParcela;
	}



	public void setOpcaoSelectParcela(int opcaoSelectParcela) {
		OpcaoSelectParcela = opcaoSelectParcela;
	}

	
	
	
	
	
}
