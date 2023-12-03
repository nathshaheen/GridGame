import java.awt.Color;
import java.util.Random;

public class Sand extends Cell {
    private static Random random = new Random();
    private final float yellow;

    /**
     * Constructor
     *
     * @param column the column of the {@code Sand}
     * @param row    the row of the {@code Sand}
     * @param x      the x position of the {@code Sand}
     * @param y      the y position of the {@code Sand}
     */
    public Sand(char column, int row, int x, int y) {
        super(column, row, x, y);

        yellow = random.nextFloat();
        if (yellow >= 0.8f) {   // Determine if Sand is Quicksand
            setType("Quicksand");
        } else {
            setType("Sand");
        }

        int r = (200 + (int )(yellow * 50 + 1));
        int g = (150 + (int) (yellow * 50 + 1));
        int b = 0;
        setColor(new Color(r, g, b));
    }

    /*
     * GETTERS AND SETTERS
     */
    @Override
    public int getCrossingTime() {
        if (yellow >= 0.8f) {
            return 3;
        } else {
            return 1;
        }
    }
}
