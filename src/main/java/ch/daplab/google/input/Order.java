package ch.daplab.google.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2/11/16.
 */
public class Order {
    private int coordRow;
    private int coordCol;
    private int id;
    private boolean delivered = false;
    private Map<Product, Integer> productQty = new HashMap<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        for (Product product: productList){
            Integer numb = productQty.get(product);
            if (numb == null){
                numb = 1;
            } else {
                numb += 1;
            }
            productQty.put(product, numb);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    private List<Product> productList;

    public Map<Product, Integer> getProductQty() {
        return productQty;
    }
}
