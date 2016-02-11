package ch.daplab.google;

import ch.daplab.google.Algo.AlgoNaive;
import ch.daplab.google.dorwi.Helper;
import ch.daplab.google.dorwi.commands.Command;
import ch.daplab.google.dorwi.commands.Deliver;
import ch.daplab.google.dorwi.commands.Load;
import ch.daplab.google.input.*;
import ch.daplab.google.output.Output;
import ch.daplab.google.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 6/25/15.
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String args[]) throws Exception {
       Input input = new Input();
        //String fileName = "busy_day";
        String fileName = "mother_of_all_warehouses";
        //String fileName = "redundancy";
        Container container = input.loadData(fileName + ".in");
        Warehouse w0 = container.getWarehouseList().get(0);
        List<Order> orders = Helper.sortOrder(container.getOrderList(), w0);


        List<Command> commands = new ArrayList<>();

        List<Drone> availableDrones = container.getDroneList();
        for (Order order: orders){
            if (Helper.canLoad(w0, order)){
                Drone drone = availableDrones.get(0);
                for (Map.Entry<Product, Integer> entry: order.getProductQty().entrySet()){
                    Load load = new Load(drone, w0, entry.getKey(), entry.getValue());
                    commands.add(load);
                }
                for (Map.Entry<Product, Integer> entry: order.getProductQty().entrySet()){
                    Deliver deliver = new Deliver(drone, order, entry.getKey(), entry.getValue());
                    commands.add(deliver);
                }
                break;
            }
        }

        Output output = new Output(fileName + ".out", commands);
        output.write();
    }
}
