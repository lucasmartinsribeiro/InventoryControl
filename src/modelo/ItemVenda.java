package modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda {

	@Id @GeneratedValue
	private Long id;	
	private Integer quantidade;
	private Double valorUnitario;
	
	@ManyToOne
	private Venda venda;
	
	@ManyToOne
	private Produto produto;
	

	public ItemVenda() {
		
	}
	
	public Double getTotalItem(Double item) {
		item += valorUnitario;
		return item;
	}
	

	public ItemVenda(Integer quantidade, Double valorUnitario, Venda venda, Produto produto) {
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.venda = venda;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + ", venda="
				+ venda + ", produto=" + produto + "]";
	}

	
	
}
