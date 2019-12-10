package view;

import java.util.Scanner;

public class MenuPrincipal {
	
	Scanner sc = new Scanner(System.in);
	
	public void menuPrincipal() {
        System.out.println("#Menu Principal");
        System.out.println("01- Funcionario");
        System.out.println("02- Gerente");
        System.out.println("03- Badeco");
        System.out.println("04- Cliente");
        System.out.println("05- Marca");
        System.out.println("06- Modelo");
        System.out.println("07- Automovel");
        System.out.println("08- Vendas");
        System.out.println("00- Sair");

        int op = sc.nextInt();

        switch (op) {
        
            case 1 :
            	FuncionarioView funcionarioView = new FuncionarioView();
            	funcionarioView.menuFuncionario();
            	break;
            	
            	
            case 2:
            	GerenteView gerenteView = new GerenteView();
            	gerenteView.menuGerente();
            	
            case 3:
            	BadecoView badecoView = new BadecoView();
            	badecoView.menuBadeco();
        	case 4:
        		ClienteView clienteView = new ClienteView();
        		clienteView.menuCliente();
        		break;
            case 5:
                MarcaView marcaView = new MarcaView();
                marcaView.menuMarca();
                break;
            case 6:
            	ModeloView modeloView = new ModeloView();
            	modeloView.menuModelo();
                break;
            case 7:
            	AutomovelView automovelView = new AutomovelView();
            	automovelView.menuAutomovel();
            	break;
            	
            case 8: 
            	VendaView vendaView = new VendaView();
            	vendaView.menuVenda();
            	break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                menuPrincipal();
                break;
        }

    }

}
