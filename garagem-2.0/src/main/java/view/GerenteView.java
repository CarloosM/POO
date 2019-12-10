package view;

import java.util.List;

import controller.GerenteController;
import entity.Gerente;

public class GerenteView extends MenuPrincipal{



	GerenteController gerenteController;

	public GerenteView() {
		this.gerenteController = new GerenteController();
	}

	public void menuGerente() {

		System.out.println("#Menu Gerente");
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
			menuGerente();
			break;
		}
		menuGerente();
	}

	private void save() {
		Gerente gerente = new Gerente();
		System.out.println("> Inserir Gerente");
		sc.nextLine();
		System.out.println("> Informe o nome: ");
		gerente.setNome(sc.nextLine());

		System.out.println("> Informe o cpf");
		gerente.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço");
		gerente.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone");
		gerente.setTelefone(sc.nextLine());

		System.out.println("> Informe a data de nascimento");
		gerente.setDataNascimento(sc.nextLine());

		System.out.println("> Informe o departamento: ");
		gerente.setDepartamento(sc.nextLine());
		
		System.out.println("> Informe o usuario: ");
		gerente.setUsuario(sc.nextLine());
		
		System.out.println("> Informe a senha: ");
		gerente.setSenha(sc.nextLine());
		
		System.out.println("> Informe o salário: ");
		gerente.setSalario(sc.nextBigDecimal());
		
		System.out.println("> Informe o código: ");
		gerente.setCodigo(sc.nextInt());
		

		if (gerenteController.save(gerente)) {
			System.out.println("Gerente inserido com sucesso!");
		} else {
			System.out.println("Erro ao inserir Gerente!");
		}
	}

	private void findAll() {
		System.out.println("Lista de Gerentes");
		List<Gerente> gerentes = gerenteController.findAll();

		if (gerentes == null || gerentes.isEmpty()) {
			System.out.println("Nenhum resultado encontrado");
		}

		for (Gerente gerente : gerentes) {
			System.out.println("==========");
			System.out.println("Nome: " + gerente.getNome());
			System.out.println("Cpf: " + gerente.getCpf());
			System.out.println("Endereco: " + gerente.getEndereco());
			System.out.println("Telefone: " + gerente.getTelefone());
			System.out.println("Data de Nascimento " + gerente.getDataNascimento());
			System.out.println("Departamento: " + gerente.getDepartamento());
			System.out.println("Usuário: " + gerente.getUsuario());
			System.out.println("Senha: " + gerente.getSenha());
			System.out.println("Salário: " + gerente.getSalario());
			System.out.println("Código: " + gerente.getCodigo());
		}
		System.out.println("=======");
	}

	private void findById() {
		System.out.println("Informe um id do Gerente que irá ser buscado:");
		int id = sc.nextInt();
		Gerente gerente = gerenteController.findById(id);

		if (gerente == null) {
			System.out.println("Gerente com o id informado não encontrado.");
		}

		System.out.println("=======");
		System.out.println("Nome: " + gerente.getNome());
		System.out.println("Cpf: " + gerente.getCpf());
		System.out.println("Endereço: " + gerente.getEndereco());
		System.out.println("Telefone: " + gerente.getTelefone());
		System.out.println("Data de Nascimento " + gerente.getDataNascimento());
		System.out.println("Departamento " + gerente.getDepartamento());
		System.out.println("Usuário: " + gerente.getUsuario());
		System.out.println("Senha: " + gerente.getSenha());
		System.out.println("Salário: " + gerente.getSalario());
		System.out.println("Código: " + gerente.getCodigo());
		System.out.println("=======");

	}

	private void delete() {

		System.out.println("Informe o id do Gerente que será deletado");
		int id = sc.nextInt();
		if (gerenteController.delete(id)) {
			System.out.println("Gerente deletada com sucesso!");
		} else {
			System.out.println("Erro ao deletar Gerente");
		}
	}

	private void update() {
		System.out.println("Informe um id do gerente que irá ser alterado:");
		int id = sc.nextInt();

		Gerente gerente = gerenteController.findById(id);

		if (gerente == null) {
			System.out.println("Gerente com o id informado não encontrado.");
		}

		System.out.println("=== Gerente ===");
		sc.nextLine();
		System.out.println("> Informe o nome: ");
		gerente.setNome(sc.nextLine());

		System.out.println("> Informe o Cpf ");
		gerente.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço: ");
		gerente.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone: ");
		gerente.setTelefone(sc.nextLine());

		sc.nextLine();
		System.out.println("> Informe a Data de Nascimento: ");
		gerente.setDataNascimento(sc.nextLine());
		
		System.out.println("> Informe o Departamento: ");
		gerente.setDepartamento(sc.nextLine());
		
		System.out.println("> Informe o usuario: ");
		gerente.setUsuario(sc.nextLine());
		
		System.out.println("> Informe a senha: ");
		gerente.setSenha(sc.nextLine());
		
		System.out.println("> Informe o salário: ");
		gerente.setSalario(sc.nextBigDecimal());
		
		System.out.println("> Informe o código: ");
		gerente.setCodigo(sc.nextInt());

		if (gerenteController.update(gerente)) {
			System.out.println("Gerente alterado com sucesso!");
		} else {
			System.out.println("Erro ao alterar Gerente!");
		}
	}

}
