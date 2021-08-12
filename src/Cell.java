public class Cell{

    public int x;
    public int y;
    public boolean alive;

    public Cell(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public Cell(Cell cell) {
        this.x = cell.x;
        this.y = cell.y;
        this.alive = cell.alive;
    }
}
