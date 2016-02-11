package ch.daplab.google;

import ch.daplab.google.Algo.AlgoNaive;
import ch.daplab.google.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by vincent on 6/25/15.
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String args[]) throws Exception {
        Utils utils = new Utils();
        short[][] matrix = utils.loadData("logo.in");
        utils.printMatrix(matrix);
        List<String> listCmd=AlgoNaive.algoNaiveLine(matrix);
        utils.printListCommand(listCmd);
    }
}
