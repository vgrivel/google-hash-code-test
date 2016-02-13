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

import java.util.*;

/**
 * Created by vincent on 6/25/15.
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String args[]) throws Exception {
       Input input = new Input();
        //String fileName = "busy_day";
        //String fileName = "mother_of_all_warehouses";
        String fileName = "redundancy";
        Container container = input.loadData(fileName + ".in");
        Helper.drone_size = container.getDroneList().get(0).getCapacity();
        Warehouse w0 = container.getWarehouseList().get(0);
        List<Order> orders = Helper.sortOrder(container.getOrderList(), w0);

        List<Command> commands = new ArrayList<>();

        List<Drone> availableDrones = container.getDroneList();
        int i=0;
        for (Order order: orders){
            if (Helper.canLoad(w0, order)){
                Helper.LoadFromWareHouse(w0, order);
                Drone drone = availableDrones.get(i++ % availableDrones.size());
                int deliverableNumber = order.getProductQty().size();
                while (deliverableNumber > 0){
                    int capacity = drone.getCapacity();
                    LinkedList<Command> linkedCommands = new LinkedList<>();
/*
                     Map<Product, Integer> productQty = new TreeMap<Product, Integer>(new Comparator<Product>() {
                        @Override
                        public int compare(Product product, Product t1) {
                            return -Integer.valueOf(product.getWeight()).compareTo(t1.getWeight());
                        }
                    });
                    productQty.putAll(order.getProductQty());
*/
                    for (Map.Entry<Product, Integer> entry: order.getProductQty().entrySet()){
                        Product product = entry.getKey();
                        int num = entry.getValue();
                        int toLoad = num;
                        while (toLoad * product.getWeight() > capacity){
                            --toLoad;
                        }
                        capacity -= product.getWeight()* toLoad;
                        if (toLoad <= 0){
                            continue;
                        }
                        if (toLoad == num){
                            deliverableNumber--;
                            order.getProductQty().put(product, 0);
                        } else {
                            order.getProductQty().put(product, num-toLoad);
                        }
                        Load load = new Load(drone, w0, entry.getKey(), toLoad);
                        Deliver deliver = new Deliver(drone, order, entry.getKey(), toLoad);

                        linkedCommands.addFirst(load);
                        linkedCommands.addLast(deliver);
                    }
                    commands.addAll(linkedCommands);

                }
            }
        }

        Output output = new Output(fileName + ".out", commands);
        output.write();
    }
}
