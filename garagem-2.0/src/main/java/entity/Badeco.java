package entity;

import java.math.BigDecimal;

public class Badeco extends Funcionario {

	private String funcao;
	
	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public BigDecimal calcularSalario() {
		return salario.multiply(BigDecimal.valueOf(0.8));
	}
	
}
