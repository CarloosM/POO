package controller;

import java.util.List;

import entity.Modelo;
import model.ModeloModel;

public class ModeloController {
	
	ModeloModel modeloModel;

    public ModeloController() {
        this.modeloModel = new ModeloModel();
    }

    public boolean save(Modelo modelo){
        return this.modeloModel.save(modelo);
    }

    public boolean update(Modelo modelo){
        return this.modeloModel.update(modelo);
    }

    public boolean delete(int id){
        return this.modeloModel.delete(id);
    }

    public List<Modelo> findAll(){
        return this.modeloModel.findAll();
    }

    public Modelo findById(int id){
        return this.modeloModel.findById(id);
    }
    
    public List<Modelo> findByMarca(int id){
        return this.modeloModel.findByMarca(id);
    }
}
