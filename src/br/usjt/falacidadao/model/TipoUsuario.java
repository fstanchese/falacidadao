package br.usjt.falacidadao.model;

public enum TipoUsuario {

	SUPERVISOR("SUPERVISOR"), AVALIADOR("AVALIADOR"), CIDADAO("CIDADAO");

	private String descricao;

	TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
