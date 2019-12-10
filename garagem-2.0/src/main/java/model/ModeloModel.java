package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Marca;
import entity.Modelo;

public class ModeloModel {

    Connection connection;

    public ModeloModel() {
        this.connection = SQLConnection.getConnection();
    }

    public boolean save(Modelo modelo){
    	String sql = "INSERT INTO modelo (nome, tipo, marca) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, modelo.getNome());
            ps.setString(2, modelo.getTipo());
            ps.setInt(3, modelo.getMarca().getId());

            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean update(Modelo modelo){
    	String sql = "UPDATE modelo SET nome = ?, tipo = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {

        	ps = connection.prepareStatement(sql);
        	ps.setString(1, modelo.getNome());
        	ps.setString(2, modelo.getTipo());
        	ps.setInt(3, modelo.getId());
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(int id){
        String sql = "DELETE FROM modelo WHERE id = ?";
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

    public List<Modelo> findAll(){
    	String sql = "SELECT m.id, m.nome, m.tipo, marca.id, marca.nome FROM modelo m INNER JOIN marca marca on marca.id = m.marca ";
    	PreparedStatement ps = null;
    	ArrayList<Modelo> modelos = new ArrayList<Modelo>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Modelo modelo = new Modelo();
	    		Marca marca = new Marca();
	    		
	    		modelo.setId(rs.getInt(1));
	    		modelo.setNome(rs.getString(2));
	    		modelo.setTipo(rs.getString(3));
	    		
	    		marca.setId(rs.getInt(4));
	    		marca.setNome(rs.getString(5));
	    		
	    		modelo.setMarca(marca);
	    		modelos.add(modelo);
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return modelos;
    }
    
    public List<Modelo> findByMarca(int idMarca){
    	String sql = "SELECT m.id, m.nome, m.tipo, marca.id, marca.nome FROM modelo m INNER JOIN marca marca on marca.id = m.marca WHERE marca.id = ?";
    	PreparedStatement ps = null;
    	ArrayList<Modelo> modelos = new ArrayList<Modelo>();
    	
    	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, idMarca);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Modelo modelo = new Modelo();
	    		Marca marca = new Marca();
	    		
	    		modelo.setId(rs.getInt(1));
	    		modelo.setNome(rs.getString(2));
	    		modelo.setTipo(rs.getString(3));
	    		
	    		marca.setId(rs.getInt(4));
	    		marca.setNome(rs.getString(5));
	    		
	    		modelo.setMarca(marca);
	    		modelos.add(modelo);
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return modelos;
    }

    public Modelo findById(int id) {
    	try {
    		String sql = "SELECT  m.id, m.nome, m.tipo, marca.id, marca.nome FROM modelo m INNER JOIN marca marca on marca.id = m.marca WHERE m.id = ?";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	rs.next();
	    	Modelo modelo = new Modelo();
	    	Marca marca = new Marca();
    		
    		modelo.setId(rs.getInt(1));
    		modelo.setNome(rs.getString(2));
    		modelo.setTipo(rs.getString(3));
    		
    		marca.setId(rs.getInt(4));
    		marca.setNome(rs.getString(5));
    		
    		modelo.setMarca(marca);
	    	
	    	return modelo;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
	
}
