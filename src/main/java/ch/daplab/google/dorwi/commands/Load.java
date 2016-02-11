package ch.daplab.google.dorwi.commands;

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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(droneId);
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
