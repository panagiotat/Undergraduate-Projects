package projecta;

import java.awt.Point;
import java.util.ArrayList;

/**
 * In this class i give the arraylist Points of the convex wall from the quick
 * hull, and the other arraylist Points has begin and end. Here is the final
 * class of the projectA. With comparisons and while i find the two paths, the
 * distance and the best path. I noticed that the convex wall that comes out
 * from the quick Hull It always finishes at the end and the begin is somewhere
 * in the middle. The up way was easy to find out after the begin is the up path
 * till the treasure. But to find out the down path is to take the opposite way
 * from the begin to the first point of the convex wall and add the treasure.
 * Then i thought that the convex wall is in someway a circle so i understood
 * why that happens.
 *
 *
 */
public class FinalStep {

    private ArrayList<Point> telikoP = new ArrayList<Point>();
    private ArrayList<Point> telikoK = new ArrayList<Point>();
    private double apostasiP = 0;
    private double apostasiK = 0;
/**
 *I return the best path 
 * 
 *  
 */
    
    
    ArrayList<Point> FinalStep(ArrayList<Point> mines, ArrayList<Point> pt) {

        Point thesi = new Point(pt.get(0).x, pt.get(0).y);
        Point thisauros = new Point(pt.get(1).x, pt.get(1).y);

        /**
         * Here is the first path
         */
        telikoP.add(thesi);
        /**
         * Here is the second path
         */
        telikoK.add(thesi);

        int k = 0;
        boolean flag = true;
        while (flag == true) {

            Point e = new Point(mines.get(k).x, mines.get(k).y);
            if (e.x == thesi.x && e.y == thesi.y) {
                flag = false;
            } else {
                k++;
            }

        }
        for (int t = k - 1; t >= 0; t--) {
            Point e = new Point(mines.get(t).x, mines.get(t).y);
            telikoK.add(e);

        }

        telikoK.add(thisauros);

        for (int j = k + 1; j < mines.size(); j++) {
            Point e = new Point(mines.get(j).x, mines.get(j).y);

            telikoP.add(e);
        }

        ypolApostasi();

        /**
         * Here returns the best path
         *
         */
        if (apostasiP < apostasiK) {
            return telikoP;
        } else {
            return telikoK;
        }
    }

    /**
     * Here i meisure the distance
     *
     */
    public void ypolApostasi() {

        double apostasi1 = 0;
        double apostasi2 = 0;

        for (int i = 1; i < telikoP.size(); i++) {
            apostasi1 = Math.sqrt(Math.pow((telikoP.get(i).x - telikoP.get(i - 1).x), 2) + Math.pow(telikoP.get(i).y - telikoP.get(i - 1).y, 2));

            apostasiP = apostasiP + apostasi1;
        }

        for (int i = 1; i < telikoK.size(); i++) {

            apostasi2 = Math.sqrt(Math.pow((telikoK.get(i).x - telikoK.get(i - 1).x), 2) + Math.pow(telikoK.get(i).y - telikoK.get(i - 1).y, 2));

            apostasiK = apostasiK + apostasi2;
        }

    }
     /**
     * Here returns the distance
     *
     */

    public double apostasi() {

        if (apostasiP < apostasiK) {
            return apostasiP;
        } else {
            return apostasiK;
        }
    }

}
