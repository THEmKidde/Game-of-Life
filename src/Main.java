import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int Cols = 10, Rows = 10;

        int[][] grid = CreateInitialGeneration(Cols, Rows);

        for (int run = 0; run < 100; run++) {
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            } catch (IOException | InterruptedException ex) {}

            for (int i = 0; i < Cols; i++) {
                for (int j = 0; j < Rows; j++) {
                    if (grid[i][j] == 1) {
                        System.out.print("[X]");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.println();
            }
            System.out.println();

            try {
                Thread.sleep(300);
            } catch (Exception e) { }

            grid = CreateNextGeneration(grid);
        }
    }

    static int[][] CreateInitialGeneration(int cols, int rows) {
        int[][] grid = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = (int)Math.round(Math.random());
            }
        }

        return grid;
    }

    static int[][] CreateNextGeneration(int[][] grid) {
        int [][] next = new int[grid.length][grid[0].length];
        // Any live cell with two or three live neighbours survives
        // Any dead cell with three live neighbours becomes a live cell
        // All other live cells die in the next generation. Similarly, all other dead cells stay dead
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j< grid[0].length; j++) {
                int neighbors = CheckNeighbors(grid, i, j);
                if (grid[i][j] == 1 && (neighbors == 2 || neighbors == 3)) {
                    next[i][j] = 1;
                } else if (grid[i][j] == 0 && neighbors == 3) {
                    next[i][j] = 1;
                } else {
                    next[i][j] = 0;
                }
            }
        }

        return next;
    }

    static int CheckNeighbors(int[][] grid, int col, int row) {
        int sum = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // The sum of alive cells around the cell, wrapping around the grid borders
                sum += grid[(col + i + grid.length) % grid.length][(row + j + grid[0].length) % grid[0].length];
            }
        }
        // Subtract the cell itself from the sum
        sum -= grid[col][row];

        return sum;
    }
}
