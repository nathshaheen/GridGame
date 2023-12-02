import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Optional;

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
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].paint(g, mousePosition);
            }
        }
    }

    /**
     * Paint an overlay onto the specified {@code Cells} in {@code cellOverlay}
     *
     * @param g           where to paint
     * @param cellOverlay list of {@code Cells} to paint the overlay on
     * @param color       the {@code Color} of the overlay
     */
    public void paintOverlay(Graphics g, List<Cell> cellOverlay, Color color) {
        g.setColor(color);
        for (Cell cell : cellOverlay) {
            g.fillRect(cell.x + 2, cell.y + 2, cell.width - 4, cell.height - 4);
        }
    }

    /**
     * Return a {@code List} of {@code Cells} within a radius of {@code size}.
     * Use {@code considerTime} to consider {@code Cell crossingTime}
     *
     * @param center       the center {@code Cell} of the radius
     * @param size         the size of the radius
     * @param considerTime {@code true} to consider {@code Cell crossingTime}, {@code false} to ignore {@code Cell crossingTime}
     * @return
     */
    public List<Cell> getRadius(Cell center, int size, boolean considerTime) {
        int col = labelToCol(center.getColumn());
        int row = center.getRow();
        Set<Cell> inRadius = new HashSet<>();

        for (int i = col - 1; i < col + 2; i++) {
            for (int j = row - 1; j < row + 2; j++) {
                if (!(i == col && j == row)) {
                    Cell currentCell = getCellAtColRow(colToLabel(i), j).orElse(null);
                    if (currentCell != null) {
                        if (considerTime && size - currentCell.getCrossingTime() >= 0) {
                            inRadius.add(currentCell);
                            inRadius.addAll(getRadius(currentCell, size - currentCell.getCrossingTime(), true));
                        } else if (!considerTime && size >= 0) {
                            inRadius.add(currentCell);
                            inRadius.addAll(getRadius(currentCell, size - 1, false));
                        }
                    }
                }
            }
        }

        return new ArrayList<>(inRadius);
    }

    /**
     * Convert {@code col} numbers to their corresponding {@code label} letter
     *
     * @param col the {@code col} to convert
     * @return    the corresponding {@code label} letter of {@code col}
     */
    public char colToLabel(int col) {
        return (char) (col + 65);
    }

    /**
     * Convert {@code label} letters to their corresponding {@code col} number
     *
     * @param col the {@code label} to convert
     * @return    the corresponding {@code col} number of {@code label}
     */
    public int labelToCol(char col) {
        return (int) col - 65;
    }

    /**
     * Returns the {@code Cell} at the specified {@code col} and {@code row}
     *
     * @param col   column
     * @param row   row
     * @return  the  {@code Cell} at the specified {@code col} and {@code row}
     */
    public Optional<Cell> getCellAtColRow(char col, int row) {
        int cellCol = labelToCol(col);
        if (cellCol >= 0 && cellCol < cells.length && row >= 0 && row < cells[cellCol].length) {
            return Optional.of(cells[cellCol][row]);
        } else {
            return Optional.empty();
        }
    }

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

    public void replaceCell(Cell old, Cell replacement) {
        cells[labelToCol(old.getColumn())][old.getRow()] = replacement;
    }

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

    /*
     * GETTERS AND SETTERS
     */
//    public Cell[][] getCells() {
//        return cells;
//    }

//    public void setCells(Cell[][] cells) {
//        this.cells = cells;
//    }
}
