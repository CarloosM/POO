package entity;

import java.math.BigDecimal;

public class Gerente extends Funcionario {

	private String departamento;
	
	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public BigDecimal calcularSalario() {
		return salario.multiply(BigDecimal.valueOf(1.5));
	}
	
}
