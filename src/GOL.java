import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GOL extends JPanel implements ActionListener {

    int cellSize = 14;
    int cellPadding = 1;
    int columns = 50;
    int rows = 50;
    Cell[][] currentGeneration = CreateInitialGeneration(columns,rows);

    public GOL() {
        this.setPreferredSize(new Dimension((cellSize + cellPadding) * columns - cellPadding,(cellSize + cellPadding) * rows - cellPadding));
        this.setBackground(Color.LIGHT_GRAY);

        Timer timer = new Timer(100, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Paint cells
        for (Cell[] cols : currentGeneration) {
            for (Cell cell : cols) {
                if (cell.alive) {
                    g2d.setColor(Color.GRAY);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillRect(cell.x, cell.y, cellSize, cellSize);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Define logic for new paint
        currentGeneration = CreateNextGeneration(currentGeneration);
        repaint();
    }

    /* Create an initial grid with the placement off each cell, and randomly set each cell's state to either true or false (alive or dead) */
    private Cell[][] CreateInitialGeneration(int cols, int rows) {
        Cell[][] initialGrid = new Cell[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                initialGrid[i][j] = new Cell((cellSize + cellPadding) * i, (cellSize + cellPadding) * j, (Math.random() < 0.3));
            }
        }
        return initialGrid;
    }

    /* Create the next generation based on the current generation, based on the rules of the game of life */
    private Cell[][] CreateNextGeneration(Cell[][] currentGeneration) {
        // Create an empty next generation
        Cell[][] nextGeneration = new Cell[columns][rows];

        // Any live cell with two or three live neighbours survives
        // Any dead cell with three live neighbours becomes a live cell
        // All other live cells die in the next generation. Similarly, all other dead cells stay dead
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                // Fill the next generation with information form the current generation
                nextGeneration[i][j] = new Cell(currentGeneration[i][j]);

                // Change the next generation based on the current generation
                int neighbors = CheckNeighbors(this.currentGeneration, i, j);
                if (this.currentGeneration[i][j].alive && (neighbors == 2 || neighbors == 3)) {
                    nextGeneration[i][j].alive = true;
                } else if (!this.currentGeneration[i][j].alive && neighbors == 3) {
                    nextGeneration[i][j].alive = true;
                } else {
                    nextGeneration[i][j].alive = false;
                }
            }
        }
        return nextGeneration;
    }

    private int CheckNeighbors(Cell[][] generation, int col, int row) {
        int neighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // The alive neighbor cells of the current cell, wrapping around the grid borders
                Cell neighbor = generation[(col + i + columns) % columns][(row + j + rows) % rows];


                // The alive neighbor cells of the current cell, no wrapping
                /*
                Cell neighbor;
                if (col + i >= columns || col + i < 0 || row + j >= rows || row + j < 0) {
                    neighbor = new Cell(0,0, false);
                } else {
                    neighbor = generation[col + i][row + j];
                } */

                // If the neighbor's state is true (alive), add it to the amount of alive neighbors
                neighbors += neighbor.alive ? 1 : 0;
            }
        }
        // Subtract the cell itself from the alive neighbors
        neighbors -= generation[col][row].alive ? 1 : 0;

        return neighbors;
    }
}
