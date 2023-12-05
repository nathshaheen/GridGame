import java.awt.Polygon;
import java.util.ArrayList;

public class Dog extends Actor {
    /**
     * Constructor
     *
     * @param location the location of the {@code Dog}
     * @param redness  the redness of the {@code Dog}
     */
    public Dog(Cell location, float redness) {
        super(location, redness);
        this.setTurns(1);
        this.setMoves(2);
        this.setRange(3);
        this.setPoly();
    }

    /**
     * Draw the {@code Dog}
     */
    public void setPoly() {
        setDisplay(new ArrayList<>());
        Polygon leftEar = new Polygon();
        leftEar.addPoint(getLocation().x + 5, getLocation().y + 5);
        leftEar.addPoint(getLocation().x + 15, getLocation().y + 5);
        leftEar.addPoint(getLocation().x + 5, getLocation().y + 15);
        Polygon rightEar = new Polygon();
        rightEar.addPoint(getLocation().x + 20, getLocation().y + 5);
        rightEar.addPoint(getLocation().x + 30, getLocation().y + 5);
        rightEar.addPoint(getLocation().x + 30, getLocation().y + 15);
        Polygon face = new Polygon();
        face.addPoint(getLocation().x + 8, getLocation().y + 7);
        face.addPoint(getLocation().x + 27, getLocation().y + 7);
        face.addPoint(getLocation().x + 27, getLocation().y + 25);
        face.addPoint(getLocation().x + 8, getLocation().y + 25);
        getDisplay().add(leftEar);
        getDisplay().add(rightEar);
        getDisplay().add(face);
    }
}
