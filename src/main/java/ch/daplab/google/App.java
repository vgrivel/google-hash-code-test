package ch.daplab.google;

import ch.daplab.google.dorwi.Helper;
import ch.daplab.google.input.Container;
import ch.daplab.google.input.Input;
import ch.daplab.google.input.Order;
import ch.daplab.google.input.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by vincent on 6/25/15.
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String args[]) throws Exception {
        Input input = new Input();
        Container container = input.loadData("busy_day.in");



    }
}
