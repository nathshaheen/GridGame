import java.util.List;

public abstract class MoveStrategy {
    /**
     * @param possibleLocations
     * @return
     */
    public abstract Cell chooseNextLocation(List<Cell> possibleLocations);
}
