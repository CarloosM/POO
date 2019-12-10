package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Cliente;
import entity.enums.TipoPessoa;

public class ClienteModel {

	Connection connection;
	private PessoaModel pessoaModel;
	
    public ClienteModel() {
        this.connection = SQLConnection.getConnection();
        this.pessoaModel = new PessoaModel();
    }
    
    public boolean save(Cliente cliente) {
    	String sql = "INSERT INTO cliente (id, codigo) VALUES (?, ?)";

        try {
        	cliente.setTipoPessoa(TipoPessoa.CLIENTE.getId());
            Integer idPessoa = pessoaModel.save(cliente);
            
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ps.setInt(2, cliente.getCodigo());
            
            ps.executeUpdate();        	
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
	
    public boolean update(Cliente cliente){
    	String sql = "UPDATE cliente SET codigo = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
        	pessoaModel.update(cliente);
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, cliente.getCodigo());
        	ps.setInt(2, cliente.getId());
        	
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean delete(int id){
        String sql = "DELETE FROM cliente WHERE id = ?";
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
    
    public List<Cliente> findAll(){
    	String sql = " SELECT p.id, "
    			   + " p.nome, "
    			   + " p.cpf, "
    			   + " p.endereco, "
    			   + " p.telefone, "
    			   + " p.data_nascimento, "
    			   + " p.tipo, "
    			   + " c.codigo "
    			   + " FROM cliente c "
    			   + " INNER JOIN pessoa p on p.id = c.id "
    			   + " WHERE p.tipo = 1 ";
    	PreparedStatement ps = null;
    	List<Cliente> clientes = new ArrayList<Cliente>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Cliente cliente = new Cliente();
	    		
	    		cliente.setId(rs.getInt(1));
	    		cliente.setNome(rs.getString(2));
	    		cliente.setCpf(rs.getString(3));
	    		cliente.setEndereco(rs.getString(4));
	    		cliente.setTelefone(rs.getString(5));
	    		cliente.setDataNascimento(rs.getString(6));
	    		cliente.setTipoPessoa(rs.getInt(7));
	    		cliente.setCodigo(rs.getInt(8));
	    		
	    		clientes.add(cliente);
	    		
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return clientes;
    }
    
    public Cliente findById(int id) {
    	try {
    		String sql = " SELECT p.id, "
     			   + " p.nome, "
     			   + " p.cpf, "
     			   + " p.endereco, "
     			   + " p.telefone, "
     			   + " p.data_nascimento, "
     			   + " p.tipo, "
     			   + " c.codigo "
     			   + " FROM cliente c "
     			   + " INNER JOIN pessoa p on p.id = c.id "
     			   + " WHERE p.id = ? AND p.tipo = 1 ";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	Cliente cliente = new Cliente();
	    	if(rs != null) {
		    	rs.next();
		    	
		    	cliente.setId(rs.getInt(1));
	    		cliente.setNome(rs.getString(2));
	    		cliente.setCpf(rs.getString(3));
	    		cliente.setEndereco(rs.getString(4));
	    		cliente.setTelefone(rs.getString(5));
	    		cliente.setDataNascimento(rs.getString(6));
	    		cliente.setTipoPessoa(rs.getInt(7));
	    		cliente.setCodigo(rs.getInt(8));		    	
		    	
	    	}
	    	
	    	return cliente;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
}
