package projectb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * In this class i open the file.txt and i save the main core, the core from
 * every customer and the values into an int and into arraylists
 */
public class PointClass {

    private ArrayList<Integer> pos1 = new ArrayList<Integer>(); // here i save the core from each customer
    private ArrayList<Double> pos2 = new ArrayList<Double>(); // here i save the value
    private int pirines; // here i save the main core

    PointClass() throws FileNotFoundException, IOException {

        Scanner scanner1 = new Scanner(new File("file.txt"));
        scanner1.useLocale(Locale.ENGLISH);
        int i = 0;

        while (scanner1.hasNextDouble()) {

            if (i == 0) {
                pirines = scanner1.nextInt();

            } else if (i % 2 == 1) {

                pos1.add(scanner1.nextInt());
            } else {
                pos2.add((scanner1.nextDouble()));
            }
            i++;
        }

    }

    /**
     * Here i return the number of cores that every customer want
     *
     */
    public ArrayList<Integer> returnAll1() {
        return pos1;
    }

    /**
     * Here i return the values
     *
     */
    public ArrayList<Double> returnAll2() {
        return pos2;
    }

    /**
     * Here i return the main core
     *
     */
    public int returnPirines() {
        return pirines;
    }

}
