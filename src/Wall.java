import java.awt.Color;

public class Wall extends Cell {
    /**
     * Constructor
     *
     * @param x the {@code x} position of the {@code Wall} cell
     * @param y the {@code y} position of the {@code Wall} cell
     */
    public Wall(int x, int y) {
        super(x, y);

        this.type = "Wall";
        this.crossingTime = 1000;

        int r = (50 + (int) (Math.random() * 25 + 1));
        int g = (50 + (int) (Math.random() * 25 + 1));
        int b = (50 + (int) (Math.random() * 25 + 1));
        this.color = new Color(r, g, b);
    }
}
