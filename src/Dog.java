import java.awt.Polygon;
import java.util.ArrayList;

public class Dog extends Actor {
    /**
     *
     *
     * @param location
     */
    public Dog(Cell location, float redness) {
        super(location, redness);
        setTurns(1);
        setMoves(5);
        setRange(3);
        setPoly();
    }

    public void setPoly() {
        setDisplay(new ArrayList<>());
        Polygon ear1 = new Polygon();
        ear1.addPoint(getLocation().x + 5, getLocation().y + 5);
        ear1.addPoint(getLocation().x + 15, getLocation().y + 5);
        ear1.addPoint(getLocation().x + 5, getLocation().y + 15);
        Polygon ear2 = new Polygon();
        ear2.addPoint(getLocation().x + 20, getLocation().y + 5);
        ear2.addPoint(getLocation().x + 30, getLocation().y + 5);
        ear2.addPoint(getLocation().x + 30, getLocation().y + 15);
        Polygon face = new Polygon();
        face.addPoint(getLocation().x + 8, getLocation().y + 7);
        face.addPoint(getLocation().x + 27, getLocation().y + 7);
        face.addPoint(getLocation().x + 27, getLocation().y + 25);
        face.addPoint(getLocation().x + 8, getLocation().y + 25);
        getDisplay().add(ear1);
        getDisplay().add(ear2);
        getDisplay().add(face);
    }
}
