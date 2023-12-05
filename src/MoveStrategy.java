import java.util.List;

public interface MoveStrategy {
    Cell chooseNextLocation(List<Cell> possibleLocations);
}
