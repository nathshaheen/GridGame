import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Cell extends Rectangle {
    private static final int size = 35;
    private char column;
    private int row;
    private String type;
    private Color color;

    /**
     * Constructor
     *
     * @param column the column of the {@code Cell}
     * @param row    the row of the {@code Cell}
     * @param x      the x position of the {@code Cell}
     * @param y      the y position of the {@code Cell}
     */
    public Cell(char column, int row, int x, int y) {
        super(x, y, size, size);
        this.setColumn(column);
        this.setRow(row);
    }

    /**
     * Paint the {@code Cell}
     *
     * @param graphics      where to paint
     * @param mousePosition position of the mouse
     */
    public void paint(Graphics graphics, Point mousePosition) {
        if (contains(mousePosition)) {
            graphics.setColor(Color.GRAY);
        } else {
            graphics.setColor(color);
        }
        graphics.fillRect(x, y, size, size);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, size, size);
    }

    /**
     * Returns if the specified {@code Point} is within the {@code Cell}
     *
     * @param point the {@code Point} to test
     * @return      true if the {@code Point} is within the {@code Cell}, else false
     */
    @Override
    public boolean contains(Point point) {
        if (point != null) {
            return super.contains(point);
        } else {
            return false;
        }
    }

    public int leftOfComparison(Cell cell) {
        return Character.compare(column, cell.getColumn());
    }

    public int aboveOfComparison(Cell cell) {
        return Integer.compare(row, cell.getRow());
    }

    /*
     * GETTERS AND SETTERS
     */
    public abstract int getCrossingTime();

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Color getColor() {
//        return color;
//    }

    public void setColor(Color color) {
        this.color = color;
    }
}
