import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Cell extends Rectangle {
    private static final int size = 35;
    private char column;
    private int row;
    private String type;
    private Color color;

    /**
     * Constructor
     *
     * @param column the {@code column} of the {@code Cell}
     * @param row    the {@code row} of the {@code Cell}
     * @param x      the {@code x} position of the {@code Cell}
     * @param y      the {@code y} position of the {@code Cell}
     */
    public Cell(char column, int row, int x, int y) {
        super(x, y, size, size);
        this.column = column;
        this.row = row;
    }

    /**
     * Paint the {@code Cell}
     *
     * @param g             where to paint
     * @param mousePosition position of the mouse
     */
    public void paint(Graphics g, Point mousePosition) {
        if (contains(mousePosition)) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(color);
        }
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }

    /**
     * Returns if the specified {@code Point} is within the {@code Cell}
     *
     * @param p the {@code Point} to test
     * @return {@code true} if the {@code Point} is within the {@code Cell}, else {@code false}
     */
    @Override
    public boolean contains(Point p) {
        if (p != null) {
            return super.contains(p);
        } else {
            return false;
        }
    }

    /*
     * GETTERS AND SETTERS
     */
    public abstract int getCrossingTime();

    public char getColumn() {
        return column;
    }

//    public void setColumn(char column) {
//        this.column = column;
//    }

    public int getRow() {
        return row;
    }

//    public void setRow(int row) {
//        this.row = row;
//    }

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
