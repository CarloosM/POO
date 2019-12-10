package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Automovel;
import entity.Marca;
import entity.Modelo;

public class AutomovelModel {

	Connection connection;
	
	
	/**
	 * Model onde vai estar todo o processamento de dados
	 * 
	 */
	
    public AutomovelModel() {
        this.connection = SQLConnection.getConnection();
    }

    public boolean save(Automovel automovel){
    	String sql = "INSERT INTO automovel (cor, ano_fabricacao, ano_modelo, chassi, placa, km, valor, modelo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, automovel.getCor());
            ps.setInt(2, automovel.getAnoFabricacao().getValue());
            ps.setInt(3, automovel.getAnoModelo().getValue());
            ps.setString(4, automovel.getChassi());
            ps.setString(5, automovel.getPlaca());
            ps.setString(6, automovel.getKm());
            ps.setBigDecimal(7, automovel.getValor());
            ps.setInt(8, automovel.getModelo().getId());
            
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean update(Automovel automovel){
    	String sql = "UPDATE automovel SET cor = ?, ano_fabricacao = ?, ano_modelo = ?, chassi = ?, placa = ?, km = ?, valor = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {

        	ps = connection.prepareStatement(sql);
        	ps.setString(1, automovel.getCor());
        	ps.setInt(2, automovel.getAnoFabricacao().getValue());
        	ps.setInt(3, automovel.getAnoModelo().getValue());
            ps.setString(4, automovel.getChassi());
            ps.setString(5, automovel.getPlaca());
            ps.setString(6, automovel.getKm());
            ps.setBigDecimal(7, automovel.getValor());
            ps.setInt(8, automovel.getId());
        	
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(int id){
        String sql = "DELETE FROM automovel WHERE id = ?";
        PreparedStatement ps = null;

        try {

        	ps = connection.prepareStatement(sql);
        	ps.setInt(1,id);

        	ps.executeUpdate();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  false;
    }

    public List<Automovel> findAll(){
    	String sql = " SELECT a.id, a.cor, a.ano_fabricacao, a.ano_modelo, a.chassi, a.placa, a.km, a.valor, m.id, m.nome, m.tipo, ma.id, ma.nome FROM automovel a INNER JOIN modelo m on m.id = a.modelo INNER JOIN marca ma on ma.id = m.marca ";
    	PreparedStatement ps = null;
    	ArrayList<Automovel> automoveis = new ArrayList<Automovel>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Automovel automovel = new Automovel();
	    		Modelo modelo = new Modelo();
	    		Marca marca = new Marca();
	    		
	    		automovel.setId(rs.getInt(1));
	    		automovel.setCor(rs.getString(2));
	    		automovel.setAnoFabricacao(Year.of(rs.getInt(3)));
	    		automovel.setAnoModelo(Year.of(rs.getInt(4)));
	    		automovel.setChassi(rs.getString(5));
	    		automovel.setPlaca(rs.getString(6));
	    		automovel.setKm(rs.getString(7));
	    		automovel.setValor(rs.getBigDecimal(8));
	    		
	    		modelo.setId(rs.getInt(9));
	    		modelo.setNome(rs.getString(10));
	    		modelo.setTipo(rs.getString(11));
	    		
	    		marca.setId(rs.getInt(12));
	    		marca.setNome(rs.getString(13));
	    		
	    		modelo.setMarca(marca);
	    		automovel.setModelo(modelo);
	    		
	    		automoveis.add(automovel);
	    		
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return automoveis;
    }

    public Automovel findById(int id) {
    	try {
    		String sql = " SELECT a.id, a.cor, a.ano_fabricacao, a.ano_modelo, a.chassi, a.placa, a.km, a.valor, m.id, m.nome, m.tipo, ma.id, ma.nome FROM automovel a INNER JOIN modelo m on m.id = a.modelo INNER JOIN marca ma on ma.id = m.marca WHERE a.id = ?";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	Automovel automovel = new Automovel();
	    	Modelo modelo = new Modelo();
    		Marca marca = new Marca();
	    	if(rs != null) {
		    	rs.next();
		    	automovel.setId(rs.getInt(1));
	    		automovel.setCor(rs.getString(2));
	    		automovel.setAnoFabricacao(Year.of(rs.getInt(3)));
	    		automovel.setAnoModelo(Year.of(rs.getInt(4)));
	    		automovel.setChassi(rs.getString(5));
	    		automovel.setPlaca(rs.getString(6));
	    		automovel.setKm(rs.getString(7));
	    		automovel.setValor(rs.getBigDecimal(8));
	    		
	    		modelo.setId(rs.getInt(9));
	    		modelo.setNome(rs.getString(10));
	    		modelo.setTipo(rs.getString(11));
	    		
	    		marca.setId(rs.getInt(12));
	    		marca.setNome(rs.getString(13));
	    		
	    		modelo.setMarca(marca);
	    		automovel.setModelo(modelo);
	    	}
	    	
	    	return automovel;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    public List<Automovel> findByModelo(int idModelo) {
    	String sql = " SELECT a.id, a.cor, a.ano_fabricacao, a.ano_modelo, a.chassi, a.placa, a.km, a.valor, m.id, m.nome, m.tipo, ma.id, ma.nome FROM automovel a INNER JOIN modelo m on m.id = a.modelo INNER JOIN marca ma on ma.id = m.marca WHERE m.id = ? ";
    	PreparedStatement ps = null;
    	ArrayList<Automovel> automoveis = new ArrayList<Automovel>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	ps.setInt(1, idModelo);
	    	
	    	while(rs.next()) {
	    		Automovel automovel = new Automovel();
	    		Modelo modelo = new Modelo();
	    		Marca marca = new Marca();
	    		
	    		automovel.setId(rs.getInt(1));
	    		automovel.setCor(rs.getString(2));
	    		automovel.setAnoFabricacao(Year.of(rs.getInt(3)));
	    		automovel.setAnoModelo(Year.of(rs.getInt(4)));
	    		automovel.setChassi(rs.getString(5));
	    		automovel.setPlaca(rs.getString(6));
	    		automovel.setKm(rs.getString(7));
	    		automovel.setValor(rs.getBigDecimal(8));
	    		
	    		modelo.setId(rs.getInt(9));
	    		modelo.setNome(rs.getString(10));
	    		modelo.setTipo(rs.getString(11));
	    		
	    		marca.setId(rs.getInt(12));
	    		marca.setNome(rs.getString(13));
	    		
	    		modelo.setMarca(marca);
	    		automovel.setModelo(modelo);
	    		
	    		automoveis.add(automovel);
	    		
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return automoveis;
    }
	
}
