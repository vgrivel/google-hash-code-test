package ch.daplab.google.dorwi.commands;

import ch.daplab.google.input.Drone;
import ch.daplab.google.input.Product;
import ch.daplab.google.input.Warehouse;

/**
 * Created by dori on 11.02.16.
 */
public class Unload extends Load {


    public Unload(int droneId, int warehouseId, int productId, int itemNr) {
        super(droneId, warehouseId, productId, itemNr);
        this.tag = 'U';
    }

    public Unload(Drone drone, Warehouse warehouse, Product product, int itemNr){
        this(drone.getId(), warehouse.getId(), product.getId(), itemNr);
    }

}
