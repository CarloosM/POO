package entity;

import java.math.BigDecimal;
import java.time.Year;

public class Automovel {
	
	private int id;
	private String cor;
	private Year anoFabricacao;
	private Year anoModelo;
	private String chassi;
	private String placa;
	private String km;
	private BigDecimal valor;
	private Modelo modelo;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public Year getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(Year anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	
	public Year getAnoModelo() {
		return anoModelo;
	}
	
	public void setAnoModelo(Year anoModelo) {
		this.anoModelo = anoModelo;
	}
	
	public String getChassi() {
		return chassi;
	}
	
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getKm() {
		return km;
	}
	
	public void setKm(String km) {
		this.km = km;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}
