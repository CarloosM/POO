package controller;

import java.util.List;

import entity.Gerente;
import model.GerenteModel;

public class GerenteController {
	GerenteModel gerenteModel;

    public GerenteController() {
        this.gerenteModel = new GerenteModel();
    }

    public boolean save(Gerente gerente){
    	return this.gerenteModel.save(gerente);   
    }

    public boolean update(Gerente gerente){
        return this.gerenteModel.update(gerente);
    }

    public boolean delete(int id){
        return this.gerenteModel.delete(id);
    }

    public List<Gerente> findAll(){
        return this.gerenteModel.findAll();
    }

    public Gerente findById(int id){
        return this.gerenteModel.findById(id);
    }
}
