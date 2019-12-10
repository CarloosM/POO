package controller;

import java.util.List;

import entity.Cliente;
import model.ClienteModel;

public class ClienteController {

	ClienteModel clienteModel;

    public ClienteController() {
        this.clienteModel = new ClienteModel();
    }

    public boolean save(Cliente cliente){
        return this.clienteModel.save(cliente);
    }

    public boolean update(Cliente cliente){
        return this.clienteModel.update(cliente);
    }

    public boolean delete(int id){
        return this.clienteModel.delete(id);
    }

    public List<Cliente> findAll(){
        return this.clienteModel.findAll();
    }

    public Cliente findById(int id){
        return this.clienteModel.findById(id);
    }
}
