import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MenuItem  extends Rectangle {
    private Runnable action;
    private String display;
    static int height = 30;
    static int width = 200;

    /**
     * Constructor
     *
     * @param display the {@code String} to display
     * @param x       the x position of the {@code MenuItem}
     * @param y       the y position of the {@code MenuItem}
     * @param action  the action of the {@code MenuItem}
     */
    public MenuItem(String display, int x, int y, Runnable action) {
        super(x, y, width, height);
        this.setDisplay(display);
        this.setAction(action);
    }

    /**
     * Paint the {@code MenuItem}
     *
     * @param graphics where to paint
     */
    public void paint(Graphics graphics) {
        graphics.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
        graphics.drawRect(x - 1, y - 1, width + 2, height + 2);
        graphics.drawString(display, x + 8, y + 23);
    }

    /*
     * GETTERS AND SETTERS
     */
    public Runnable getAction() {
        return action;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

//    public String getDisplay() {
//        return display;
//    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
