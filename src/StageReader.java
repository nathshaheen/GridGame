import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StageReader {
    /**
     *  Read the stage file located in ./data/
     *
     * @param  path path to the stage file
     * @return      the {@code Stage} specified by the stage file
     */
    public static Stage readStage(String path) {
        Stage stage = new Stage();

        try {
            Properties propertiess = new Properties();
            propertiess.load(new FileInputStream(path));

            // Parse each line of the stage file
            for (String key : propertiess.stringPropertyNames()) {
                String value = propertiess.getProperty(key);
                Pattern cell = Pattern.compile("(.)(\\d+)");
                Pattern range = Pattern.compile("(.)(\\d+)->(.)(\\d+)");
                List<Cell> cellsInQuestion = new ArrayList<>();
                Matcher cellMatcher = cell.matcher(key);
                Matcher rangeMatcher = range.matcher(key);

                if (rangeMatcher.matches()) {   // Range specification
                    cellsInQuestion = stage.getGrid().getCellsInRange(rangeMatcher.group(1).charAt(0),
                            Integer.parseInt(rangeMatcher.group(2)),
                            rangeMatcher.group(3).charAt(0),
                            Integer.parseInt(rangeMatcher.group(4)));
                } else if (cellMatcher.matches()) { // Cell specification
                    stage.getGrid().getCellAtColumnRow(cellMatcher.group(1).charAt(0),
                            Integer.parseInt(cellMatcher.group(2))).ifPresent(cellsInQuestion::add);
                } else {
                    System.out.println("No match");
                }

                // Populate the stage with cells
                for (Cell c : cellsInQuestion) {
                    switch (value) {
                        case "wall" -> stage.getGrid().replaceCell(c, new Wall(c.getColumn(), c.getRow(), c.x, c.y));
                        case "oasis" -> stage.getGrid().replaceCell(c, new Oasis(c.getColumn(), c.getRow(), c.x, c.y));
                        case "palm tree" -> stage.getGrid().replaceCell(c, new PalmTree(c.getColumn(), c.getRow(), c.x, c.y));
                        case "sand" -> stage.getGrid().replaceCell(c, new Sand(c.getColumn(), c.getRow(), c.x, c.y));
                        case "dog red" -> stage.getActors().add(new Dog(c, 1.0f));
                        case "dog blue" -> stage.getActors().add(new Dog(c, 0.0f));
                        case "lion red" -> stage.getActors().add(new Lion(c, 1.0f));
                        case "lion blue" -> stage.getActors().add(new Lion(c, 0.0f));
                        case "rabbit red" -> stage.getActors().add(new Rabbit(c, 1.0f));
                        case "rabbit blue" -> stage.getActors().add(new Rabbit(c, 0.0f));
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return stage;
    }
}
