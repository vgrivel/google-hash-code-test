package ch.daplab.google.input;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2/11/16.
 */
public class Input {

    private int row;
    private int col;

    public Container loadData(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        Container cont = new Container();
        short matrix[][] = {};
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine();
            Grid g = parseFirstLine(line); //parse first line
            List<Product> productList = parseProductType(br.readLine(), br.readLine());

            matrix = new short[row][col];

            //load warehouse

            List<Warehouse> warehouseList = parseWarehouse(br, productList);

            //load order
            List<Order> orderList = parseOrder(br, productList);


            cont.setGrid(g);
            cont.setOrderList(orderList);
            cont.setProductList(productList);
            cont.setWarehouseList(warehouseList);
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return cont;
    }

    public Grid parseFirstLine(String line1) {
        String[] dim = line1.split(" ");
        Grid g = new Grid();
        row = Integer.valueOf(dim[0]);
        col = Integer.valueOf(dim[1]);
        g.setRows(Integer.valueOf(dim[0]));
        g.setColumns(Integer.valueOf(dim[1]));
        g.setDrones(Integer.valueOf(dim[2]));
        g.setTurns(Integer.valueOf(dim[3]));
        g.setMaxP(Integer.valueOf(dim[4]));
        for (int i=0; i< g.getDrones(); ++i){
            g.getDroneList().add(new Drone(i, g.getMaxP()));
        }

        return g;
    }

    public List<Product> parseProductType(String l2, String l3) {
        int nbrProd = Integer.valueOf(l2);
        String[] prod = l3.split(" ");
        List<Product> listProd = new ArrayList<>();
        for (int i = 0; i < nbrProd; i++) {
            Product p = new Product();
            p.setId(i);
            p.setWeight(Integer.valueOf(prod[i]));
            listProd.add(p);
        }

        return listProd;
    }

    public List<Warehouse> parseWarehouse(BufferedReader br, List<Product> prodList) throws IOException {
        List<Warehouse> wareList = new ArrayList<>();
        String line4 = br.readLine();
        int nbrWarehouse = Integer.valueOf(line4);

        //for each warehouse
        for (int i = 0; i < nbrWarehouse; i++) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(i);
            String coord = br.readLine();
            String[] dim = coord.split(" ");
            warehouse.setCoordRow(Integer.valueOf(dim[0]));
            warehouse.setCoordCol(Integer.valueOf(dim[1]));
            String prod = br.readLine();
            String[] prodArray = prod.split(" ");
            Map<Product, Integer> mapQty = new HashMap<>();
            for (int j = 0; j < prodArray.length; j++) {
                mapQty.put(prodList.get(j), Integer.valueOf(prodArray[j]));
            }
            warehouse.setMapQty(mapQty);
            wareList.add(warehouse);
        }


        return wareList;
    }

    public List<Order> parseOrder(BufferedReader br, List<Product> prodList) throws IOException {
        List<Order> orderList = new ArrayList<>();
        String line4 = br.readLine();
        int nbrOrder = Integer.valueOf(line4);

        //for each warehouse
        for (int i = 0; i < nbrOrder; i++) {
            Order order = new Order();
            order.setId(i);
            String coord = br.readLine();
            String[] dim = coord.split(" ");
            order.setCoordRow(Integer.valueOf(dim[0]));
            order.setCoordCol(Integer.valueOf(dim[1]));
            String nbrItemStr = br.readLine();
            int nbrItem = Integer.valueOf(nbrItemStr);
            String o = br.readLine();
            String[] oArray = o.split(" ");
            List<Product> productList = new ArrayList<>();
            for (int j = 0; j < oArray.length; j++) {
                int itemId = Integer.valueOf(oArray[j]);
                productList.add(prodList.get(itemId));
            }
            order.setProductList(productList);
            orderList.add(order);
        }


        return orderList;
    }
}
