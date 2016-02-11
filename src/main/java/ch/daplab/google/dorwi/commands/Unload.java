package ch.daplab.google.dorwi.commands;

/**
 * Created by dori on 11.02.16.
 */
public class Unload extends Load {


    public Unload(int droneId, int warehouseId, int productId, int itemNr) {
        super(droneId, warehouseId, productId, itemNr);
        this.tag = 'U';
    }
}
