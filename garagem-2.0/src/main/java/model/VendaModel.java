package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.SQLConnection;
import entity.Venda;

public class VendaModel {

	Connection connection;
	private AutomovelModel automovelModel;
	private ClienteModel clienteModel;
	private FuncionarioModel funcionarioModel;
	
	public VendaModel() {
        this.connection = SQLConnection.getConnection();
        this.automovelModel = new AutomovelModel();
        this.clienteModel = new ClienteModel();
        this.funcionarioModel = new FuncionarioModel();
    }
	
	public boolean save(Venda venda) {
		String sql = "INSERT INTO venda (cod_venda, automovel, valor_venda, cliente, funcionario, dt_venda, comissao_venda) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setInt(1, venda.getCodigoVenda());
            ps.setInt(2, venda.getAutomovel().getId());
            ps.setBigDecimal(3, venda.getValorVenda());
            ps.setInt(4, venda.getCliente().getId());
            ps.setInt(5, venda.getFuncionario().getId());
            ps.setString(6, venda.getDataVenda());
            ps.setBigDecimal(7, venda.getComissaoVenda());
            
            ps.executeUpdate();        	
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
	}
	
	public List<Venda> findAll(){
    	String sql = " SELECT v.cod_venda,"
    			   + " v.automovel, "
    			   + " v.cliente, "
    			   + " v.funcionario, "
    			   + " v.dt_venda, "
    			   + " v.comissao_venda, "
    			   + " v.valor_venda "
    			   + " FROM venda v ";
    	PreparedStatement ps = null;
    	List<Venda> vendas = new ArrayList<Venda>();
    	
    	try {
			ps = connection.prepareStatement(sql);
    	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		Venda venda = new Venda();
	    		venda.setCodigoVenda(rs.getInt(1));
	    		Integer idAutomovel = rs.getInt(2);
	    		Integer idCliente = rs.getInt(3);
	    		Integer idFuncionario = rs.getInt(4);
	    		venda.setAutomovel(automovelModel.findById(idAutomovel));
	    		venda.setCliente(clienteModel.findById(idCliente));
	    		venda.setFuncionario(funcionarioModel.findById(idFuncionario));
	    		venda.setDataVenda(rs.getString(5));
	    		venda.setComissaoVenda(rs.getBigDecimal(6));
	    		venda.setValorVenda(rs.getBigDecimal(7));	    		
	    		
	    		vendas.add(venda);
	    		
	    	}
    
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
        return vendas;
    }
	
	public Venda findById(int id) {
    	try {
    		String sql = " SELECT v.cod_venda,"
     			   + " v.automovel, "
     			   + " v.cliente, "
     			   + " v.funcionario, "
     			   + " v.dt_venda, "
     			   + " v.comissao_venda, "
     			   + " v.valor_venda "
     			   + " FROM venda v "
     			   + " WHERE v.id = ? ";
        	PreparedStatement ps = null;
        	
        	ps = connection.prepareStatement(sql);
        	ps.setInt(1, id);
        	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	Venda venda = new Venda();
	    	if(rs != null) {
		    	rs.next();
	    		
	    		venda.setCodigoVenda(rs.getInt(1));
	    		Integer idAutomovel = rs.getInt(2);
	    		Integer idCliente = rs.getInt(3);
	    		Integer idFuncionario = rs.getInt(4);
	    		venda.setAutomovel(automovelModel.findById(idAutomovel));
	    		venda.setCliente(clienteModel.findById(idCliente));
	    		venda.setFuncionario(funcionarioModel.findById(idFuncionario));
	    		venda.setDataVenda(rs.getString(5));
	    		venda.setComissaoVenda(rs.getBigDecimal(6));
	    		venda.setValorVenda(rs.getBigDecimal(7));		    	
		    	
	    	}
	    	
	    	return venda;
        } catch (SQLException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
}
