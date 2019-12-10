package view;

import java.util.List;
import controller.FuncionarioController;
import entity.Funcionario;

public class FuncionarioView extends MenuPrincipal {

	FuncionarioController funcionarioController;

	public FuncionarioView() {
		this.funcionarioController = new FuncionarioController();
	}

	public void menuFuncionario() {

		System.out.println("#Menu Funcionario");
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
			menuFuncionario();
			break;
		}
		menuFuncionario();
	}

	private void save() {
		Funcionario funcionario = new Funcionario();
		System.out.println("> Inserir Funcionário");
		sc.nextLine();
		
		System.out.println("> Informe o nome: ");
		funcionario.setNome(sc.nextLine());

		System.out.println("> Informe o cpf: ");
		funcionario.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço: ");
		funcionario.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone: ");
		funcionario.setTelefone(sc.nextLine());

		System.out.println("> Informe a data de nascimento: ");
		funcionario.setDataNascimento(sc.nextLine());

		System.out.println("> Informe o código: ");
		funcionario.setCodigo(sc.nextInt());
		
		System.out.println("> Informe o usuário: ");
		funcionario.setUsuario(sc.nextLine());
		
		System.out.println("> Informe a senha: ");
		funcionario.setSenha(sc.nextLine());
		
		System.out.println("> Informe o salário: ");
		funcionario.setSalario(sc.nextBigDecimal());
		
		
		if (funcionarioController.save(funcionario)) {
			System.out.println("Funcionário inserido com sucesso!");
		} else {
			System.out.println("Erro ao inserir Funcionário!");
		}
	}

	private void findAll() {
		System.out.println("Lista de Funcionários");
		List<Funcionario> funcionarios = funcionarioController.findAll();

		if (funcionarios == null ||funcionarios.isEmpty()) {
			System.out.println("Nenhum resultado encontrado");
		}

		for (Funcionario funcionario : funcionarios) {
			System.out.println("==========");
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Cpf: " + funcionario.getCpf());
			System.out.println("Endereco: " + funcionario.getEndereco());
			System.out.println("Telefone: " + funcionario.getTelefone());
			System.out.println("Data de Nascimento " + funcionario.getDataNascimento());
			System.out.println("Código: " + funcionario.getCodigo());
			System.out.println("Usuário: " + funcionario.getUsuario());
			System.out.println("Senha: " + funcionario.getSenha());
			System.out.println("Salário: " + funcionario.getSalario());
		}
		System.out.println("=======");
	}

	private void findById() {
		System.out.println("Informe um id do Funcionário que irá ser buscado:");
		int id = sc.nextInt();
		Funcionario funcionario = funcionarioController.findById(id);

		if (funcionario == null) {
			System.out.println("funcionário com o id informado não encontrado.");
		}

		System.out.println("=======");
		System.out.println("Nome: " + funcionario.getNome());
		System.out.println("Cpf: " + funcionario.getCpf());
		System.out.println("Endereço: " + funcionario.getEndereco());
		System.out.println("Telefone: " + funcionario.getTelefone());
		System.out.println("Data de Nascimento " + funcionario.getDataNascimento());
		System.out.println("Codigo " + funcionario.getCodigo());
		System.out.println("Usuário:  " + funcionario.getUsuario());
		System.out.println("Senha:  " + funcionario.getSenha());
		System.out.println("Salário:  " + funcionario.getSalario());
		System.out.println("=======");

	}

	private void delete() {

		System.out.println("Informe o id do Funcionário que será deletado");
		int id = sc.nextInt();
		if (funcionarioController.delete(id)) {
			System.out.println("Funcionário deletada com sucesso!");
		} else {
			System.out.println("Erro ao deletar Funcionário");
		}
	}

	private void update() {
		System.out.println("Informe um id do funcionário que irá ser alterado:");
		int id = sc.nextInt();

		Funcionario funcionario = funcionarioController.findById(id);

		if (funcionario == null) {
			System.out.println("Funcionário com o id informado não encontrado.");
		}

		System.out.println("=== Funcionário ===");
		sc.nextLine();
		System.out.println("> Informe o nome: ");
		funcionario.setNome(sc.nextLine());

		System.out.println("> Informe o Cpf ");
		funcionario.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço: ");
		funcionario.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone: ");
		funcionario.setTelefone(sc.nextLine());

		sc.nextLine();
		System.out.println("> Informe a Data de Nascimento: ");
		funcionario.setDataNascimento(sc.nextLine());
		
		System.out.println("> Informe o usuário: ");
	    funcionario.setUsuario(sc.nextLine());
		
		System.out.println("> Informe a senha: ");
		funcionario.setSenha(sc.nextLine());
		
		System.out.println("> Informe o salário: ");
		funcionario.setSalario(sc.nextBigDecimal());
		

		if (funcionarioController.update(funcionario)) {
			System.out.println("Funcionário alterado com sucesso!");
		} else {
			System.out.println("Erro ao alterar Funcionário!");
		}
	}

	
	
	
	
}
