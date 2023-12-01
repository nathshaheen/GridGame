import java.util.List;

public abstract class MoveStrategy {
    public abstract Cell chooseNextLocation(List<Cell> possibleLocations);
}
