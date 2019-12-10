package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Marca;

public class MarcaModel {

    Connection connection;

    public MarcaModel() {
        this.connection = SQLConnection.getConnection();
    }

    public boolean save(Marca marca){
    	String sql = "INSERT INTO marca (nome) VALUES (?)";

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, marca.getNome());

            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean update(Marca marca){
    	String sql = "UPDATE marca SET nome = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {

        	ps = connection.prepareStatement(sql);
        	ps.setString(1,marca.getNome());
        	ps.setInt(2,marca.getId());
        	
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(int id){
        String sql = "DELETE FROM marca WHERE id = ?";
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

    public List<Marca> findAll(){
    	String sql = "SELECT * FROM marca";
    	PreparedStatement ps = null;
    	ArrayList<Marca> marcas = new ArrayList<Marca>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Marca marca = new Marca();
	    		
	    		marca.setId(rs.getInt(1));
	    		marca.setNome(rs.getString(2));
	    		marcas.add(marca);
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return marcas;
    }

    public Marca findById(int id) {
    	try {
    		String sql = "SELECT * FROM marca WHERE id = ?";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	Marca marca = new Marca();
	    	if(rs != null) {
		    	rs.next();
		    	marca.setId(rs.getInt(1));
		    	marca.setNome(rs.getString(2));
	    	}
	    	
	    	return marca;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }

}
