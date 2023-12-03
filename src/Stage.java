import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
    private Grid grid;
    private List<Actor> actors;
    private List<Cell> cellOverlay;
    private List<MenuItem> menuOverlay;
    private Optional<Actor> actorInAction;
    enum State {ChoosingActor, SelectingNewLocation, CPUMoving, SelectingMenuItem, SelectingTarget}
    State currentState;

    /**
     * Constructor
     */
    public Stage() {
        grid = new Grid();
        actors = new ArrayList<>();
        cellOverlay = new ArrayList<>();
        menuOverlay = new ArrayList<>();
        currentState = State.ChoosingActor;
    }

    /**
     * Paint the {@code Stage} based on the currentState of the game
     *
     * @param graphics      where to paint
     * @param mousePosition position of the mouse
     */
    public void paint(Graphics graphics, Point mousePosition) {
        if (currentState.equals(State.CPUMoving)) {
            // Move each CPU Actor according to their MoveStrategy
            for (Actor actor: actors) {
                if (!actor.isTeamRed()) {
                    List<Cell> possibleLocations = grid.getRadius(actor.getLocation(), actor.getMoves(), true);
                    Cell nextLocation = actor.getStrategy().chooseNextLocation(possibleLocations);
                    actor.setLocation(nextLocation);
                }
            }

            // Pass control to player
            currentState = State.ChoosingActor;
            for (Actor actor : actors) {
                actor.setTurns(1);
            }
        } else if (currentState == State.ChoosingActor) {
            // Check if players turn has ended
            boolean turnsLeft = false;
            for (Actor actor : actors) {
                if (actor.getTurns() > 0 && actor.isTeamRed()) {
                    turnsLeft = true;
                }
            }

            // Pass control to CPU
            if (!turnsLeft) {
                currentState = State.CPUMoving;
            }
        }

        // Grid
        grid.paint(graphics, mousePosition);

        // Cell overlay
        grid.paintOverlay(graphics, cellOverlay, new Color(0.0f, 0.0f, 1.0f, 0.5f));

        // Actors
        for (Actor actor : actors) {
            actor.paint(graphics);
        }

        // Current game state text
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString(currentState.toString(), 720, 20);

        // Currently moused overed Cell text
        Optional<Cell> cellAtPoint = grid.getCellAtPoint(mousePosition);
        if (cellAtPoint.isPresent()) {
            Cell cellAtPointCell = cellAtPoint.get();
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawString(cellAtPointCell.getColumn() + String.valueOf(cellAtPointCell.getRow()), 720, 50);
            graphics.drawString(cellAtPointCell.getType(), 820, 50);
            graphics.drawString("Crossing Time:", 720, 65);
            graphics.drawString(String.valueOf(cellAtPointCell.getCrossingTime()), 820, 65);
        }

        // Actor sidebar text
        int yLocation = 138;
        for (int i = 0; i < actors.size(); i++) {
            Actor actor = actors.get(i);
            graphics.drawString(actor.getClass().toString(), 720, yLocation + 100 * i);
            graphics.drawString("Location:", 730, yLocation + 13 + 100 * i);
            graphics.drawString(actor.getLocation().getColumn() + Integer.toString(actor.getLocation().getRow()), 820, yLocation + 13+ 100 * i);
            graphics.drawString("Redness:", 730, yLocation + 26 + 100 * i);
            graphics.drawString(Float.toString(actor.getRedness()), 840, yLocation + 26 + 100 * i);
            graphics.drawString("Strategy:", 730, yLocation + 39 + 100 * i);
            graphics.drawString(actor.getStrategy().getClass().toString(), 840, yLocation + 39 + 100 * i);
            graphics.drawString("Turns:", 730, yLocation + 52 + 100 * i);
            graphics.drawString(String.valueOf(actor.getTurns()), 840, yLocation + 52 + 100 * i);
            graphics.drawString("Moves:", 730, yLocation + 65 + 100 * i);
            graphics.drawString(String.valueOf(actor.getMoves()), 840, yLocation + 65 + 100 * i);
            graphics.drawString("Range:", 730, yLocation + 78 + 100 * i);
            graphics.drawString(String.valueOf(actor.getRange()), 840, yLocation + 78 + 100 * i);
        }

        // Menu
        for (MenuItem menuItem : menuOverlay) {
            menuItem.paint(graphics);
        }
    }

    /**
     * Handle mouse user input based on game state; {@code ChoosingActor}, {@code SelectingNewLocation}, {@code SelectingMenuItem} and {@code SelectingTarget}
     *
     * @param x the x coordinate of the mouse
     * @param y the y coordinate of the mouse
     */
    public void mouseClicked(int x, int y) {
        switch (currentState) {
            case ChoosingActor -> {
                actorInAction = Optional.empty();

                for (Actor actor : actors) {
                    if (actor.getLocation().contains(x, y) && actor.isTeamRed()) {
                        cellOverlay = grid.getRadius(actor.getLocation(), actor.getMoves(), true);
                        actorInAction = Optional.of(actor);
                        currentState = State.SelectingNewLocation;
                    }
                }

                if (actorInAction.isEmpty()) {
                    currentState = State.SelectingMenuItem;
                    menuOverlay.add(new MenuItem("Oops", x, y, () -> currentState = State.ChoosingActor));
                    menuOverlay.add(new MenuItem("End Turn", x, y + MenuItem.height, () -> currentState = State.CPUMoving));
                    menuOverlay.add(new MenuItem("End Game", x, y + MenuItem.height * 2, () -> System.exit(0)));

                }
            }
            case SelectingNewLocation -> {
                Optional<Cell> selectedCell = Optional.empty();

                for (Cell cell : cellOverlay) {
                    if (cell.contains(x, y)) {
                        selectedCell = Optional.of(cell);
                    }
                }

                if (selectedCell.isPresent() && actorInAction.isPresent()) {
                    cellOverlay = new ArrayList<>();
                    actorInAction.get().setLocation(selectedCell.get());
                    actorInAction.get().setTurns(actorInAction.get().getTurns() - 1);
                    menuOverlay.add(new MenuItem("Fire", x, y, () -> {
                        cellOverlay = grid.getRadius(actorInAction.get().getLocation(), actorInAction.get().getRange(), false);
                        cellOverlay.remove(actorInAction.get().getLocation());
                        currentState = State.SelectingTarget;
                    }));

                    currentState = State.SelectingMenuItem;
                }
            }
            case SelectingMenuItem ->  {
                for(MenuItem menuItem : menuOverlay) {
                    if (menuItem.contains(x, y)) {
                        menuItem.getAction().run();
                        menuOverlay = new ArrayList<>();
                    }
                }
            }
            case SelectingTarget -> {
                for (Cell cell : cellOverlay) {
                    if (cell.contains(x, y)) {
                        Optional<Actor> optionalActor = getActorAtCell(cell);
                        optionalActor.ifPresent(actor -> actor.makeRedder(0.1f));
                    }
                }
                cellOverlay = new ArrayList<>();
                currentState = State.ChoosingActor;
            }
            default -> System.out.println(currentState);
        }
    }

    /**
     * Return the {@code Actor} at the specified {@code Cell} if it exists
     *
     * @return the {@code Actor} at the specified {@code Cell} if it exists
     */
    public Optional<Actor> getActorAtCell(Cell cell) {
        for (Actor actor : actors) {
            if (actor.getLocation().equals(cell)) {
                return Optional.of(actor);
            }
        }
        return Optional.empty();
    }

    /*
     * GETTERS AND SETTERS
     */
    public Grid getGrid() {
        return grid;
    }

//    public void setGrid(Grid grid) {
//        this.grid = grid;
//    }

    public List<Actor> getActors() {
        return actors;
    }

//    public void setActors(List<Actor> actors) {
//        this.actors = actors;
//    }

//    public List<Cell> getCellOverlay() {
//        return cellOverlay;
//    }

//    public void setCellOverlay(List<Cell> cellOverlay) {
//        this.cellOverlay = cellOverlay;
//    }

//    public List<MenuItem> getMenuOverlay() {
//        return menuOverlay;
//    }

//    public void setMenuOverlay(List<MenuItem> menuOverlay) {
//        this.menuOverlay = menuOverlay;
//    }

//    public Optional<Actor> getActorInAction() {
//        return actorInAction;
//    }

//    public void setActorInAction(Optional<Actor> actorInAction) {
//        this.actorInAction = actorInAction;
//    }
}
