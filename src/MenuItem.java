import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class MenuItem  extends Rectangle {
    private Runnable action;
    private String display;
    static int height = 30;
    static int width = 200;

    /**
     *
     *
     * @param display
     * @param x
     * @param y
     * @param action
     */
    public MenuItem(String display, int x, int y, Runnable action) {
        super(x, y, width, height);
        this.display = display;
        this.action = action;
    }

    /**
     *
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawRect(x - 1, y - 1, width + 2, height + 2);
//        g.setFont(displayFont);
        g.drawString(display, x + 8, y + 23);
    }

    /*
     * GETTERS AND SETTERS
     */

    /**
     *
     *
     * @return
     */
    public Runnable getAction() {
        return action;
    }
}
