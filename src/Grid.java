import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class Grid {
    private Cell[][] cells = new Cell[20][20];
    private static Random random = new Random();

    /**
     * Constructor
     */
    public Grid() {
        // Populate cells with different Cell Types
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int x = 10 + 35 * i;
                int y = 10 + 35 * j;
                switch(random.nextInt(10)) {
                    case 0 -> cells[i][j] = cells[i][j] = new Wall(colToLabel(i), j, x, y);
                    case 1 -> cells[i][j] = cells[i][j] = new Oasis(colToLabel(i), j, x, y);
                    case 2 -> cells[i][j] = cells[i][j] = new PalmTree(colToLabel(i), j, x, y);
                    default -> cells[i][j] = cells[i][j] = new Sand(colToLabel(i), j, x, y);
                }
            }
        }
    }

    /**
     * Paint the {@code Grid}
     *
     * @param g             where to paint
     * @param mousePosition position of the mouse
     */
    public void paint(Graphics g, Point mousePosition) {
//        doToEachCell((Cell cell) -> cell.paint(g, mousePosition));
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].paint(g, mousePosition);
            }
        }
    }

    /**
     *
     */
    public void paintOverlay(Graphics g, List<Cell> cellOverlay, Color color) {
        g.setColor(color);
        for (Cell cell : cellOverlay) {
            g.fillRect(cell.x + 2, cell.y + 2, cell.width - 4, cell.height - 4);
        }
    }

    /**
     *
     *
     * @param center
     * @param size
     * @param considerTime
     * @return
     */
    public List<Cell> getRadius(Cell center, int size, boolean considerTime) {
        int i = labelToCol(center.getColumn());
        int j = center.getRow();
        Set<Cell> inRadius = new HashSet<>();
        if (size > 0) {
            getCellAtColRow(colToLabel(i), j - 1).ifPresent(inRadius::add);
            getCellAtColRow(colToLabel(i), j + 1).ifPresent(inRadius::add);
            getCellAtColRow(colToLabel(i - 1), j).ifPresent(inRadius::add);
            getCellAtColRow(colToLabel(i + 1), j).ifPresent(inRadius::add);
        }

        for (Cell cell : inRadius.toArray(new Cell[0])) {
            if (considerTime) {
                inRadius.addAll(getRadius(cell, size - cell.getCrossingTime(), true));
            } else {
                inRadius.addAll(getRadius(cell, size - 1, false));
            }
        }

        return new ArrayList<>(inRadius);
    }

    /**
     *
     *
     * @param col
     * @return
     */
    public char colToLabel(int col) {
        return (char) (col + 65);
    }

    /**
     *
     *
     * @param col
     * @return
     */
    public int labelToCol(char col) {
        return (int) col - 65;
    }

    /**
     * Returns the {@code Cell} at the specified Column and Row
     *
     * @param col   column
     * @param row   row
     * @return  the  {@code Cell} at the specified Column and Row
     */
    public Optional<Cell> getCellAtColRow(char col, int row) {
        int cellCol = labelToCol(col);
        if (cellCol >= 0 && cellCol < cells.length && row >= 0 && row < cells[cellCol].length) {
            return Optional.of(cells[cellCol][row]);
        } else {
            return Optional.empty();
        }
    }

    /**
     *
     *
     * @param col1
     * @param row1
     * @param col2
     * @param row2
     * @return
     */
    public List<Cell> cellsInRange(char col1, int row1, char col2, int row2) {
        int cellCol1 = labelToCol(col1);
        int cellCol2 = labelToCol(col2);
        List<Cell> cellsInRange = new ArrayList<>();
        System.out.println();
        for (int i = cellCol1; i <= cellCol2; i++) {
            for (int j = row1; j <= row2; j++) {
                getCellAtColRow(colToLabel(i), j).ifPresent(cellsInRange::add);
            }
        }
        return cellsInRange;
    }

    /**
     *
     *
     * @param old
     * @param replacement
     */
    public void replaceCell(Cell old, Cell replacement) {
        cells[labelToCol(old.getColumn())][old.getRow()] = replacement;
    }

    /**
     *
     *
     * @param p
     * @return
     */
    public Optional<Cell> cellsAtPoint(Point p) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].contains(p)) {
                    return Optional.of(cells[i][j]);
                }
            }
        }
        return Optional.empty();
    }

    /**
     *
     *
     * @return
     */
    public Cell[][] getCells() {
        return cells;
    }
}
