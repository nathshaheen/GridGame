import java.util.List;

public class LeftMostMove extends MoveStrategy {
    /**
     * Return the left-most {@code Cell} from the {@code List} of {@code possibleLocations}
     *
     * @param possibleLocations a {@code List} of {@code Cells} to be selected from
     * @return the left-most {@code Cell} from the {@code List} of {@code possibleLocations}
     */
    public Cell chooseNextLocation(List<Cell> possibleLocations) {
        return possibleLocations.get(0);
    }
}
