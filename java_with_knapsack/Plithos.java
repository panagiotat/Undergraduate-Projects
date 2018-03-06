package projectb;

import java.util.ArrayList;
import java.util.Map;

/**
 * And this class i took it ready from the internet it implements the coin
 * change maker algorithm I also changed it to receive arraylists and i changed
 * the printCoinCombination function to print with right format
 *
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CoinChangingMinimumCoin.java
 */
public class Plithos {

    /**
     * Bottom up way of solving this problem. Keep input sorted. Otherwise
     * temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which can be very low
     * negative number Returns Integer.MAX_VALUE - 1 if solution is not
     * possible.
     *
     * @param total here is the number of cores that the client want
     * @param coins here is the number available cores
     * @param k here is the number of the client
     *
     */
    public void minimumCoinBottomUp(int total, int coins[], int k) {
        int T[] = new int[total + 1];
        int R[] = new int[total + 1];
        T[0] = 0;
        for (int i = 1; i <= total; i++) {
            T[i] = Integer.MAX_VALUE - 1;
            R[i] = -1;
        }
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= total; i++) {
                if (i >= coins[j]) {
                    if (T[i - coins[j]] + 1 < T[i]) {
                        T[i] = 1 + T[i - coins[j]];
                        R[i] = j;
                    }
                }
            }
        }
        printCoinCombination(R, coins, k);

    }

    /**
     * In this function i print with the right format the combination of cores
     * from every single client
     *
     * @param R is the array that find the combination
     * @param coins is the available cores
     * @param i the number of the client
     */
    public void printCoinCombination(int R[], int coins[], int i) {
        int n1 = 0;
        int n2 = 0;
        int n7 = 0;
        int n11 = 0;

        ArrayList<Integer> num = new ArrayList<Integer>();

        int start = R.length - 1;
        System.out.print("Client " + i + ": ");
        while (start != 0) {
            int j = R[start];

            num.add(coins[j]);

            start = start - coins[j];
        }
        for (int k = 0; k < num.size(); k++) {
            if (num.get(k) == 11) {
                n11++;
            } else if (num.get(k) == 7) {
                n7++;

            } else if (num.get(k) == 2) {
                n2++;
            } else {
                n1++;
            }

        }

        System.out.print(+n1 + " 1-core,");
        System.out.print(" " + n2 + " 2-core,");
        System.out.print(" " + n7 + " 7-core,");
        System.out.print(" " + n11 + " 11-core VMs");

        System.out.print("\n");

    }
}
