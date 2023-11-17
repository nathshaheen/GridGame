import java.awt.Graphics;
import java.awt.Point;

public class Grid {
    Cell[][] cells = new Cell[20][20];

    /**
     * Constructor
     */
    public Grid() {
        // Populate cells with different Cell Types
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int col = 10 + 35 * i;
                int row = 10 + 35 * j;
                double rand = Math.random();

                if (rand < 0.7) {
                    cells[i][j] = new Sand(col, row);
                } else if (rand < 0.8) {
                    cells[i][j] = new PalmTree(col, row);
                } else if (rand < 0.9) {
                    cells[i][j] = new Oasis(col, row);
                } else {
                    cells[i][j] = new Wall(col, row);
                }
            }
        }
    }

    /**
     * Paint the {@code Grid}
     *
     * @param g         where to paint
     * @param mousePos  position of the mouse
     */
    public void paint(Graphics g, Point mousePos) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].paint(g, mousePos);
            }
        }
    }

    /**
     * Returns the {@code Cell} at the specified Column and Row
     *
     * @param c column
     * @param r row
     * @return  the {@code Cell} at the specified Column and Row
     */
    public Cell cellAtColRow(int c, int r) {
        return cells[c][r];
    }
}
