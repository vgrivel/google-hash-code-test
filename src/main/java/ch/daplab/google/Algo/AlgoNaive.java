package ch.daplab.google.Algo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vincent on 2/10/16.
 */
public class AlgoNaive {


    public static List<String> algoNaiveLine(short[][] matrix) {
        String l = "PAINT_LINE ";
        List<String> result = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    int refR = i;
                    int refC = j;

                    while(j < matrix[0].length && matrix[i][j]==1){
                        j++;
                    }
                    result.add(l + refR + " " + refC + " " + refR + " " +(j-1) );
                }


            }
        }

        return result;
    }
}
