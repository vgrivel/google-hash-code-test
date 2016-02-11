package ch.daplab.google.input;

import java.util.Map;

/**
 * Created by vincent on 2/11/16.
 */
public class Warehouse {


    public int getCoordRow() {
        return coordRow;
    }

    public void setCoordRow(int coordRow) {
        this.coordRow = coordRow;
    }

    public int getCoordCol() {
        return coordCol;
    }

    public void setCoordCol(int coordCol) {
        this.coordCol = coordCol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Map<Product, Integer> getMapQty() {
        return mapQty;
    }

    public void setMapQty(Map<Product, Integer> mapQty) {
        this.mapQty = mapQty;
    }

    private Map<Product, Integer> mapQty;
    private int coordCol;
    private int id;
    private int coordRow;

}
