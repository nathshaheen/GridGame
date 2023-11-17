import java.awt.Color;

public class PalmTree extends Cell {
    /**
     * Constructor
     *
     * @param x the {@code x} position of the {@code PalmTree} cell
     * @param y the {@code y} position of the {@code PalmTree} cell
     */
    public PalmTree(int x, int y) {
        super(x, y);

        this.type = "Palm Tree";
        this.crossingTime = 2;

        int r = 0;
        int g = (100 + (int) (Math.random() * 50 + 1));
        int b = 0;
        this.color = new Color(r, g, b);
    }
}
