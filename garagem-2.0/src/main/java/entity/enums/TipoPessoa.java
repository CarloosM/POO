package entity.enums;

public enum TipoPessoa {
	
	CLIENTE(1, "Cliente"),
	FUNCIONARIO(2, "Funcionário"),
	BADECO(3, "Badeco"),
	GERENTE(4, "Gerente");

	private TipoPessoa(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	private Integer id;
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public TipoPessoa getDescricaoById(Integer id) {
		TipoPessoa tipoPessoa = null;
		for(TipoPessoa tipo : TipoPessoa.values()) {
			if(tipo.getId().equals(id)) {
				tipoPessoa= tipo;
			}
		}
		return tipoPessoa; 
	}
}
