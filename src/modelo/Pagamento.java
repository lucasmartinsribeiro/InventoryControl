package modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pagamento {
	@Id @GeneratedValue
	private Long id;
	private Double valorTotal;
	private Integer quantidadeParcela;	
	
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;
	
	public Double getValorParcela() {
		Double valParc = 0.0;
		return valParc;
	}

	public Pagamento() {
	}

	public Pagamento(Double valorTotal, Integer quantidadeParcela, TipoPagamento tipo) {
		this.valorTotal = valorTotal;
		this.quantidadeParcela = quantidadeParcela;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", valorTotal=" + valorTotal + ", quantidadeParcela=" + quantidadeParcela
				+ ", tipo=" + tipo + "]";
	}
	
	
}
