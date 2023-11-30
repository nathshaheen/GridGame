import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;

public class Lion extends Actor{
    /**
     *
     *
     * @param location
     */
    public Lion(Cell location, float redness) {
        super(location, redness);
        setTurns(1);
        setMoves(3);
        setRange(5);
        setPoly();
    }

    public void setPoly() {
        setDisplay(new ArrayList<>());
        Polygon mane = new Polygon();
        mane.addPoint(getLocation().x + 6, getLocation().y + 6);
        mane.addPoint(getLocation().x + 29, getLocation().y + 6);
        mane.addPoint(getLocation().x + 29, getLocation().y + 29);
        mane.addPoint(getLocation().x + 6, getLocation().y + 29);
        Polygon face = new Polygon();
        face.addPoint(getLocation().x + 11, getLocation().y + 11);
        face.addPoint(getLocation().x + 24, getLocation().y + 11);
        face.addPoint(getLocation().x + 24, getLocation().y + 24);
        face.addPoint(getLocation().x + 11, getLocation().y + 24);
        getDisplay().add(mane);
        getDisplay().add(face);
    }
}
