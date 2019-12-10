package entity;

import java.math.BigDecimal;

public class Funcionario extends Pessoa implements InterfaceFuncionario {

	private Integer codigo;
	
	private String usuario;
	
	private String senha; 
	
	protected BigDecimal salario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public BigDecimal calcularSalario() {
		return salario.multiply(BigDecimal.valueOf(1.0));
	}
	
}
