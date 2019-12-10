package controller;

import java.util.List;

import entity.Badeco;
import model.BadecoModel;

public class BadecoController {
	BadecoModel badecoModel;

    public BadecoController() {
        this.badecoModel = new BadecoModel();
    }

    public boolean save(Badeco badeco){
    	return this.badecoModel.save(badeco);   
    }

    public boolean update(Badeco badeco){
        return this.badecoModel.update(badeco);
    }

    public boolean delete(int id){
        return this.badecoModel.delete(id);
    }

    public List<Badeco> findAll(){
        return this.badecoModel.findAll();
    }

    public Badeco findById(int id){
        return this.badecoModel.findById(id);
    }
}
