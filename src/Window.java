import javax.swing.*;

public class Window extends JFrame {

    GameOfLife gameOfLife = new GameOfLife();

    public Window() {
        this.setTitle("Conway's Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.add(gameOfLife);

        this.pack();
        this.setVisible(true);
    }
}
