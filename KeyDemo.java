
// import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyDemo implements KeyListener {

    JFrame frame;
    JLabel label;
    int x = 0, y = 0;

    KeyDemo() {

        label = new JLabel(new ImageIcon("flayer.png"));
        label.setBounds(x, y, 300, 300);

        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.add(label);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            y = y - 10;
            label.setLocation(x, y);
        }
        if (key == KeyEvent.VK_DOWN) {
            y = y + 10;
            label.setLocation(x, y);
        }
        if (key == KeyEvent.VK_LEFT) {
            x = x - 10;
            label.setLocation(x, y);
        }
        if (key == KeyEvent.VK_RIGHT) {
            x = x + 10;
            label.setLocation(x, y);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
