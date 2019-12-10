package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Badeco;
import entity.enums.TipoPessoa;

public class BadecoModel {
	
	Connection connection;
	private FuncionarioModel funcionarioModel;
	
    public BadecoModel() {
        this.connection = SQLConnection.getConnection();
        this.funcionarioModel = new FuncionarioModel();
    }
    
    public boolean save(Badeco badeco) {
    	String sql = "INSERT INTO badeco (id, funcao) VALUES (?, ?)";

        try {
            Integer idFuncionario = funcionarioModel.save(badeco, TipoPessoa.BADECO);
            
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setInt(1, idFuncionario);
            ps.setString(2, badeco.getFuncao());
            
            ps.executeUpdate();        	
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
	
    public boolean update(Badeco badeco){
    	String sql = "UPDATE badeco SET funcao = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
        	funcionarioModel.update(badeco);
        	ps = connection.prepareStatement(sql);
        	ps.setString(1, badeco.getFuncao());
        	ps.setInt(2, badeco.getId());
        	
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean delete(int id){
        String sql = "DELETE FROM badeco WHERE id = ?";
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
    
    public List<Badeco> findAll(){
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
    			   + " b.funcao "
    			   + " FROM badeco b "
    			   + " INNER JOIN funcionario f on f.id = b.id "
    			   + " INNER JOIN pessoa p on p.id = f.id "
    			   + " WHERE p.tipo = 3 ";
    	PreparedStatement ps = null;
    	List<Badeco> badecos = new ArrayList<Badeco>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Badeco badeco = new Badeco();
	    		
	    		badeco.setId(rs.getInt(1));
	    		badeco.setNome(rs.getString(2));
	    		badeco.setCpf(rs.getString(3));
	    		badeco.setEndereco(rs.getString(4));
	    		badeco.setTelefone(rs.getString(5));
	    		badeco.setDataNascimento(rs.getString(6));
	    		badeco.setTipoPessoa(rs.getInt(7));
	    		badeco.setCodigo(rs.getInt(8));
	    		badeco.setUsuario(rs.getString(9));
	    		badeco.setSalario(rs.getBigDecimal(10));
	    		badeco.setFuncao(rs.getString(11));
	    		
	    		badecos.add(badeco);
	    		
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return badecos;
    }
    
    public Badeco findById(int id) {
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
     			   + " b.funcao "
     			   + " FROM badeco b "
     			   + " INNER JOIN funcionario f on f.id = b.id "
     			   + " INNER JOIN pessoa p on p.id = f.id "
     			   + " WHERE f.id = ? AND p.tipo = 3 ";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	Badeco bandeco = new Badeco();
	    	if(rs != null) {
		    	rs.next();
	    		
		    	bandeco.setId(rs.getInt(1));
		    	bandeco.setNome(rs.getString(2));
		    	bandeco.setCpf(rs.getString(3));
	    		bandeco.setEndereco(rs.getString(4));
	    		bandeco.setTelefone(rs.getString(5));
	    		bandeco.setDataNascimento(rs.getString(6));
	    		bandeco.setTipoPessoa(rs.getInt(7));
	    		bandeco.setCodigo(rs.getInt(8));
	    		bandeco.setUsuario(rs.getString(9));
	    		bandeco.setSalario(rs.getBigDecimal(10));
	    		bandeco.setFuncao(rs.getString(11));		    	
		    	
	    	}
	    	
	    	return bandeco;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
}
