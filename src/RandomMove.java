import java.util.List;
import java.util.Random;

public class RandomMove extends MoveStrategy {
    private static Random random = new Random();

    /**
     *
     *
     * @param possibleLocations
     * @return
     */
    public Cell chooseNextLocation(List<Cell> possibleLocations) {
        return possibleLocations.get(random.nextInt(possibleLocations.size()));
    }
}
