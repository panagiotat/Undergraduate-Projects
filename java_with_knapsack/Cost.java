/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectb;

import java.util.ArrayList;

/**
 * In this class i count the amount of money that a client can give if we accept
 * his offer
 *
 */
public class Cost {

    /**
     *
     * @param ar1 the number of cores he want
     * @param ar2 the value that can give from each core
     * @return ar1*ar2 the ammount of money he will give if we accept his offer
     */
    public ArrayList<Double> Cost(ArrayList<Integer> ar1, ArrayList<Double> ar2) {
        ArrayList<Double> kostos = new ArrayList<Double>();

        for (int i = 0; i < ar1.size(); i++) {

            kostos.add((ar1.get(i) * ar2.get(i)));

        }

        return kostos;
    }

}
