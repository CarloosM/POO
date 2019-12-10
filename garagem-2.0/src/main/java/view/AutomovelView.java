package view;

import java.time.Year;
import java.util.List;

import controller.AutomovelController;
import controller.ModeloController;
import entity.Automovel;
import entity.Modelo;

public class AutomovelView extends MenuPrincipal {
	
	AutomovelController automovelController;
	ModeloController modeloController;
	
	/**
	 * Construtor com sobrecarga das instancias @AutomovelControler e @ModeloControler
	 * 
	 */
	public AutomovelView() {
		this.automovelController = new AutomovelController();
		this.modeloController = new ModeloController();
	}
	
	/**
	 * Chamada do SubMenu
	 * 
	 */
	public void menuAutomovel() {
		System.out.println("#Menu Automovel");
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
	            menuAutomovel();
	            break;
        }
        menuAutomovel();
	}
	
	private void findAll(){
        System.out.println("Lista de Automóveis");
        List<Automovel> automoveis = automovelController.findAll();
        
        if(automoveis == null || automoveis.isEmpty()) {
        	System.out.println("Nenhum resultado encontrado");
        }
        
        for(Automovel automovel : automoveis) {
        	System.out.println("==========");
        	System.out.println("ID: " + automovel.getId());
        	System.out.println("Modelo/Marca: " + automovel.getModelo().getNome() + " / " + automovel.getModelo().getMarca().getNome());
        	System.out.println("Cor: " + automovel.getCor());
        	System.out.println("Ano do Modelo: " + automovel.getAnoModelo());
        	System.out.println("Km: " + automovel.getKm());
        }
        System.out.println("=======");
    }

	private void save(){
    	Automovel automovel = new Automovel();
    	System.out.println("> Inserir Automóvel");
    	sc.nextLine();
    	System.out.println("> Informe a cor: ");
    	automovel.setCor(sc.nextLine());
    	
		System.out.println("> Informe o ano de fabricação: ");
		automovel.setAnoFabricacao(Year.of(sc.nextInt()));
		
		System.out.println("> Informe o ano do modelo: ");
		automovel.setAnoModelo(Year.of(sc.nextInt()));
		
		System.out.println("> Informe o chassi: ");
		automovel.setChassi(sc.nextLine());
		
		sc.nextLine();
		System.out.println("> Informe a placa: ");
		automovel.setPlaca(sc.nextLine());
		
		System.out.println("> Informe o km: ");
		automovel.setKm(sc.nextLine());
		
		System.out.println("> Informe o valor: ");
		automovel.setValor(sc.nextBigDecimal());
		
		System.out.println("> Informe o id da Modelo que esse Automóvel irá se referênciar");
		int idModelo = sc.nextInt();
		
		Modelo modelo = modeloController.findById(idModelo);
		
		while(modelo == null) {
			System.out.println("> Id informado não encontrado");
			System.out.println("> Informe o id da Modelo que esse Automóvel irá referênciar");
			idModelo = sc.nextInt();
			modelo = modeloController.findById(idModelo);
		}
		
		automovel.setModelo(modelo);

        if(automovelController.save(automovel)){
        	System.out.println("Automóvel inserido com sucesso!");
        } else {
        	System.out.println("Erro ao inserir Automóvel!");
        }
    }
	
	private void update() {
		System.out.println("Informe um id do automóvel que irá ser alterado:");
    	int id = sc.nextInt();
    	
    	Automovel automovel = automovelController.findById(id);
    	
    	if(automovel == null) {
    		System.out.println("Automóvel com o id informado não encontrado.");
    	}
    	
    	System.out.println("=== Automóvel ===");
    	sc.nextLine();
    	System.out.println("> Informe a cor: ");
    	automovel.setCor(sc.nextLine());
    	
		System.out.println("> Informe o novo ano de fabricação: ");
		automovel.setAnoFabricacao(Year.of(sc.nextInt()));
		
		System.out.println("> Informe o novo ano do modelo: ");
		automovel.setAnoModelo(Year.of(sc.nextInt()));
		
		System.out.println("> Informe o novo chassi: ");
		automovel.setChassi(sc.nextLine());
		
		sc.nextLine();
		System.out.println("> Informe a novo placa: ");
		automovel.setPlaca(sc.nextLine());
		
		System.out.println("> Informe o novo km: ");
		automovel.setKm(sc.nextLine());
		
		System.out.println("> Informe o novo valor: ");
		automovel.setValor(sc.nextBigDecimal());
		
		if(automovelController.update(automovel)) {
			System.out.println("Automóvel alterado com sucesso!");
		} else {
			System.out.println("Erro ao alterar Automóvel!");
        }
	}
	
	private void findById(){
     	System.out.println("Informe um id do automóvel que irá ser buscado:");
     	int id = sc.nextInt();
     	Automovel automovel = automovelController.findById(id);
     	
     	if(automovel == null) {
     		System.out.println("Automóvel com o id informado não encontrado.");
     	}
     	
     	System.out.println("=======");
     	System.out.println("ID: " + automovel.getId());
    	System.out.println("Modelo/Marca: " + automovel.getModelo().getNome() + " / " + automovel.getModelo().getMarca().getNome());
    	System.out.println("Cor: " + automovel.getCor());
    	System.out.println("Ano de Fabricação: " + automovel.getAnoFabricacao());
    	System.out.println("Ano do Modelo: " + automovel.getAnoModelo());
    	System.out.println("Chassi: " + automovel.getChassi());
    	System.out.println("Placa: " + automovel.getPlaca());
    	System.out.println("Km: " + automovel.getKm());
    	System.out.println("Valor: " + automovel.getValor());
    	System.out.println("=======");
     	
    }
	
	private void delete(){

        System.out.println("Informe o id do automóvel que será deletado");
        int id = sc.nextInt();
        if(automovelController.delete(id)) {
        	System.out.println("Automóvel deletada com sucesso!");
        } else {
        	System.out.println("Erro ao deletar Automóvel");
        }
    }
}
