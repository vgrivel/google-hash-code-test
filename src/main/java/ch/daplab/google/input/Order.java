package ch.daplab.google.input;

import java.util.List;

/**
 * Created by vincent on 2/11/16.
 */
public class Order {
    private int coordRow;
    private int coordCol;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

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

    private List<Product> productList;
}
