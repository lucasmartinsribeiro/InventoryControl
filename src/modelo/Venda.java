package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.OneToOne;

@Entity
public class Venda {

	@Id @GeneratedValue 
	private Long id;	
	private String cpfCliente;
	
	@Temporal(TemporalType.DATE)
	private Date dataVenda;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Pagamento pagamento;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "venda" ,targetEntity =ItemVenda.class)
	private List<ItemVenda> itens = new ArrayList<ItemVenda>();
	
	public Venda() {
	}

	public void AdicionaItem(ItemVenda i) {
		itens.add(i);
		i.setVenda(this);
	}
	
	
	
	public Venda(String cpfCliente, Date dataVenda, Pagamento pagamento, List<ItemVenda> itens) {
		super();
		this.cpfCliente = cpfCliente;
		this.dataVenda = dataVenda;
		this.pagamento = pagamento;
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", cpfCliente=" + cpfCliente + ", dataVenda=" + dataVenda + ", pagamento="
				+ pagamento + ", itens=" + itens + "]";
	}

	
	
}
