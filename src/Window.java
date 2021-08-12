import javax.swing.*;

public class Window extends JFrame {

    GOL gameOfLife = new GOL();

    public Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.add(gameOfLife);

        this.pack();
        this.setVisible(true);
    }
}
