package ch.daplab.google.dorwi.commands;

import ch.daplab.google.input.Drone;
import ch.daplab.google.input.Order;
import ch.daplab.google.input.Product;
import ch.daplab.google.input.Warehouse;

/**
 * Created by dori on 11.02.16.
 */
public class Deliver extends Command {

    int orderId;
    int productId;
    int itemNr;


    public Deliver(int droneId, int orderId, int productId, int itemNr) {
        super(droneId);
        this.orderId = orderId;
        this.productId = productId;
        this.itemNr = itemNr;
        this.tag = 'D';
    }


    public Deliver(Drone drone, Order order, Product product, int itemNr){
        this(drone.getId(), order.getId(), product.getId(), itemNr);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(droneId);
        sb.append(" ");
        sb.append(tag);
        sb.append(" ");
        sb.append(orderId);
        sb.append(" ");
        sb.append(productId);
        sb.append(" ");
        sb.append(itemNr);
        return sb.toString();
    }
}
