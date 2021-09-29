package br.com.antonina.toca.enus;

public enum OrderStatus {
	
	
	
	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	private Integer cod;
	private String description;
	
	private OrderStatus(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static OrderStatus toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (OrderStatus x : OrderStatus.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status inválida! " + cod);
	}

}
