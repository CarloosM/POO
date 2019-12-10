package controller;

import java.util.List;

import entity.Venda;
import model.VendaModel;

public class VendaController {
	VendaModel vendaModel;

    public VendaController() {
        this.vendaModel = new VendaModel();
    }

    public boolean save(Venda venda){
    	return this.vendaModel.save(venda);   
    }

    public List<Venda> findAll(){
        return this.vendaModel.findAll();
    }

    public Venda findById(int id){
        return this.vendaModel.findById(id);
    }
}
