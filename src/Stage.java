import java.awt.Graphics;
import java.awt.Point;

public class Stage {
    Grid grid;

    /**
     * Constructor
     */
    public Stage() {
        grid = new Grid();
    }

    /**
     * Paint the {@code Stage}
     *
     * @param g         where to paint
     * @param mouseLoc  position of the mouse
     */
    public void paint(Graphics g, Point mouseLoc) {
        grid.paint(g, mouseLoc);
    }
}
