package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.SQLConnection;
import entity.Pessoa;

public class PessoaModel {

	Connection connection;
	
    public PessoaModel() {
        this.connection = SQLConnection.getConnection();
    }
    
    public Integer save(Pessoa pessoa){
    	String sql = "INSERT INTO pessoa (nome, cpf, endereco, telefone, data_nascimento, tipo) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getCpf());
            ps.setString(3, pessoa.getEndereco());
            ps.setString(4, pessoa.getTelefone());         	
        	ps.setString(5, pessoa.getDataNascimento());
            ps.setInt(6, pessoa.getTipoPessoa());
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery("SELECT MAX(id) from pessoa"); 
            rs.next();
            return rs.getInt(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return 0;
    }
    
    public boolean update(Pessoa pessoa){
    	String sql = "UPDATE pessoa SET nome = ?, cpf = ?, endereco = ?, telefone = ?, data_nascimento = ?, tipo = ? WHERE id = ?";

        try {
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getCpf());
            ps.setString(3, pessoa.getEndereco());
            ps.setString(4, pessoa.getTelefone());         	
        	ps.setString(5, pessoa.getDataNascimento());
            ps.setInt(6, pessoa.getTipoPessoa());
            ps.setInt(7,  pessoa.getId());
            ps.executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
    
    public boolean delete(int id){
        String sql = "DELETE FROM pessoa WHERE id = ?";
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
	
}
