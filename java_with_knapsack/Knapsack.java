/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectb;

import java.util.ArrayList;

/**
 * This class i took it ready from the internet it implements knapsack algorithm
 * but i have changed it so it can receive arraylists. The basic difference from
 * the original algorithm is that arraylists and the arrays are using different
 * pointer but i also have changed a little the printing format to fit the
 * requested
 *
 * http://www.sanfoundry.com/java-program-knapsack-algorithm/
 */
public class Knapsack {

    ArrayList<Integer> cl = new ArrayList<Integer>(); // Here i save the accepted clients

    /**
     *
     * @param wt this is the number of cores that customer want
     * @param val this is actually the number of cores (that customer want) *
     * the money that he is ready to give
     * @param W this is the main core
     * @param N this is the number of customers that give us the offer
     */
    public void solve(ArrayList<Integer> wt, ArrayList<Integer> val, int W, int N) {

        int NEGATIVE_INFINITY = Integer.MIN_VALUE;

        double[][] m = new double[N + 1][W + 1];

        double[][] sol = new double[N + 1][W + 1];

        int k = 0;

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j <= W; j++) {

                double m1 = m[i - 1][j];

                double m2 = NEGATIVE_INFINITY;

                if (j >= wt.get(k)) {
                    m2 = (m[i - 1][j - wt.get(k)] + val.get(k));
                }

                /**
                 * select max of m1, m2 *
                 */
                m[i][j] = Math.max(m1, m2);

                sol[i][j] = m2 > m1 ? 1 : 0;

            }
            k++;
        }

        /**
         * make list of what all items to finally select *
         */
        int[] selected = new int[N + 1];
        k = N - 1;
        for (int n = N, w = W; n > 0; n--) {

            if (sol[n][w] != 0) {

                selected[n] = 1;

                w = w - wt.get(k);

            } else {
                selected[n] = 0;
            }
            k--;

        }

        /**
         * Print finally selected items
         *
         */
        System.out.print("\nClients accepted : ");

        for (int i = 1; i < N + 1; i++) {

            if (selected[i] == 1) {
                cl.add(i);

            }
        }
        System.out.print(cl.get(0));
        for (int t = 1; t < cl.size(); t++) {
            System.out.print(", " + cl.get(t));

        }

        System.out.println();

    }

    /**
     * In this class i return the accepted customers
     *
     * @return
     */
    public ArrayList<Integer> returnClients() {

        return cl;
    }
}
