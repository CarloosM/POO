package view;

import java.util.List;

import controller.VendaController;
import entity.Automovel;
import entity.Cliente;
import entity.Funcionario;
import entity.Venda;
import model.AutomovelModel;
import model.ClienteModel;
import model.FuncionarioModel;

public class VendaView extends MenuPrincipal {
	
	private VendaController vendaController;
	private ClienteModel clienteModel;
	private FuncionarioModel funcionarioModel;
	private AutomovelModel automovelModel;
	
	public VendaView() {
		this.vendaController = new VendaController();
		this.clienteModel = new ClienteModel();
		this.funcionarioModel = new FuncionarioModel();
		this.automovelModel = new AutomovelModel();
	}
	
	public void menuVenda() {
		System.out.println("#Menu Venda");
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
//	            update();
	            break;
	        case 4:
	            findById();
	            break;
	        case 5:
//	            delete();
	            break;
	        case 0:
	        	menuPrincipal();
	            break;
	        default:
	            System.out.println("Opção inválida");
	            menuVenda();
	            break;
        }
        menuVenda();
	}
	
	private void findAll(){
        System.out.println("Lista das Vendas");
        List<Venda> vendas = vendaController.findAll();
        
        if(vendas == null || vendas.isEmpty()) {
        	System.out.println("Nenhum resultado encontrado");
        }
        
        for(Venda venda : vendas) {
        	System.out.println("==========");
        	System.out.println("Código da venda: " + venda.getCodigoVenda());
        	System.out.println("Data da venda: " + venda.getDataVenda());
        	System.out.println("Valor da venda: " + venda.getValorVenda());
        	System.out.println("Valor da comissão: " + venda.getComissaoVenda());
        	System.out.println("ID Automóvel: " + venda.getAutomovel().getId());
        	System.out.println("Modelo Automóvel: " + venda.getAutomovel().getModelo().getNome());
        	System.out.println("Chassi Automóvel: " + venda.getAutomovel().getChassi());
        	System.out.println("ID do Cliente: " + venda.getCliente().getId());
        	System.out.println("Nome do Cliente: " + venda.getCliente().getNome());
        	System.out.println("ID do Funcionario: " + venda.getFuncionario().getId());
        	System.out.println("Nome do Funcionario: " + venda.getFuncionario().getNome());
        }
        System.out.println("=======");
    }
	
	private void save() {
		Venda venda = new Venda();
		
		System.out.println("> Inserir Venda");
    	sc.nextLine();
    	System.out.println("> Informe a data da venda: ");
    	venda.setDataVenda(sc.nextLine());
    	
		System.out.println("> Informe o valor da venda: ");
		venda.setValorVenda(sc.nextBigDecimal());
		
		System.out.println("> Informe o id do cliente: ");
		int idCliente = sc.nextInt();
		
		Cliente cliente = clienteModel.findById(idCliente);
		
		while(cliente == null) {
			System.out.println("> Id informado não encontrado");
			System.out.println("> Informe o id do Cliente.");
			idCliente = sc.nextInt();
			cliente = clienteModel.findById(idCliente);
		}
		
		venda.setCliente(cliente);
		
		System.out.println("> Informe o id do Automóvel: ");
		int idAutomovel = sc.nextInt();
		
		Automovel automovel = automovelModel.findById(idAutomovel);
		
		while(automovel == null) {
			System.out.println("> Id informado não encontrado");
			System.out.println("> Informe o id do Automovel.");
			idAutomovel = sc.nextInt();
			automovel = automovelModel.findById(idAutomovel);
		}
		venda.setAutomovel(automovel);
		
		sc.nextLine();
		System.out.println("> Informe o id do Funcionário: ");
		int idFuncionario = sc.nextInt();
		
		Funcionario funcionario = funcionarioModel.findById(idFuncionario);
		
		while(automovel == null) {
			System.out.println("> Id informado não encontrado");
			System.out.println("> Informe o id do Funcionario.");
			idFuncionario = sc.nextInt();
			funcionario = funcionarioModel.findById(idFuncionario);
		}
		venda.setFuncionario(funcionario);

        if(vendaController.save(venda)){
        	System.out.println("Venda inserida com sucesso!");
        } else {
        	System.out.println("Erro ao inserir Venda!");
        }
	}
	
	private void findById(){
     	System.out.println("Informe um id da venda que irá ser buscada:");
     	int id = sc.nextInt();
     	Venda venda = vendaController.findById(id);
     	
     	if(venda == null) {
     		System.out.println("Venda com o id informado não encontrado.");
     	}
     	
     	System.out.println("=======");
     	System.out.println("Código da venda: " + venda.getCodigoVenda());
    	System.out.println("Automóvel: " + venda.getAutomovel().getModelo().getNome() + " / " + venda.getAutomovel().getModelo().getMarca().getNome());
    	System.out.println("Valor da venda: " + venda.getValorVenda());
    	System.out.println("Cliente: " + venda.getCliente().getNome());
    	System.out.println("Funcionario: " + venda.getFuncionario().getNome());
    	System.out.println("Data da venda: " + venda.getDataVenda());
    	System.out.println("Comissão: " + venda.getComissaoVenda());
    	System.out.println("=======");
     	
    }
}
