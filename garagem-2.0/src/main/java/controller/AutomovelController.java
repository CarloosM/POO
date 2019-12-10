package controller;

import java.util.List;

import entity.Automovel;
import model.AutomovelModel;

public class AutomovelController {

	AutomovelModel automovelModel;

    public AutomovelController() {
        this.automovelModel = new AutomovelModel();
    }

    public boolean save(Automovel marca){
        return this.automovelModel.save(marca);
    }

    public boolean update(Automovel marca){
        return this.automovelModel.update(marca);
    }

    public boolean delete(int id){
        return this.automovelModel.delete(id);
    }

    public List<Automovel> findAll(){
        return this.automovelModel.findAll();
    }

    public Automovel findById(int id){
        return this.automovelModel.findById(id);
    }
    
    public List<Automovel> findByModelo(int id){
        return this.automovelModel.findByModelo(id);
    }	
}
