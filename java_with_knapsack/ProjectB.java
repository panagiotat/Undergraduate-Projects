// Giorgos Panagiotatos 2627 panagiotat@csd.auth.gr
package projectb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Here is the main. Here i call the classes and i print what is neccesary to
 * fit the requested
 *
 */
public class ProjectB {

    public static void main(String[] args) throws IOException {

        ArrayList<Integer> meg = new ArrayList<Integer>(); //*Here is the number of cores that every customer want

        ArrayList<Double> meg1 = new ArrayList<Double>(); // Here is the values

        Map<Integer, Integer> map = new HashMap<>(); // this was neccesary for the class
        int coins[] = {1, 2, 7, 11}; // this is the available cores
        Plithos cc = new Plithos();

        PointClass Save = new PointClass();

        meg = Save.returnAll1();
        int m = 1;
        for (int i = 0; i < meg.size(); i++) {

            cc.minimumCoinBottomUp(meg.get(i), coins, m);
            m++;

        }
        Cost K = new Cost();
        meg1 = K.Cost(Save.returnAll1(), Save.returnAll2());

        Knapsack Ypol = new Knapsack();
        int j = 0;

        Ypol.solve(meg, meg1, Save.returnPirines(), Save.returnAll1().size());
        double n = 0;

        for (int l = 0; l < Ypol.cl.size(); l++) {
            n = n + meg1.get(Ypol.cl.get(l) - 1);

        }
        System.out.println("Total amount : " + n);

    }

}
