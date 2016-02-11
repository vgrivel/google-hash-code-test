package ch.daplab.google.dorwi.commands;

import ch.daplab.google.input.Drone;
import ch.daplab.google.input.Product;
import ch.daplab.google.input.Warehouse;

/**
 * Created by dori on 11.02.16.
 */
public class Load extends Command {

    int warehouseId;
    int productId;
    int itemNr;

    public Load(int droneId, int warehouseId, int productId, int itemNr) {
        super(droneId);
        this.tag = 'L';
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.itemNr = itemNr;

    }

    public Load(Drone drone, Warehouse warehouse, Product product, int itemNr){
        this(drone.getId(), warehouse.getId(), product.getId(), itemNr);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(droneId);
        sb.append(" ");
        sb.append(tag);
        sb.append(" ");
        sb.append(warehouseId);
        sb.append(" ");
        sb.append(productId);
        sb.append(" ");
        sb.append(itemNr);
        return sb.toString();
    }


}
