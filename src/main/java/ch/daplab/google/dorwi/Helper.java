package ch.daplab.google.dorwi;


import ch.daplab.google.input.*;

import java.util.*;

/**
 * Created by dori on 11.02.16.
 */
public class Helper {


    public static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.ceil(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }


    public static List<Order> sortOrder(List<Order> orders, Warehouse warehouse){
        final int x = warehouse.getCoordCol();
        final int y = warehouse.getCoordRow();

        int n = orders.size();
        Order[] orderArray = new Order[n];
        orderArray = orders.toArray(orderArray);

        Arrays.sort(orderArray, new Comparator<Order>() {
            @Override
            public int compare(Order order, Order t1) {
                int d1 = distance(order.getCoordCol(), order.getCoordRow(), x, y);
                int d2 = distance(t1.getCoordCol(), t1.getCoordRow(), x, y);
                if (d1 < d2){
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


    public static Warehouse sortWarehouse(List<Warehouse> wareHouses, Order order){
        final int x = order.getCoordCol();
        final int y = order.getCoordRow();
        int n = wareHouses.size();
        Warehouse[] wareHouseArray = new Warehouse[n];
        wareHouseArray = wareHouses.toArray(wareHouseArray);

        Arrays.sort(wareHouseArray, new Comparator<Warehouse>() {
            @Override
            public int compare(Warehouse order, Warehouse t1) {
                int d1 = distance(order.getCoordCol(), order.getCoordRow(), x, y);
                int d2 = distance(t1.getCoordCol(), t1.getCoordRow(), x, y);
                if (d1 < d2){
                    return -1;
                } else if (d1 > d2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return wareHouseArray[0];
    }



    public static boolean canLoad(Warehouse warehouse, Order order){
        for (Map.Entry<Product, Integer> entry: order.getProductQty().entrySet()){
            if (warehouse.getMapQty().get(entry.getKey()) < entry.getValue()){
                return false;
            }
        }
        return true;
    }

    public static Map<Order, Warehouse> getClosestWarehouseForOrder(Container container) {

        List<Warehouse> warehouseList = container.getWarehouseList();
        List<ch.daplab.google.input.Order> orderList = container.getOrderList();

        Map<Order, Warehouse> map = new HashMap<>();
        for (ch.daplab.google.input.Order order : orderList) {
            Warehouse warehouses = Helper.sortWarehouse(warehouseList, order);
            map.put(order, warehouses);
        }

        return map;
    }

    public static Map<Warehouse, Order> getClosestOrderForWarehouse(Container container) {

        List<Warehouse> warehouseList = container.getWarehouseList();
        List<ch.daplab.google.input.Order> orderList = container.getOrderList();

        Map<Warehouse, Order> map = new HashMap<>();
        for (Warehouse warehouse : warehouseList) {
            List<Order> listOrder = Helper.sortOrder(orderList, warehouse);
            map.put(warehouse, listOrder.get(0));
        }

        return map;
    }
}
