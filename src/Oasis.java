import java.awt.Color;

public class Oasis extends Cell {
    /**
     * Constructor
     *
     * @param x the {@code x} position of the {@code Oasis} cell
     * @param y the {@code y} position of the {@code Oasis} cell
     */
    public Oasis(int x, int y) {
        super(x, y);

        this.type = "Oasis";
        this.crossingTime = 8;

        int r = 0;
        int g = (100 + (int) (Math.random() * 50 + 1));
        int b = (200 + (int) (Math.random() * 50 + 1));
        this.color = new Color(r, g, b);
    }
}
