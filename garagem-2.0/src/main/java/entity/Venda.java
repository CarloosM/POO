package entity;

import java.math.BigDecimal;

public class Venda {
	
	private Integer codigoVenda;
	
	private Automovel automovel;
	
	private BigDecimal valorVenda;
	
	private Cliente cliente;
	
	private Funcionario funcionario;
	
	private String dataVenda;
	
	private BigDecimal comissaoVenda;

	public Integer getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(Integer codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public BigDecimal getComissaoVenda() {
		return comissaoVenda;
	}

	public void setComissaoVenda(BigDecimal comissaoVenda) {
		this.comissaoVenda = comissaoVenda;
	}	
}
