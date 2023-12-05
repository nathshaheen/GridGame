import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Grid implements Iterable<Cell> {
    private Cell[][] cells = new Cell[20][20];
    private static Random random = new Random();

    /**
     * Constructor
     */
    public Grid() {
        // Populate cells with different Cell Types
        for (int column = 0; column < cells.length; column++) {
            for (int row = 0; row < cells[column].length; row++) {
                int x = 10 + 35 * column;
                int y = 10 + 35 * row;
                char columnLabel = getColumnLabel(column);
                switch(random.nextInt(10)) {
                    case 0 -> cells[column][row] = new Wall(columnLabel, row, x, y);
                    case 1 -> cells[column][row] = new Oasis(columnLabel, row, x, y);
                    case 2 -> cells[column][row] = new PalmTree(columnLabel, row, x, y);
                    default -> cells[column][row] = new Sand(columnLabel, row, x, y);
                }
            }
        }
    }

    /**
     * Paint the {@code Grid}
     *
     * @param graphics      where to paint
     * @param mousePosition position of the mouse
     */
    public void paint(Graphics graphics, Point mousePosition) {
        for (Cell cell : this) {
            cell.paint(graphics, mousePosition);
        }
    }

    /**
     * Paint an overlay onto the specified {@code Cells} in cellOverlay
     *
     * @param graphics    where to paint
     * @param cellOverlay list of {@code Cells} to paint the overlay on
     * @param color       the {@code Color} of the overlay
     */
    public void paintOverlay(Graphics graphics, List<Cell> cellOverlay, Color color) {
        graphics.setColor(color);
        for (Cell cell : cellOverlay) {
            graphics.fillRect(cell.x + 2, cell.y + 2, cell.width - 4, cell.height - 4);
        }
    }

    /**
     * Return a {@code List} of {@code Cells} within a circumference of size.
     * Use considerTime to consider {@code Cell} crossingTime
     *
     * @param center       the center {@code Cell} of the radius
     * @param size         the size of the radius
     * @param considerTime true to consider {@code Cell} crossingTime, false to ignore crossingTime
     * @return             a {@code List} of {@code Cells} within a circumference of size
     */
    public List<Cell> getRadius(Cell center, int size, boolean considerTime) {
        int column = getColumnNumber(center.getColumn());
        int row = center.getRow();
        Set<Cell> inRadius = new HashSet<>();

        for (int i = column - 1; i < column + 2; i++) {
            for (int j = row - 1; j < row + 2; j++) {
                if (!(i == column && j == row)) {
                    Cell currentCell = getCellAtColumnRow(getColumnLabel(i), j).orElse(null);
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
     * Convert column numbers to their corresponding label letter
     *
     * @param column the column to convert
     * @return       the corresponding label letter of column
     */
    public char getColumnLabel(int column) {
        return (char) (column + 65);
    }

    /**
     * Convert label letters to their corresponding column number
     *
     * @param column the label to convert
     * @return       the corresponding column number of label
     */
    public int getColumnNumber(char column) {
        return (int) column - 65;
    }

    /**
     * Returns the {@code Cell} at the specified column and row
     *
     * @param column the column
     * @param row    the row
     * @return       the {@code Cell} at the specified column and row
     */
    public Optional<Cell> getCellAtColumnRow(char column, int row) {
        int columnLabel = getColumnNumber(column);
        if (columnLabel >= 0 && columnLabel < cells.length && row >= 0 && row < cells[columnLabel].length) {
            return Optional.of(cells[columnLabel][row]);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Return {@code Cells} between the specified columns and rows
     *
     * @param columnStart the start column
     * @param rowStart    the start row
     * @param columnEnd   the end column
     * @param rowEnd      the end row
     * @return            the {@code Cells} between the specified columns and rows
     */
    public List<Cell> getCellsInRange(char columnStart, int rowStart, char columnEnd, int rowEnd) {
        int columnStartLabel = getColumnNumber(columnStart);
        int columnEndLabel = getColumnNumber(columnEnd);
        List<Cell> cellsInRange = new ArrayList<>();
        for (int i = columnStartLabel; i <= columnEndLabel; i++) {
            for (int j = rowStart; j <= rowEnd; j++) {
                getCellAtColumnRow(getColumnLabel(i), j).ifPresent(cellsInRange::add);
            }
        }
        return cellsInRange;
    }

    /**
     * Replace the old {@code Cell} with the new {@code Cell}
     *
     * @param old         the {@code Cell} to replace
     * @param replacement the replacement {@code Cell}
     */
    public void replaceCell(Cell old, Cell replacement) {
        cells[getColumnNumber(old.getColumn())][old.getRow()] = replacement;
    }

    /**
     * Return the {@code Cell} at the specified {@code Point} if it exists
     *
     * @param point the {@code Point} to test at
     * @return      the {@code Cell} at the specified {@code Point}
     */
    public Optional<Cell> getCellAtPoint(Point point) {
        for (Cell cell : this) {
            if (cell.contains(point)) {
                return Optional.of(cell);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a {@code CellIterator}
     *
     * @return a {@code CellIterator}
     */
    @Override
    public CellIterator iterator() {
        return new CellIterator(cells);
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
