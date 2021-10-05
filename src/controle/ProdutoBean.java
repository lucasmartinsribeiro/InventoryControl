package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Produto;
import service.ProdutoService;

@ViewScoped
@ManagedBean
public class ProdutoBean {
	@EJB
	private ProdutoService produtoService;
	
	private Produto produto = new Produto();
	
	private String numCodigo = null;
	private Boolean statusInputs = false;
	
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@PostConstruct
	public void init() {
		if(numCodigo=="") {
			PopulaProdutos();
		}else {
			PopulaProdutos();
		}
		statusInputs = false;
	}
	
	private void PopulaProdutos() {
		produtos = produtoService.listAll();
	}
	
		
	public void gravar() {		
		if (produto.getId()==null) {
			try {
				produtoService.create(produto);
				System.out.println("Produto Cadastrado");
				displayMensagem("Sucesso", "Registro Efetuado com Sucesso!");
			}catch (Exception e) {
				System.out.println(e);
			}
		}else {
			try {
				produtoService.merge(produto);
				System.out.println("Produto Atualizado");
				displayMensagem("Sucesso", "Registro Atualizado com Sucesso!");
				statusInputs = false;
			}catch (Exception e) {
				displayMensagem("Error", "Não foi possivel completar a tarefa");
				System.out.println(e);
			}
		}

		produto = new Produto();
		PopulaProdutos();
	}

	
	public void cancelar() {
		produto = new Produto();
		statusInputs = false;
		PopulaProdutos();
	}
	
	
	public void filterCodigo() {
		System.out.println(numCodigo);
		if(numCodigo.isEmpty()) {
			PopulaProdutos();
		}else {
			produtos = produtoService.ObtemPorCodigo(numCodigo);			
		}
	}
	
	public void carregaProduto(Produto p) {
		produto = p;
	}
	
	public void excluirProduto(Produto p) {
		try {
			produtoService.remove(p);
			System.out.println("Produto Removido");
			displayMensagem("Sucesso", "Registro Removido com Sucesso!");
		}catch (Exception e) {
			displayMensagem("Error", "Não foi possivel completar a tarefa");
			System.out.println(e);
		}
		produto = new Produto();
		PopulaProdutos();
	}
	
	public void AlterarQuantidade(Produto p) {
		produto = p;
		statusInputs = true;
	}
	
	
	
	public void displayMensagem(String title, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();  
		context.addMessage(null, new FacesMessage(title, mensagem)); 
	}
	
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getNumCodigo() {
		return numCodigo;
	}

	public void setNumCodigo(String numCodigo) {
		this.numCodigo = numCodigo;
	}

	public Boolean getStatusInputs() {
		return statusInputs;
	}

	public void setStatusInputs(Boolean statusInputs) {
		this.statusInputs = statusInputs;
	}
	
	
}
