import java.awt.Color;
import java.util.Random;

public class Oasis extends Cell {
    private static Random random = new Random();

    /**
     * Constructor
     *
     * @param column the column of the {@code Oasis}
     * @param row    the row of the {@code Oasis}
     * @param x      the x position of the {@code Oasis}
     * @param y      the y position of the {@code Oasis}
     */
    public Oasis(char column, int row, int x, int y) {
        super(column, row, x, y);
        this.setType("Oasis");

        int r = 0;
        int g = (100 + (random.nextInt(0, 1) * 50 + 1));
        int b = (200 + (random.nextInt(0, 1) * 50 + 1));
        this.setColor(new Color(r, g, b));
    }

    /*
     * GETTERS AND SETTERS
     */
    @Override
    public int getCrossingTime() {
        return 4;
    }
}
