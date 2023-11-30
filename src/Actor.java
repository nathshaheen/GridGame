import java.awt.*;
import java.util.ArrayList;

public abstract class Actor {
    private Cell location;
    private ArrayList<Polygon> display;
    private MoveStrategy strategy = new RandomMove();
    private float redness;
    private int turns;
    private int moves;
    private int range;

    public Actor(Cell location, float redness) {
        this.location = location;
        this.redness = redness;
    }

    /**
     *
     *
     * @param g
     */
    public void paint(Graphics g) {
        for (Polygon polygon : display) {
            g.setColor(new Color(redness, 0f ,1.0f - redness));
            g.fillPolygon(polygon);
            g.setColor(Color.GRAY);
            g.drawPolygon(polygon);
        }
    }

    public abstract void setPoly();

    /**
     *
     *
     * @param amount
     */
    public void makeRedder(float amount) {
        redness += amount;
        if (redness > 1.0f) {
            redness = 1.0f;
        }
    }

    public boolean isTeamRed() {
        return redness > 0.5f;
    }

    /*
     * GETTERS AND SETTERS
     */

    /**
     *
     *
     * @return
     */
    public ArrayList<Polygon> getDisplay() {
        return display;
    }

    /**
     *
     *
     * @param display
     */
    public void setDisplay(ArrayList<Polygon> display) {
        this.display = display;
    }

    /**
     *
     *
     * @return
     */
    public Cell getLocation() {
        return location;
    }

    /**
     *
     *
     * @param location
     */
    public void setLocation(Cell location) {
        this.location = location;
        if (this.location.getRow() % 2 == 0) {
            this.strategy = new RandomMove();
        } else {
            this.strategy = new LeftMostMove();
        }
        setPoly();
    }

    /**
     *
     *
     * @return
     */
    public float getRedness() {
        return redness;
    }

    /**
     *
     *
     * @param redness
     */
    public void setRedness(float redness) {
        this.redness = redness;
    }

    /**
     *
     *
     * @return
     */
    public int getTurns() {
        return turns;
    }

    /**
     *
     *
     * @param turns
     */
    public void setTurns(int turns) {
        this.turns = turns;
    }

    /**
     *
     *
     * @return
     */
    public int getMoves() {
        return moves;
    }

    /**
     *
     *
     * @param moves
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     *
     *
     * @return
     */
    public int getRange() {
        return range;
    }

    /**
     *
     *
     * @param range
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     *
     *
     * @return
     */
    public MoveStrategy getStrategy() {
        return strategy;
    }

    /**
     *
     *
     * @param strategy
     */
    public void setStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
    }
}
