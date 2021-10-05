package modelo;

public enum TipoPagamento {
	DINHEIRO("D"), 
	CARTAO("C");
	
	private final String tipo;
	TipoPagamento(String tipoPagamento){
		tipo = tipoPagamento;
	}
	
	
	public String getTipoPagamento() {
		return tipo;
	}
}
