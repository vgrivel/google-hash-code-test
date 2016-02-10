package ch.daplab.google.utils;

import java.io.*;
import java.util.List;

/**
 * Created by vincent on 2/10/16.
 */
public class Utils {
    public short[][] loadData(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        short matrix[][] = {};
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine();

            String[] dim = line.split(" ");
            int rows = Integer.valueOf(dim[0]);
            int columns = Integer.valueOf(dim[1]);
            matrix = new short[rows][columns];
            int r = 0;

            while ((line = br.readLine()) != null) {
                for (int c = 0; c < columns; c++) {
                    char ch = line.charAt(c);
                    if (ch == '.') {
                        matrix[r][c] = 0;
                    } else if (ch == '#') {
                        matrix[r][c] = 1;
                    } else {
                        System.err.println("Error while decoding input data at r:" + r + " c:" + c);
                    }

                }
                r++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return matrix;
    }

    public void printMatrix(short[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void printListCommand(List<String> listCmd){

        for(String s: listCmd){
            System.out.println(s);
        }
    }

}
