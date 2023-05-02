

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.SistemaException;
import service.Service;
import util.Strings;

public class App {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Service service = new Service(sc);

		boolean continua = true;
		do {
			try {
				System.out.println(Strings.MENU_PRINCIPAL.toString());
				int opcaoMenu = sc.nextInt();
				sc.nextLine();
				switch (opcaoMenu) {
				case 1:
					service.criarCadastro();
					break;
				case 2:
					service.listarCadastros();
					break;
				case 3:
					service.pesquisarPorNome();
					break;
				case 4:
					service.atualizarDados(service.receberId());
					break;
				case 0:
					System.out.println(Strings.ENCERRAMENTO.toString());
					continua = false;
					break;
				default:
					System.out.println(Strings.OPCAO_INVALIDA.toString());
					break;
				}
			} catch (SistemaException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(Strings.OPCAO_INVALIDA.toString());
				sc.next();
			} finally {
				Thread.sleep(1500l);
			}
		} while (continua);
	}

}


// import java.awt.Color;
// import java.awt.Font;
// import javax.swing.JFrame;
// import javax.swing.JLabel;

// public class App {
//     public static void main(String[] args) throws Exception {

//         JLabel label = new JLabel("Olá, seja bem vindo!", JLabel.CENTER);
//         label.setFont(new Font("Calibri", Font.BOLD,42));
        
//         JFrame janela = new JFrame();
//         janela.setSize(720, 720);
//         janela.getContentPane().setBackground(new Color(0, 139, 139));
//         janela.add(label);
//         janela.setVisible(true);

//         // Funcionario funcionario = new Funcionario();
//         // Gerente gerente = new Gerente();

//         // funcionario.setNome("Zé");
//         // gerente.setNome("Carlos");
//         // gerente.setSenha(1234);

//         // System.out.println("Dados do funcionario: ");
//         // System.out.println(funcionario.getNome());
//         // System.out.println("Dadps dp gerente: ");
//         // System.out.println(gerente.getNome());
//         // System.out.println(gerente.getSenha());
//         // System.out.println(gerente.autenticar(1234));
//     }
// }
