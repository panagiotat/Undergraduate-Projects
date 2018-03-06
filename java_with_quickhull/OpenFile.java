package projecta;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * With these class i open the file and i save the points into a point arraylist
 * Before i begin i want to tell that the scanner.nextInt() made some mistakes
 * that is why i do it that way to be sure. Here i open the file.txt and with
 * scanner i read-save one by one in an arraylist integer one for the begin and
 * the end and one for the mines . I also save the number of diamonds in an int
 * diamanta. After i save all the numbers i put them two by two (except from the
 * diamonds) into 2 arraylists Points the arraylist points1 have all the points
 * and the begin-end the other have only the begin and the end.
 */
public class OpenFile {

    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Point> points1 = new ArrayList<Point>();
    ArrayList<Integer> pos = new ArrayList<Integer>();
    ArrayList<Integer> pos1 = new ArrayList<Integer>();

    /**
     * With this class i return the mines plus begin end.
     */
    private int diamantia;

    /**
     *Here i return the mines and the begin-end
     *
     */
    public ArrayList<Point> OpenFile() throws FileNotFoundException, IOException {

        Scanner scanner = new Scanner(new File("file.txt"));

        int i = 0;

        while (scanner.hasNextInt()) {
            if (i == 4) {
                diamantia = scanner.nextInt();
            } else {

                pos.add(scanner.nextInt());
            }

            i++;

        }
        int l = 0;
        for (int k = 2; k < pos.size(); k = k + 2) {

            Point e = new Point(pos.get(k - 2), pos.get(k - 1));
            points1.add(l, e);

            l++;
        }

        l = 0;

        for (int z = 0; z < 4; z = z + 2) {
            Point e = new Point(pos.get(z), pos.get(z + 1));

            points.add(l, e);
            l++;
        }

        return points1;
    }

    /**
     * Here i return only the begin and the end
     *
     *
     */
    public ArrayList<Point> returnBeginEnd() {
        return points;
    }

    /**
     * Here i return the diamonds
     *
     *
     */
    public int diamant() {
        return diamantia;
    }
}
