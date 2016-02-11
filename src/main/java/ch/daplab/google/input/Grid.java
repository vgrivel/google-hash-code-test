package ch.daplab.google.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincent on 2/11/16.
 */
public class Grid {
    private int rows;
    private int columns;
    private int drones;
    private int turns;
    private int maxP;
    private List<Drone> droneList = new ArrayList<>();

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getDrones() {
        return drones;
    }

    public void setDrones(int drones) {
        this.drones = drones;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getMaxP() {
        return maxP;
    }

    public void setMaxP(int maxP) {
        this.maxP = maxP;
    }

    public List<Drone> getDroneList() {
        return droneList;
    }

    public void setDroneList(List<Drone> droneList) {
        this.droneList = droneList;
    }
}
