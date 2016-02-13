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
        List<String> files = new ArrayList<>();
        files.add("busy_day");
        files.add("mother_of_all_warehouses");
        files.add("redundancy");
        for (String fileName: files){
            Container container = input.loadData(fileName + ".in");
            Helper.drone_size = container.getDroneList().get(0).getCapacity();

            List<Command> commands = new ArrayList<>();

            List<Drone> availableDrones = container.getDroneList();
            int i=0;

            for (Warehouse warehouse: container.getWarehouseList()){
                List<Order> orders = Helper.sortOrder(container.getOrderList(), warehouse);
                for (Order order: orders){
                    if (order.isDelivered()){
                        continue;
                    }
                    if (Helper.canLoad(warehouse, order)){
                        Helper.LoadFromWareHouse(warehouse, order);
                        order.setDelivered(true);
                        int deliverableNumber = order.getProductQty().size();
                        while (deliverableNumber > 0){
                            Drone drone = availableDrones.get(i++ % availableDrones.size());
                            int capacity = drone.getCapacity();
                            LinkedList<Command> linkedCommands = new LinkedList<>();
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
                                Load load = new Load(drone, warehouse, entry.getKey(), toLoad);
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
        }

}
