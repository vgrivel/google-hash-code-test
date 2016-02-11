package ch.daplab.google.dorwi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dori on 11.02.16.
 */
public class Helper {


    public static int distance(int x1, int y1, int x2, int y2){
        return (int)Math.ceil(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }


    public static List<Order> sortOrder(List<Order> orders, final int x,final int y){
        int n = orders.size();
        Order[] orderArray = new Order[n];
        orderArray = orders.toArray(orderArray);

        Arrays.sort(orderArray, new Comparator<Order>() {
            @Override
            public int compare(Order order, Order t1) {
                int d1 = distance(order.x, order.y, x, y);
                int d2 = distance(t1.x, t1.y, x, y);
                if (d1 < d2){
                    return -1;
                } else if (d1 > d2){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return Arrays.asList(orderArray);
    }


    public static List<WareHouse> sortWarehouse(List<WareHouse> wareHouses, final int x, final int y){
        int n = wareHouses.size();
        WareHouse[] wareHouseArray = new WareHouse[n];
        wareHouseArray = wareHouses.toArray(wareHouseArray);

        Arrays.sort(wareHouseArray, new Comparator<WareHouse>() {
            @Override
            public int compare(WareHouse order, WareHouse t1) {
                int d1 = distance(order.x, order.y, x, y);
                int d2 = distance(t1.x, t1.y, x, y);
                if (d1 < d2){
                    return -1;
                } else if (d1 > d2){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return Arrays.asList(wareHouseArray);
    }
}
