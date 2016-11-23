package br.usjt.falacidadao.model;

public enum Status {

	ABERTO("ABERTO"), BLOQUEADO("BLOQUEADO"), CANCELADO("CANCELADO"), FECHADO("FECHADO"), ENCAMINHADO("ENCAMINHADO");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
