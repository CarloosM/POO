package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Gerente;
import entity.enums.TipoPessoa;

public class GerenteModel {

	Connection connection;
	private FuncionarioModel funcionarioModel;
	
    public GerenteModel() {
        this.connection = SQLConnection.getConnection();
        this.funcionarioModel = new FuncionarioModel();
    }
    
    public boolean save(Gerente gerente) {
    	String sql = "INSERT INTO gerente (id, departamento) VALUES (?, ?)";

        try {
            Integer idFuncionario = funcionarioModel.save(gerente, TipoPessoa.GERENTE);
            
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setInt(1, idFuncionario);
            ps.setString(2, gerente.getDepartamento());
            
            ps.executeUpdate();        	
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
	
    public boolean update(Gerente gerente){
    	String sql = "UPDATE gerente SET departamento = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
        	funcionarioModel.update(gerente);
        	ps = connection.prepareStatement(sql);
        	ps.setString(1, gerente.getDepartamento());
        	ps.setInt(2, gerente.getId());
        	
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean delete(int id){
        String sql = "DELETE FROM gerente WHERE id = ?";
        PreparedStatement ps = null;

        try {

        	ps = connection.prepareStatement(sql);
        	ps.setInt(1,id);

        	ps.executeUpdate();
        	funcionarioModel.delete(id);
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  false;
    }
    
    public List<Gerente> findAll(){
    	String sql = " SELECT p.id, "
    			   + " p.nome, "
    			   + " p.cpf, "
    			   + " p.endereco, "
    			   + " p.telefone, "
    			   + " p.data_nascimento, "
    			   + " p.tipo, "
    			   + " f.codigo, "
    			   + " f.usuario,"
    			   + " f.salario,"
    			   + " g.departamento "
    			   + " FROM gerente g "
    			   + " INNER JOIN funcionario f on f.id = g.id "
    			   + " INNER JOIN pessoa p on p.id = f.id "
    			   + " WHERE p.tipo = 4 ";
    	PreparedStatement ps = null;
    	List<Gerente> gerentes = new ArrayList<Gerente>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Gerente gerente = new Gerente();
	    		
	    		gerente.setId(rs.getInt(1));
	    		gerente.setNome(rs.getString(2));
	    		gerente.setCpf(rs.getString(3));
	    		gerente.setEndereco(rs.getString(4));
	    		gerente.setTelefone(rs.getString(5));
	    		gerente.setDataNascimento(rs.getString(6));
	    		gerente.setTipoPessoa(rs.getInt(7));
	    		gerente.setCodigo(rs.getInt(8));
	    		gerente.setUsuario(rs.getString(9));
	    		gerente.setSalario(rs.getBigDecimal(10));
	    		gerente.setDepartamento(rs.getString(11));
	    		
	    		gerentes.add(gerente);
	    		
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return gerentes;
    }
    
    public Gerente findById(int id) {
    	try {
    		String sql = " SELECT p.id, "
     			   	   + " p.nome, "
     			   	   + " p.cpf, "
     			   	   + " p.endereco, "
     			   	   + " p.telefone, "
     			   	   + " p.data_nascimento, "
     			   	   + " p.tipo, "
     			   	   + " f.codigo, "
     			   	   + " f.usuario,"
     			   	   + " f.salario,"
     			   	   + " g.departamento "
     			   	   + " FROM gerente g "
     			   	   + " INNER JOIN funcionario f on f.id = g.id "
     			   	   + " INNER JOIN pessoa p on p.id = f.id "
     			   	   + " WHERE g.id = ? AND p.tipo = 4 ";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	Gerente gerente = new Gerente();
	    	if(rs != null) {
		    	rs.next();
	    		
	    		gerente.setId(rs.getInt(1));
	    		gerente.setNome(rs.getString(2));
	    		gerente.setCpf(rs.getString(3));
	    		gerente.setEndereco(rs.getString(4));
	    		gerente.setTelefone(rs.getString(5));
	    		gerente.setDataNascimento(rs.getString(6));
	    		gerente.setTipoPessoa(rs.getInt(7));
	    		gerente.setCodigo(rs.getInt(8));
	    		gerente.setUsuario(rs.getString(9));
	    		gerente.setSalario(rs.getBigDecimal(10));
	    		gerente.setDepartamento(rs.getString(11));		    	
		    	
	    	}
	    	
	    	return gerente;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
}
