package ch.daplab.google.dorwi.commands;

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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(droneId);
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
