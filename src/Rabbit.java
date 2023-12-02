import java.awt.Polygon;
import java.util.ArrayList;

public class Rabbit extends Actor {
    /**
     * Constructor
     *
     * @param location the {@code Cell location} of the {@code Rabbit}
     * @param redness  the {@code redness} of the {@code Rabbit}
     */
    public Rabbit(Cell location, float redness) {
        super(location, redness);
        setTurns(1);
        setMoves(3);
        setRange(1);
        setPoly();
    }

    /**
     * Draw the {@code Rabbit}
     */
    public void setPoly() {
        setDisplay(new ArrayList<>());
        Polygon ear1 = new Polygon();
        ear1.addPoint(getLocation().x + 11, getLocation().y + 5);
        ear1.addPoint(getLocation().x + 11, getLocation().y + 12);
        ear1.addPoint(getLocation().x + 16, getLocation().y + 12);
        ear1.addPoint(getLocation().x + 16, getLocation().y + 5);
        Polygon ear2 = new Polygon();
        ear2.addPoint(getLocation().x + 19, getLocation().y + 5);
        ear2.addPoint(getLocation().x + 19, getLocation().y + 12);
        ear2.addPoint(getLocation().x + 24, getLocation().y + 12);
        ear2.addPoint(getLocation().x + 24, getLocation().y + 5);
        Polygon face = new Polygon();
        face.addPoint(getLocation().x + 8, getLocation().y + 12);
        face.addPoint(getLocation().x + 27, getLocation().y + 12);
        face.addPoint(getLocation().x + 27, getLocation().y + 25);
        face.addPoint(getLocation().x + 8, getLocation().y + 25);
        getDisplay().add(face);
        getDisplay().add(ear1);
        getDisplay().add(ear2);
    }
}

