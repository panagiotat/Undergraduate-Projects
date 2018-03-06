
package projecta;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *This is the main class. From here i call all the classes and 
 * i use them according to the correct sequence
 * 
 */
public class ProjectA {

   
    public static void main(String[] args) throws FileNotFoundException, IOException {

        OpenFile File = new OpenFile();

        Roads qh = new Roads();

        ArrayList<Point> p;
        ArrayList<Point> p1;
        p = qh.quickHull(File.OpenFile());
      

        FinalStep telos = new FinalStep();

        p1 = telos.FinalStep(p, File.returnBeginEnd());

        Diamonds diam = new Diamonds();

        int k = diam.Diamonds(File.diamant());

        System.out.println("The shortest distance is " + telos.apostasi());

        int i = 0;
        System.out.print("The shortest path is: ");

        System.out.print("(" + p1.get(i).x + " , " + p1.get(i).y + ")");
        for (i = 1; i < p1.size(); i++) {
            System.out.print("-->(" + p1.get(i).x + " , " + p1.get(i).y + ")");
        }
        System.out.println("");
        System.out.println("Number of weightings:  " + k);
       
    }

}
