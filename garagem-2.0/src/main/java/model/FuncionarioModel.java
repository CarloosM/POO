package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Funcionario;
import entity.enums.TipoPessoa;

public class FuncionarioModel {
	
	Connection connection;
	private PessoaModel pessoaModel;
	
    public FuncionarioModel() {
        this.connection = SQLConnection.getConnection();
        this.pessoaModel = new PessoaModel();
    }
    
    public Integer save(Funcionario funcionario, TipoPessoa tipoPessoa) {
    	String sql = "INSERT INTO funcionario (id, codigo, usuario, senha, salario) VALUES (?, ?, ?, ?, ?)";

        try {
        	funcionario.setTipoPessoa(tipoPessoa.getId());
            Integer idPessoa = pessoaModel.save(funcionario);
            
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ps.setInt(2, funcionario.getCodigo());
            ps.setString(3, funcionario.getUsuario());
            ps.setString(4, funcionario.getSenha());
            ps.setBigDecimal(5, funcionario.getSalario());
            
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery("SELECT MAX(id) from pessoa"); 
            rs.next();
            return rs.getInt(1);      	
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return 0;
    }
	
    public boolean update(Funcionario funcionario){
    	String sql = "UPDATE funcionario SET codigo = ?, usuario = ?, senha = ?, salario = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
        	pessoaModel.update(funcionario);
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, funcionario.getCodigo());
        	ps.setString(2, funcionario.getUsuario());
        	ps.setString(3, funcionario.getSenha());
        	ps.setBigDecimal(4, funcionario.getSalario());
        	ps.setInt(5, funcionario.getId());
        	
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean delete(int id){
        String sql = "DELETE FROM funcionario WHERE id = ?";
        PreparedStatement ps = null;

        try {

        	ps = connection.prepareStatement(sql);
        	ps.setInt(1,id);

        	ps.executeUpdate();
        	pessoaModel.delete(id);
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  false;
    }
    
    public List<Funcionario> findAll(){
    	String sql = " SELECT p.id, "
    			   + " p.nome, "
    			   + " p.cpf, "
    			   + " p.endereco, "
    			   + " p.telefone, "
    			   + " p.data_nascimento, "
    			   + " p.tipo, "
    			   + " f.codigo, "
    			   + " f.usuario,"
    			   + " f.salario "
    			   + " FROM funcionario f "
    			   + " INNER JOIN pessoa p on p.id = f.id "
    			   + " WHERE p.tipo = 2 ";
    	PreparedStatement ps = null;
    	List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Funcionario funcionario = new Funcionario();
	    		
	    		funcionario.setId(rs.getInt(1));
	    		funcionario.setNome(rs.getString(2));
	    		funcionario.setCpf(rs.getString(3));
	    		funcionario.setEndereco(rs.getString(4));
	    		funcionario.setTelefone(rs.getString(5));
	    		funcionario.setDataNascimento(rs.getString(6));
	    		funcionario.setTipoPessoa(rs.getInt(7));
	    		funcionario.setCodigo(rs.getInt(8));
	    		funcionario.setUsuario(rs.getString(9));
	    		funcionario.setSalario(rs.getBigDecimal(10));
	    		
	    		funcionarios.add(funcionario);
	    		
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return funcionarios;
    }
    
    public Funcionario findById(int id) {
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
     			   	   + " f.salario "
     			   	   + " FROM funcionario f "
     			   	   + " INNER JOIN pessoa p on p.id = f.id "
     			   	   + " WHERE f.id = ? AND p.tipo = 2 ";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	Funcionario funcionario = new Funcionario();
	    	if(rs != null) {
		    	rs.next();
		    	
		    	funcionario.setId(rs.getInt(1));
	    		funcionario.setNome(rs.getString(2));
	    		funcionario.setCpf(rs.getString(3));
	    		funcionario.setEndereco(rs.getString(4));
	    		funcionario.setTelefone(rs.getString(5));
	    		funcionario.setDataNascimento(rs.getString(6));
	    		funcionario.setTipoPessoa(rs.getInt(7));
	    		funcionario.setCodigo(rs.getInt(8));
	    		funcionario.setUsuario(rs.getString(9));
	    		funcionario.setSalario(rs.getBigDecimal(10));		    	
		    	
	    	}
	    	
	    	return funcionario;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
}
