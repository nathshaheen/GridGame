import java.awt.Polygon;
import java.util.ArrayList;

public class Rabbit extends Actor {
    /**
     * Constructor
     *
     * @param location the location of the {@code Rabbit}
     * @param redness  the redness of the {@code Rabbit}
     */
    public Rabbit(Cell location, float redness) {
        super(location, redness);
        this.setTurns(1);
        this.setMoves(3);
        this.setRange(1);
        this.setPoly();
    }

    /**
     * Draw the {@code Rabbit}
     */
    public void setPoly() {
        setDisplay(new ArrayList<>());
        Polygon leftEar = new Polygon();
        leftEar.addPoint(getLocation().x + 11, getLocation().y + 5);
        leftEar.addPoint(getLocation().x + 11, getLocation().y + 12);
        leftEar.addPoint(getLocation().x + 16, getLocation().y + 12);
        leftEar.addPoint(getLocation().x + 16, getLocation().y + 5);
        Polygon rightEar = new Polygon();
        rightEar.addPoint(getLocation().x + 19, getLocation().y + 5);
        rightEar.addPoint(getLocation().x + 19, getLocation().y + 12);
        rightEar.addPoint(getLocation().x + 24, getLocation().y + 12);
        rightEar.addPoint(getLocation().x + 24, getLocation().y + 5);
        Polygon face = new Polygon();
        face.addPoint(getLocation().x + 8, getLocation().y + 12);
        face.addPoint(getLocation().x + 27, getLocation().y + 12);
        face.addPoint(getLocation().x + 27, getLocation().y + 25);
        face.addPoint(getLocation().x + 8, getLocation().y + 25);
        getDisplay().add(face);
        getDisplay().add(leftEar);
        getDisplay().add(rightEar);
    }
}

