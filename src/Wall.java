import java.awt.Color;
import java.util.Random;

public class Wall extends Cell {
    private static Random random = new Random();

    /**
     * Constructor
     *
     * @param column    the {@code column} of the {@code Wall}
     * @param row       the {@code row} of the {@code Wall}
     * @param x         the {@code x} position of the {@code Wall}
     * @param y         the {@code y} position of the {@code Wall}
     */
    public Wall(char column, int row, int x, int y) {
        super(column, row, x, y);
        setType("Wall");

        int r = (50 + (random.nextInt(0, 1) * 25 + 1));;
        int g = (50 + (random.nextInt(0, 1) * 25 + 1));
        int b = (50 + (random.nextInt(0, 1) * 25 + 1));;
        setColor(new Color(r, g, b));
    }

    /*
     * GETTERS AND SETTERS
     */

    /**
     * Returns the {@code crossingTime}
     *
     * @return the {@code crossingTime}
     */
    @Override
    public int getCrossingTime() {
        return 5;
    }
}
