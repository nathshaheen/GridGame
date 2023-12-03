import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Actor {
    private Cell location;
    private ArrayList<Polygon> display;
    private MoveStrategy strategy = new RandomMove();
    private float redness;
    private int turns;
    private int moves;
    private int range;

    /**
     * Constructor
     *
     * @param location the location of the {@code Actor}
     * @param redness  the redness} of the {@code Actor}
     */
    public Actor(Cell location, float redness) {
        this.location = location;
        this.redness = redness;
    }

    /**
     * Paint the {@code Actor}
     *
     * @param graphics where to paint
     */
    public void paint(Graphics graphics) {
        for (Polygon polygon : display) {
            graphics.setColor(new Color(redness, 0f ,1.0f - redness));
            graphics.fillPolygon(polygon);
            graphics.setColor(Color.GRAY);
            graphics.drawPolygon(polygon);
        }
    }

    public abstract void setPoly();

    /**
     * Add redness to the Actor, thus slowly changing their team
     *
     * @param amount the amount of redness to add
     */
    public void makeRedder(float amount) {
        redness += amount;
        if (redness > 1.0f) {
            redness = 1.0f;
        }
    }

    /**
     * Return if the {@code Actor} is on the red team
     *
     * @return returns true if the {@code Actor} is on the red team
     */
    public boolean isTeamRed() {
        return redness > 0.5f;
    }

    /*
     * GETTERS AND SETTERS
     */
    public Cell getLocation() {
        return location;
    }

    public void setLocation(Cell location) {
        this.location = location;

        // Assign a MoveStrategy
        if (this.location.getRow() % 2 == 0) {
            this.strategy = new RandomMove();
        } else {
            this.strategy = new LeftMostMove();
        }
        setPoly();
    }

    public ArrayList<Polygon> getDisplay() {
        return display;
    }

    public void setDisplay(ArrayList<Polygon> display) {
        this.display = display;
    }

    public MoveStrategy getStrategy() {
        return strategy;
    }

//    public void setStrategy(MoveStrategy strategy) {
//        this.strategy = strategy;
//    }

    public float getRedness() {
        return redness;
    }

//    public void setRedness(float redness) {
//        this.redness = redness;
//    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
