import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class MenuItem  extends Rectangle {
    private Runnable action;
    private String display;
    static int height = 30;
    static int width = 200;

    /**
     * Constructor
     *
     * @param display the {@code String} to display
     * @param x       the {@code x} position of the {@code MenuItem}
     * @param y       the {@code y} position of the {@code MenuItem}
     * @param action  the {@code action} of the {@code MenuItem}
     */
    public MenuItem(String display, int x, int y, Runnable action) {
        super(x, y, width, height);
        this.display = display;
        this.action = action;
    }

    /**
     * Paint the {@code MenuItem}
     *
     * @param g where to paint
     */
    public void paint(Graphics g) {
        g.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawRect(x - 1, y - 1, width + 2, height + 2);
        g.drawString(display, x + 8, y + 23);
    }

    /*
     * GETTERS AND SETTERS
     */
    public Runnable getAction() {
        return action;
    }

//    public void setAction(Runnable action) {
//        this.action = action;
//    }

//    public String getDisplay() {
//        return display;
//    }

//    public void setDisplay(String display) {
//        this.display = display;
//    }
}
