package ch.daplab.google.dorwi;


import ch.daplab.google.input.Container;
import ch.daplab.google.input.Order;
import ch.daplab.google.input.Product;
import ch.daplab.google.input.Warehouse;

import java.util.*;

/**
 * Created by dori on 11.02.16.
 */
public class Helper {

    public static int drone_size;

    public static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.ceil(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }


    public static int getOrderWeight(Order order){
        int c = 0;
        for (Map.Entry<Product, Integer> product: order.getProductQty().entrySet()){
            c += product.getValue()*product.getKey().getWeight();
        }
        return c;
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
                double num1 = Math.ceil(getOrderWeight(order) /  drone_size);
                double num2 = Math.ceil(getOrderWeight(t1) /  drone_size);
                // TODO maybe the log ones should be processed in the end
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


    public static Warehouse sortWarehouse(List<Warehouse> wareHouses, Order order) {
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
                if (d1 < d2) {
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


    public static boolean canLoad(Warehouse warehouse, Order order) {
        for (Map.Entry<Product, Integer> entry : order.getProductQty().entrySet()) {
            if (warehouse.getMapQty().get(entry.getKey()) < entry.getValue()) {
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

    
    public static Map<Warehouse, List<Order>> getListOrderForWarehouse(Container container) {
        Map<Order, Warehouse> map = getClosestWarehouseForOrder(container);
        Map<Warehouse, List<Order>> mapListWarehouseOrder = new HashMap<>();


        for (Order order : map.keySet()) {
            if (!mapListWarehouseOrder.containsKey(map.get(order))) {
                List<Order> orderList = new ArrayList<>();
                orderList.add(order);
                mapListWarehouseOrder.put(map.get(order), orderList);
            } else {
                List<Order> orderList = mapListWarehouseOrder.remove(map.get(order));
                orderList.add(order);
                mapListWarehouseOrder.put(map.get(order), orderList);
            }

        }

        return mapListWarehouseOrder;
    }

    public static Map<Warehouse, List<Order>> getListOrderForWarehouseCanBeDeliver(Container container) {
        Map<Warehouse, List<Order>> mapListWarehouseOrder = getListOrderForWarehouse(container);
        Map<Warehouse, List<Order>> returnMapList = new HashMap<>();

        for (Warehouse warehouse : mapListWarehouseOrder.keySet()) {

            Map<Product, Integer> mapQty = new HashMap<>(warehouse.getMapQty());
            List<Order> orderWarehouse = mapListWarehouseOrder.get(warehouse);

            List<Order> orderToDeliver = new ArrayList<>();

            for (int i=0; i<orderWarehouse.size(); i++) {
                Order order = orderWarehouse.get(i);
                boolean toadd=true;
                for (Product product : order.getProductList()) {
                    if (mapQty.get(product) > 0) {
                        Integer qty = mapQty.remove(product);
                        mapQty.put(product, qty-1);
                    }
                    else{
                        toadd=false;
                    }
                }
                //we keep that order
                if(toadd) {
                    orderToDeliver.add(order);
                }
            }


            returnMapList.put(warehouse, orderToDeliver);
        }
        return returnMapList;
    }

    public static void LoadFromWareHouse(Warehouse w, Order o){
        for (Map.Entry<Product, Integer> entry: o.getProductQty().entrySet()){
            int num = w.getMapQty().get(entry.getKey());
            w.getMapQty().put(entry.getKey(), num - entry.getValue());
        }
    }
}
