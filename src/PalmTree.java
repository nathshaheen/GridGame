import java.awt.Color;
import java.util.Random;

public class PalmTree extends Cell {
    private static Random random = new Random();

    /**
     * Constructor
     *
     * @param column    the {@code column} of the {@code PalmTree}
     * @param row       the {@code row} of the {@code PalmTree}
     * @param x         the {@code x} position of the {@code PalmTree}
     * @param y         the {@code y} position of the {@code PalmTree}
     */
    public PalmTree(char column, int row, int x, int y) {
        super(column, row, x, y);
        setType("Palm Tree");

        int r = 0;
        int g = (100 + (random.nextInt(0, 1) * 50 + 1));
        int b = 0;
        setColor(new Color(r, g, b));
    }

    /*
     * GETTERS AND SETTERS
     */
    @Override
    public int getCrossingTime() {
        return 2;
    }
}
