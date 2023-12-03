import java.util.List;
import java.util.Random;

public class RandomMove extends MoveStrategy {
    private static Random random = new Random();

    /**
     * Return a random {@code Cell} from the {@code List} of possibleLocations
     *
     * @param possibleLocations a {@code List} of {@code Cells} to be selected from
     * @return                  a random {@code Cell} from the {@code List} of possibleLocations
     */
    public Cell chooseNextLocation(List<Cell> possibleLocations) {
        return possibleLocations.get(random.nextInt(possibleLocations.size()));
    }
}
