import java.awt.*;
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
     * Paint the {@code Stage}
     *
     * @param g             where to paint
     * @param mousePosition position of the mouse
     */
    public void paint(Graphics g, Point mousePosition) {
        if (currentState.equals(State.CPUMoving)) {
            for (Actor actor: actors) {
                if (!actor.isTeamRed()) {
                    List<Cell> possibleLocations = grid.getRadius(actor.getLocation(), actor.getMoves(), true);
                    Cell nextLocation = actor.getStrategy().chooseNextLocation(possibleLocations);
                    actor.setLocation(nextLocation);
                }
            }

            currentState = State.ChoosingActor;
            for (Actor actor : actors) {
                actor.setTurns(1);
            }
        } else if (currentState == State.ChoosingActor) {
            boolean turnsLeft = false;   // ADDED

            for (Actor actor : actors) {
                if (actor.getTurns() > 0 && actor.isTeamRed()) {
                    turnsLeft = true;
                }
            }

            if (!turnsLeft) {
                currentState = State.CPUMoving;
            }
        }

        grid.paint(g, mousePosition);
        grid.paintOverlay(g, cellOverlay, new Color(0.0f, 0.0f, 1.0f, 0.5f));

        for (Actor actor : actors) {
            actor.paint(g);
        }

        g.setColor(Color.DARK_GRAY);
        g.drawString(currentState.toString(), 720, 20);

        Optional<Cell> cellAtPoint = grid.cellsAtPoint(mousePosition);
        if (cellAtPoint.isPresent()) {
            Cell cellAtPointCell = cellAtPoint.get();
            g.setColor(Color.DARK_GRAY);
            g.drawString(cellAtPointCell.getColumn() + String.valueOf(cellAtPointCell.getRow()), 720, 50);
            g.drawString(cellAtPointCell.getType(), 820, 50);
            g.drawString("Crossing Time:", 720, 65);
            g.drawString(String.valueOf(cellAtPointCell.getCrossingTime()), 820, 65);
        }

        int yLocation = 138;
        for (int i = 0; i < actors.size(); i++) {
            Actor actor = actors.get(i);
            g.drawString(actor.getClass().toString(), 720, yLocation + 100 * i);
            g.drawString("Location:", 730, yLocation + 13 + 100 * i);
            g.drawString(actor.getLocation().getColumn() + Integer.toString(actor.getLocation().getRow()), 820, yLocation + 13+ 100 * i);
            g.drawString("Redness:", 730, yLocation + 26 + 100 * i);
            g.drawString(Float.toString(actor.getRedness()), 840, yLocation + 26 + 100 * i);
            g.drawString("Strategy:", 730, yLocation + 39 + 100 * i);
            g.drawString(actor.getStrategy().getClass().toString(), 840, yLocation + 39 + 100 * i);
            g.drawString("Turns:", 730, yLocation + 52 + 100 * i);
            g.drawString(String.valueOf(actor.getTurns()), 840, yLocation + 52 + 100 * i);
            g.drawString("Moves:", 730, yLocation + 65 + 100 * i);
            g.drawString(String.valueOf(actor.getMoves()), 840, yLocation + 65 + 100 * i);
            g.drawString("Range:", 730, yLocation + 78 + 100 * i);
            g.drawString(String.valueOf(actor.getRange()), 840, yLocation + 78 + 100 * i);
        }

        for (MenuItem menuItem : menuOverlay) {
            menuItem.paint(g);
        }
    }

//    TODO: Remove
//    /**
//     *
//     *
//     * @param from
//     * @param size
//     * @return
//     */
//    public List<Cell> getClearRadius(Cell from, int size) {
//        List<Cell> output = grid.getRadius(from, size, true);
//        for (Actor actor : actors) {
//            output.remove(actor.getLocation());
//        }
//        return output;
//    }

    /**
     *
     *
     * @param x
     * @param y
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
                Optional<Cell> clicked = Optional.empty();

                for (Cell cell : cellOverlay) {
                    if (cell.contains(x, y)) {
                        clicked = Optional.of(cell);
                    }
                }

                if (clicked.isPresent() && actorInAction.isPresent()) {
                    cellOverlay = new ArrayList<>();
                    actorInAction.get().setLocation(clicked.get());
                    actorInAction.get().setTurns(actorInAction.get().getTurns() - 1);
                    menuOverlay.add(new MenuItem("Fire", x, y, () -> {
                        cellOverlay = grid.getRadius(actorInAction.get().getLocation(), actorInAction.get().getRange(), true);
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
                        Optional<Actor> optionalActor = actorAt(cell);
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
     *
     *
     * @return
     */
    public Optional<Actor> actorAt(Cell cell) {
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

    /**
     *
     *
     * @return
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     *
     *
     * @param grid
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     *
     *
     * @return
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     *
     *
     * @param actors
     */
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    /**
     *
     *
     * @return
     */
    public List<MenuItem> getMenuOverlay() {
        return menuOverlay;
    }

    /**
     *
     *
     * @param menuOverlay
     */
    public void setMenuOverlay(List<MenuItem> menuOverlay) {
        this.menuOverlay = menuOverlay;
    }

    /**
     *
     *
     * @return
     */
    public Optional<Actor> getActorInAction() {
        return actorInAction;
    }

    /**
     *
     *
     * @param actorInAction
     */
    public void setActorInAction(Optional<Actor> actorInAction) {
        this.actorInAction = actorInAction;
    }
}
