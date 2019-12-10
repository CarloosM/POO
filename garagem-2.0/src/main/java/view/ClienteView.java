package view;

import controller.ClienteController;
import entity.Cliente;
import java.util.List;

public class ClienteView extends MenuPrincipal {

	ClienteController clienteController;

	public ClienteView() {
		this.clienteController = new ClienteController();
	}

	public void menuCliente() {

		System.out.println("#Menu Cliente");
		System.out.println("01- Listar");
		System.out.println("02- Inserir");
		System.out.println("03- Alterar");
		System.out.println("04- Buscar");
		System.out.println("05- Excluir");
		System.out.println("00- Voltar");

		int op = sc.nextInt();

		switch (op) {
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
			menuCliente();
			break;
		}
		menuCliente();
	}

	private void save() {
		Cliente cliente = new Cliente();
		System.out.println("> Inserir Cliente");
		sc.nextLine();
		System.out.println("> Informe o nome: ");
		cliente.setNome(sc.nextLine());

		System.out.println("> Informe o cpf");
		cliente.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço");
		cliente.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone");
		cliente.setTelefone(sc.nextLine());

		System.out.println("> Informe a data de nascimento");
		cliente.setDataNascimento(sc.nextLine());

		System.out.println("> Informe o código: ");
		cliente.setCodigo(sc.nextInt());

		if (clienteController.save(cliente)) {
			System.out.println("Cliente inserido com sucesso!");
		} else {
			System.out.println("Erro ao inserir Cliente!");
		}
	}

	private void findAll() {
		System.out.println("Lista de Clientes");
		List<Cliente> clientes = clienteController.findAll();

		if (clientes == null || clientes.isEmpty()) {
			System.out.println("Nenhum resultado encontrado");
		}

		for (Cliente cliente : clientes) {
			System.out.println("==========");
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Cpf: " + cliente.getCpf());
			System.out.println("Endereco: " + cliente.getEndereco());
			System.out.println("Telefone: " + cliente.getTelefone());
			System.out.println("Data de Nascimento " + cliente.getDataNascimento());
			System.out.println("Código: " + cliente.getCodigo());
		}
		System.out.println("=======");
	}

	private void findById() {
		System.out.println("Informe um id do Cliente que irá ser buscado:");
		int id = sc.nextInt();
		Cliente cliente = clienteController.findById(id);

		if (cliente == null) {
			System.out.println("Cliente com o id informado não encontrado.");
		}

		System.out.println("=======");
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("Cpf: " + cliente.getCpf());
		System.out.println("Endereço: " + cliente.getEndereco());
		System.out.println("Telefone: " + cliente.getTelefone());
		System.out.println("Data de Nascimento " + cliente.getDataNascimento());
		System.out.println("Codigo " + cliente.getCodigo());
		System.out.println("=======");

	}

	private void delete() {

		System.out.println("Informe o id do Cliente que será deletado");
		int id = sc.nextInt();
		if (clienteController.delete(id)) {
			System.out.println("Cliente deletada com sucesso!");
		} else {
			System.out.println("Erro ao deletar Cliente");
		}
	}

	private void update() {
		System.out.println("Informe um id do cliente que irá ser alterado:");
		int id = sc.nextInt();

		Cliente cliente = clienteController.findById(id);

		if (cliente == null) {
			System.out.println("Cliente com o id informado não encontrado.");
		}

		System.out.println("=== Cliente ===");
		sc.nextLine();
		System.out.println("> Informe o nome: ");
		cliente.setNome(sc.nextLine());

		System.out.println("> Informe o Cpf ");
		cliente.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço: ");
		cliente.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone: ");
		cliente.setTelefone(sc.nextLine());

		sc.nextLine();
		System.out.println("> Informe a Data de Nascimento: ");
		cliente.setDataNascimento(sc.nextLine());

		if (clienteController.update(cliente)) {
			System.out.println("Cliente alterado com sucesso!");
		} else {
			System.out.println("Erro ao alterar Cliente!");
		}
	}
}
