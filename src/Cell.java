import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cell extends Rectangle {
    static int size = 35;
    String type;
    int crossingTime;
    Color color;

    /**
     * Constructor
     *
     * @param x the x position of the {@code Cell}
     * @param y the y position of the {@code Cell}
     */
    public Cell(int x, int y) {
        super(x, y, size, size);
        color = Color.LIGHT_GRAY;
    }

    /**
     * Paint the Cell
     *
     * @param g         where to paint
     * @param mousePos  position of the mouse
     */
    public void paint(Graphics g, Point mousePos) {
        if (contains(mousePos)) {
            drawCellInfo(g);
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
     * @return  {@code true} if the Point is within the {@code cell}, else {@code false}
     */
    public boolean contains(Point p) {
        if (p != null) {
            return super.contains(p);
        } else {
            return false;
        }
    }

    /**
     * Draws the Cell Position, Cell Type, and Crossing Time in the Sidebar
     *
     * @param g where to draw
     */
    public void drawCellInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Cell Position: " + (x / 35) + ", " + (y / 35), 730, 30);
        g.drawString("Cell Type: " + getCellType(), 730, 50);
        g.drawString("Crossing Time: " + getCrossingTime(), 730, 70);
    }

    /**
     * Returns the {@code Cell type}
     *
     * @return  the {@code Cell type}
     */
    public String getCellType() {
        return type;
    }

    /**
     * Returns the {@code Cell crossingTime}
     * @return  the {@code Cell crossingTime}
     */
    public int getCrossingTime() {
        return crossingTime;
    }
}
