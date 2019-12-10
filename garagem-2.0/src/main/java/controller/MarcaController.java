package controller;

import entity.Marca;
import model.MarcaModel;

import java.util.List;

public class MarcaController {

    MarcaModel marcaModel;

    public MarcaController() {
        this.marcaModel = new MarcaModel();
    }

    public boolean save(Marca marca){
        return this.marcaModel.save(marca);
    }

    public boolean update(Marca marca){
        return this.marcaModel.update(marca);
    }

    public boolean delete(int id){
        return this.marcaModel.delete(id);
    }

    public List<Marca> findAll(){
        return this.marcaModel.findAll();
    }

    public Marca findById(int id){
        return this.marcaModel.findById(id);
    }

}
