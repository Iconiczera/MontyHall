import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MontyHallGUI extends JFrame {

    private Random random = new Random();
    private int portaPremiada;
    private int portaEscolhida;

    public MontyHallGUI() {
        setTitle("Monty Hall");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1));
        add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Bem-vindo ao Monty Hall!");
        panel.add(label);
        JButton button = new JButton("Play");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogarMontyHall();
            }
        });
        panel.add(button);
    }

    private void jogarMontyHall() {
        portaPremiada = random.nextInt(3) + 1;

        String escolha = JOptionPane.showInputDialog(null, "Escolha uma porta (1, 2 ou 3):");
        portaEscolhida = Integer.parseInt(escolha);

        int portaVazia;
        do {
            portaVazia = random.nextInt(3) + 1;
        } while (portaVazia == portaEscolhida || portaVazia == portaPremiada);

        JOptionPane.showMessageDialog(null, "O apresentador abriu a porta " + portaVazia + " que está vazia.");

        int opcao = JOptionPane.showConfirmDialog(null, "Deseja trocar para a outra porta?", "Trocar de porta", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            for (int i = 1; i <= 3; i++) {
                if (i != portaEscolhida && i != portaVazia) {
                    portaEscolhida = i;
                    break;
                }
            }
        }

        if (portaEscolhida == portaPremiada) {
            JOptionPane.showMessageDialog(null, "Você ganhou! Parabéns!");
        } else {
            JOptionPane.showMessageDialog(null, "Você perdeu :(. A porta premiada era a " + portaPremiada + ".");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MontyHallGUI game = new MontyHallGUI();
                game.setVisible(true);
            }
        });
    }
}
