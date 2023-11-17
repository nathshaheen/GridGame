import java.awt.Color;

public class Sand extends Cell {
    /**
     * Constructor
     *
     * @param x the {@code x} position of the {@code Sand} cell
     * @param y the {@code y} position of the {@code Sand} cell
     */
    public Sand(int x, int y) {
        super(x, y);

        // Determine if Sand is Quicksand
        double viscosity = Math.random();
        if (viscosity >= 0.8) {
            this.type = "Quicksand";
            this.crossingTime = 15;
        } else {
            this.type = "Sand";
            this.crossingTime = 5;
        }

        int r = (200 + (int) (viscosity * 50 + 1));
        int g = (150 + (int) (viscosity * 50 + 1));
        int b = 0;
        this.color = new Color(r, g, b);
    }
}
