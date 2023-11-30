import java.util.List;

public class LeftMostMove extends MoveStrategy {
    /**
     *
     *
     * @param possibleLocations
     * @return
     */
    public Cell chooseNextLocation(List<Cell> possibleLocations) {
        return possibleLocations.get(0);
    }
}
