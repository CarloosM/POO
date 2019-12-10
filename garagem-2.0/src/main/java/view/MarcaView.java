package view;

import java.util.List;

import controller.MarcaController;
import controller.ModeloController;
import entity.Marca;
import entity.Modelo;

public class MarcaView extends MenuPrincipal {

    MarcaController marcaController;
    ModeloController modeloController;
    
    public MarcaView() {
        this.marcaController = new MarcaController();
        this.modeloController = new ModeloController();
    }

    public void menuMarca(){
    	
    	System.out.println("#Menu Marca");
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
                menuMarca();
                break;
        }
        menuMarca();
    }


    private void save(){
    	Marca marca = new Marca();
    	System.out.println("> Inserir Marca");
    	sc.nextLine();
    	System.out.println("> Informe o nome da marca: ");
		marca.setNome(sc.nextLine());

        if(marcaController.save(marca)){
        	System.out.println("Marca inserida com sucesso!");
        } else {
        	System.out.println("Erro ao inserir Marca!");
        }
    }

    private void update(){
    	System.out.println("Informe um id da marca que irá ser alterada:");
    	int id = sc.nextInt();
    	
    	Marca marca = marcaController.findById(id);
    	
    	if(marca == null) {
    		System.out.println("Marca com o id informado não encontrado.");
    		menuMarca();
    	} 
    	
		System.out.println("Marca");
		System.out.println("ID: " + marca.getId());
		System.out.println("Nome: " + marca.getNome());
		
		sc.nextLine();
		System.out.println("Digite o novo nome para Marca");
		marca.setNome(sc.nextLine());
    	
		if(marcaController.update(marca)) {
			System.out.println("Marca alterada com sucesso!");
		} else {
			System.out.println("Erro ao alterar Marca!");
        }
    }

    private void delete(){

        System.out.println("Informe o id da marca que será deletada");
        int idMarca = sc.nextInt();
        
        List<Modelo> modelos = modeloController.findByMarca(idMarca);
        
        
        if(modelos == null || modelos.isEmpty()) {
		    if(marcaController.delete(idMarca)) {
		    	System.out.println("Marca deletada com sucesso!");
		    }
        }else {
        	System.out.println("Erro ao deletar Marca");
        }
    }

    private void findAll(){
        System.out.println("Lista de Marcas");
        List<Marca> marcas = marcaController.findAll();
        
        if(marcas == null || marcas.isEmpty()) {
        	System.out.println("Nenhum resultado encontrado");
        }
        
        for(Marca marca : marcas) {
        	System.out.println("==========");
        	System.out.println("ID: " + marca.getId());
        	System.out.println("Nome: " + marca.getNome());
        }
    }

    private void findById(){
     	System.out.println("Informe um id da marca que irá ser buscada:");
     	int id = sc.nextInt();
     	Marca marca = marcaController.findById(id);
     	
     	if(marca == null) {
     		System.out.println("Marca não encontrada");
     		menuMarca();
     	}
     	
     	System.out.println("=== Marca ===");
     	System.out.println("ID: " + marca.getId());
     	System.out.println("Nome: " + marca.getNome());
     	
    }
    
}
