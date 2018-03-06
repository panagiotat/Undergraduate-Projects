
package projecta;

import java.util.Random;

/**
 * With this class i count the number of weightings that need to be done to find
 * the fake diamond. I divide the diamonds in three parts. The part one and the
 * part two are equal and they are diamonds/3 but the part three is the part one
 * + diamonds%3, so with the help of function zygos i found with logn weightings
 * the fake diamond
 *
 */
public class Diamonds {
    
    /**
     * Here i return the weightings
     * 
     * 
     */

    int Diamonds(int diamantia) {
        int n = 0;

        int ypoloipo;

        int m1;
        int m3;

        int k;

        while (diamantia != 1) {
            ypoloipo = diamantia % 3;

            m1 = diamantia / 3;

            m3 = (diamantia / 3) + ypoloipo;

            k = zygos();
            n++;
            if (k == 1 || k == -1) {
                diamantia = m1;

            } else {
                diamantia = m3;
            }

            if (diamantia == 2) {
                diamantia = 1;
                n++;
            }
        }
        return n;
    }

    /**
     * Here is the ready function zygos
     *
     *
     */
    int zygos() {
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(100);
        if (x < 34) {
            return 1;
        } else if (x < 67) {
            return 0;
        } else {
            return -1;
        }
    }

}
