import java.util.List;

public class LeftMostMove implements MoveStrategy {
    /**
     * Return the left-most {@code Cell} from the {@code List} of possibleLocations
     *
     * @param possibleLocations a {@code List} of {@code Cells} to be selected from
     * @return                  the left-most {@code Cell} from the {@code List} of possibleLocations
     */
    @Override
    public Cell chooseNextLocation(List<Cell> possibleLocations) {
        Cell leftMost = possibleLocations.get(0);
        for (Cell cell : possibleLocations) {
            if (cell.leftOfComparison(leftMost) < 0) {
                leftMost = cell;
            }
        }
        return leftMost;
    }
}
