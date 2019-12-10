package controller;

import java.util.List;

import entity.Funcionario;
import entity.enums.TipoPessoa;
import model.FuncionarioModel;

public class FuncionarioController {
	FuncionarioModel funcionarioModel;

    public FuncionarioController() {
        this.funcionarioModel = new FuncionarioModel();
    }

    public boolean save(Funcionario funcionario){
    	Integer salvou = this.funcionarioModel.save(funcionario, TipoPessoa.FUNCIONARIO);   
    	return salvou>0;
    }

    public boolean update(Funcionario funcionario){
        return this.funcionarioModel.update(funcionario);
    }

    public boolean delete(int id){
        return this.funcionarioModel.delete(id);
    }

    public List<Funcionario> findAll(){
        return this.funcionarioModel.findAll();
    }

    public Funcionario findById(int id){
        return this.funcionarioModel.findById(id);
    }
}
