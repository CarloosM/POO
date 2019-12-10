package view;

import java.util.List;

import controller.AutomovelController;
import controller.MarcaController;
import controller.ModeloController;
import entity.Automovel;
import entity.Marca;
import entity.Modelo;

public class ModeloView extends MenuPrincipal {

	ModeloController modeloController;
	MarcaController marcaController;
	AutomovelController automovelController;

    public ModeloView() {
        this.modeloController = new ModeloController();
        this.marcaController = new MarcaController();
        this.automovelController = new AutomovelController();
    }

    public void menuModelo() {
    	System.out.println("#Menu Modelo");
        System.out.println("01- Listar");
        System.out.println("02- Inserir");
        System.out.println("03- Alterar");
        System.out.println("04- Buscar");
        System.out.println("05- Excluir");
        System.out.println("00- Voltar");
        
        int op = sc.nextInt();
        
        switch (op){
	        case 1:
	            findAll();
	            break;
	        case 2:
	            save();
	            break;
	        case 3:
	            update();
	            break;
	        case 4:
	            findById();
	            break;
	        case 5:
	            delete();
	            break;
	        case 0:
	        	menuPrincipal();
	            break;
	        default:
	            System.out.println("Opção inválida");
	            menuModelo();
	            break;
        }
        menuModelo();
    }
    
    private void save(){
    	Modelo modelo = new Modelo();
    	System.out.println("> Inserir Modelo");
    	sc.nextLine();
    	System.out.println("> Informe o nome do modelo: ");
		modelo.setNome(sc.nextLine());
		System.out.println("> Informe o tipo do modelo: ");
		modelo.setTipo(sc.nextLine());
		System.out.println("> Informe o id da Marca que esse Modelo irá referênciar");
		int idMarca = sc.nextInt();
		Marca marca = marcaController.findById(idMarca);
		
		while(marca == null) {
			System.out.println("> Id informado não encontrado");
			System.out.println("> Informe o id da Marca que esse Modelo irá referênciar");
			idMarca = sc.nextInt();
			marca = marcaController.findById(idMarca);
		}
		
		modelo.setMarca(marca);

        if(modeloController.save(modelo)){
        	System.out.println("Modelo inserido com sucesso!");
        } else {
        	System.out.println("Erro ao inserir Modelo!");
        }
    }
    
    private void findAll(){
        System.out.println("Lista de Modelo");
        List<Modelo> modelos = modeloController.findAll();
        
        if(modelos == null || modelos.isEmpty()) {
        	System.out.println("Nenhum resultado encontrado");
        }
        
        for(Modelo modelo : modelos) {
        	System.out.println("==========");
        	System.out.println("ID: " + modelo.getId());
        	System.out.println("Nome: " + modelo.getNome());
        	System.out.println("Marca: id - " + modelo.getMarca().getId() + ", nome - " + modelo.getMarca().getNome());
        }
        System.out.println("=======");
    }
    
    private void update(){
    	System.out.println("Informe um id do modelo que irá ser alterado:");
    	int id = sc.nextInt();
    	
    	Modelo modelo = modeloController.findById(id);
    	
    	if(modelo == null) {
    		System.out.println("Modelo com o id informado não encontrado.");
    	} 
    	
		System.out.println("=== Modelo ===");
		System.out.println("ID: " + modelo.getId());
		System.out.println("Nome: " + modelo.getNome());
		System.out.println("Tipo: " + modelo.getTipo());
		
		sc.nextLine();
		System.out.println("Informe o novo nome para Modelo");
		modelo.setNome(sc.nextLine());
		System.out.println("Informe o novo tipo para Modelo");
		modelo.setTipo(sc.nextLine());
    	
		if(modeloController.update(modelo)) {
			System.out.println("Modelo alterado com sucesso!");
		} else {
			System.out.println("Erro ao alterar Modelo!");
        }
    }

    private void findById(){
     	System.out.println("Informe um id do modelo que irá ser buscada:");
     	int id = sc.nextInt();
     	Modelo modelo = modeloController.findById(id);
     	
     	if(modelo == null) {
     		System.out.println("Modelo com o id informado não encontrado.");
     	}
     	
     	System.out.println("=======");
     	System.out.println("ID: " + modelo.getId());
    	System.out.println("Nome: " + modelo.getNome());
    	System.out.println("Tipo: " + modelo.getTipo());
    	System.out.println("Marca: id - " + modelo.getMarca().getId() + ", nome - " + modelo.getMarca().getNome());
    	System.out.println("=======");
     	
    }
    
    private void delete(){

        System.out.println("Informe o id da modelo que será deletado");
        int id = sc.nextInt();
        
        List<Automovel> automoveis = automovelController.findByModelo(id);
         
        if(automoveis == null || automoveis.isEmpty()) {
		    if(modeloController.delete(id)) {
		    	System.out.println("Modelo deletada com sucesso!");
		    }
        }else {
        	System.out.println("Erro ao deletar Modelo");
        }
    }
}
