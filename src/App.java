import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class App {
    public static void main(String[] args) throws Exception {

        JLabel label = new JLabel("Olá, seja bem vindo!", JLabel.CENTER);
        label.setFont(new Font("Calibri", Font.BOLD,42));
        
        JFrame janela = new JFrame();
        janela.setSize(720, 720);
        janela.getContentPane().setBackground(new Color(0, 139, 139));
        janela.add(label);
        janela.setVisible(true);

        // Funcionario funcionario = new Funcionario();
        // Gerente gerente = new Gerente();

        // funcionario.setNome("Zé");
        // gerente.setNome("Carlos");
        // gerente.setSenha(1234);

        // System.out.println("Dados do funcionario: ");
        // System.out.println(funcionario.getNome());
        // System.out.println("Dadps dp gerente: ");
        // System.out.println(gerente.getNome());
        // System.out.println(gerente.getSenha());
        // System.out.println(gerente.autenticar(1234));
    }
}
