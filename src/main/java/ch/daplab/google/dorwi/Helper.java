package ch.daplab.google.dorwi;

import ch.daplab.google.input.Container;
import ch.daplab.google.input.Order;
import ch.daplab.google.input.Warehouse;

import java.util.*;

/**
 * Created by dori on 11.02.16.
 */
public class Helper {


    public static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.ceil(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }


    public static List<Order> sortOrder(List<Order> orders, final int x, final int y) {
        int n = orders.size();
        Order[] orderArray = new Order[n];
        orderArray = orders.toArray(orderArray);

        Arrays.sort(orderArray, new Comparator<Order>() {
            @Override
            public int compare(Order order, Order t1) {
                int d1 = distance(order.getCoordCol(), order.getCoordRow(), x, y);
                int d2 = distance(t1.getCoordCol(), t1.getCoordRow(), x, y);
                if (d1 < d2) {
                    return -1;
                } else if (d1 > d2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return Arrays.asList(orderArray);
    }


    public static List<Warehouse> sortWarehouse(List<Warehouse> wareHouses, final int x, final int y) {
        int n = wareHouses.size();
        Warehouse[] wareHouseArray = new Warehouse[n];
        wareHouseArray = wareHouses.toArray(wareHouseArray);

        Arrays.sort(wareHouseArray, new Comparator<Warehouse>() {
            @Override
            public int compare(Warehouse order, Warehouse t1) {
                int d1 = distance(order.getCoordCol(), order.getCoordRow(), x, y);
                int d2 = distance(t1.getCoordCol(), t1.getCoordRow(), x, y);
                if (d1 < d2) {
                    return -1;
                } else if (d1 > d2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return Arrays.asList(wareHouseArray);
    }

    public static Map<Order, Warehouse> getClosestWarehouseForOrder(Container container) {

        List<Warehouse> warehouseList = container.getWarehouseList();
        List<ch.daplab.google.input.Order> orderList = container.getOrderList();

        Map<Order, Warehouse> map = new HashMap<>();
        for (ch.daplab.google.input.Order order : orderList) {
            List<Warehouse> warehouses = Helper.sortWarehouse(warehouseList, order.getCoordCol(), order.getCoordRow());
            map.put(order, warehouses.get(0));
        }

        return map;
    }

    public static Map<Warehouse, Order> getClosestOrderForWarehouse(Container container) {

        List<Warehouse> warehouseList = container.getWarehouseList();
        List<ch.daplab.google.input.Order> orderList = container.getOrderList();

        Map<Warehouse, Order> map = new HashMap<>();
        for (Warehouse warehouse : warehouseList) {
            List<Order> listOrder = Helper.sortOrder(orderList, warehouse.getCoordCol(), warehouse.getCoordRow());
            map.put(warehouse, listOrder.get(0));
        }

        return map;
    }
}
