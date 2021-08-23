import javax.swing.JFrame;
import java.awt.Color;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("The Ball Crushing Game");
        Gameplay gamePlay = new Gameplay();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setBounds(10, 10, 700, 600);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(48, 42, 40));

        frame.add(gamePlay);
        frame.add(gamePlay);
        frame.add(gamePlay);

    }
}