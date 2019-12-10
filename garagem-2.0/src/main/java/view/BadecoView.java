package view;

import java.util.List;
import controller.BadecoController;
import entity.Badeco;

public class BadecoView  extends MenuPrincipal{


	BadecoController badecoController;

	public BadecoView() {
		this.badecoController = new BadecoController();
	}

	public void menuBadeco() {

		System.out.println("#Menu Badeco");
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
			menuBadeco();
			break;
		}
		menuBadeco();
	}

	private void save() {
		Badeco badeco = new Badeco();
		System.out.println("> Inserir Badeco");
		sc.nextLine();
		System.out.println("> Informe o nome: ");
		badeco.setNome(sc.nextLine());

		System.out.println("> Informe o cpf");
		badeco.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço");
		badeco.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone");
		badeco.setTelefone(sc.nextLine());

		System.out.println("> Informe a data de nascimento");
		badeco.setDataNascimento(sc.nextLine());

		System.out.println("> Informe a função: ");
		badeco.setFuncao(sc.nextLine());
		
		System.out.println("> Informe o usuario: ");
		badeco.setUsuario(sc.nextLine());
		
		System.out.println("> Informe a senha: ");
		badeco.setSenha(sc.nextLine());
		
		System.out.println("> Informe o salário: ");
		badeco.setSalario(sc.nextBigDecimal());
		
		System.out.println("> Informe o código: ");
		badeco.setCodigo(sc.nextInt());

		if (badecoController.save(badeco)) {
			System.out.println("Badeco inserido com sucesso!");
		} else {
			System.out.println("Erro ao inserir Badeco!");
		}
	}

	private void findAll() {
		System.out.println("Lista de Badecos");
		List<Badeco> badecos = badecoController.findAll();

		if (badecos == null || badecos.isEmpty()) {
			System.out.println("Nenhum resultado encontrado");
		}

		for (Badeco badeco : badecos) {
			System.out.println("==========");
			System.out.println("Nome: " + badeco.getNome());
			System.out.println("Cpf: " + badeco.getCpf());
			System.out.println("Endereco: " + badeco.getEndereco());
			System.out.println("Telefone: " + badeco.getTelefone());
			System.out.println("Data de Nascimento " + badeco.getDataNascimento());
			System.out.println("Função: " + badeco.getFuncao());
			System.out.println("Usuário: " + badeco.getUsuario());
			System.out.println("Senha: " + badeco.getSenha());
			System.out.println("Salário: " + badeco.getSalario());
			System.out.println("Código: " + badeco.getCodigo());
		
		}
		System.out.println("=======");
	}

	private void findById() {
		System.out.println("Informe um id do Badeco que irá ser buscado:");
		int id = sc.nextInt();
		Badeco badeco = badecoController.findById(id);

		if (badeco == null) {
			System.out.println("Badeco com o id informado não encontrado.");
		}

		System.out.println("=======");
		System.out.println("Nome: " + badeco.getNome());
		System.out.println("Cpf: " + badeco.getCpf());
		System.out.println("Endereço: " + badeco.getEndereco());
		System.out.println("Telefone: " + badeco.getTelefone());
		System.out.println("Data de Nascimento: " + badeco.getDataNascimento());
		System.out.println("Função: " + badeco.getFuncao());
		System.out.println("Usuário: " + badeco.getUsuario());
		System.out.println("Senha: " + badeco.getSenha());
		System.out.println("Salário: " + badeco.getSalario());
		System.out.println("Código: " + badeco.getCodigo());
		System.out.println("=======");

	}

	private void delete() {

		System.out.println("Informe o id do Badeco que será deletado");
		int id = sc.nextInt();
		if (badecoController.delete(id)) {
			System.out.println("Badeco deletada com sucesso!");
		} else {
			System.out.println("Erro ao deletar Badeco");
		}
	}

	private void update() {
		System.out.println("Informe um id do badeco que irá ser alterado:");
		int id = sc.nextInt();

		Badeco badeco = badecoController.findById(id);

		if (badeco == null) {
			System.out.println("Badeco com o id informado não encontrado.");
		}

		System.out.println("=== Badeco ===");
		sc.nextLine();
		System.out.println("> Informe o nome: ");
		badeco.setNome(sc.nextLine());

		System.out.println("> Informe o Cpf ");
		badeco.setCpf(sc.nextLine());

		System.out.println("> Informe o endereço: ");
		badeco.setEndereco(sc.nextLine());

		System.out.println("> Informe o telefone: ");
		badeco.setTelefone(sc.nextLine());

		sc.nextLine();
		System.out.println("> Informe a Data de Nascimento: ");
		badeco.setDataNascimento(sc.nextLine());
		
		System.out.println("> Informe a função: ");
		badeco.setFuncao(sc.nextLine());
		
		System.out.println("> Informe o usuario: ");
		badeco.setUsuario(sc.nextLine());
		
		System.out.println("> Informe a senha: ");
		badeco.setSenha(sc.nextLine());
		
		System.out.println("> Informe o salário: ");
		badeco.setSalario(sc.nextBigDecimal());
		
		System.out.println("> Informe o código: ");
		badeco.setCodigo(sc.nextInt());
		
		if (badecoController.update(badeco)) {
			System.out.println("Badeco alterado com sucesso!");
		} else {
			System.out.println("Erro ao alterar Badeco!");
		}
	}

}
