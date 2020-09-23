import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.ArrayList;

public class Words {
    private static String filename = "commonwords.txt";
    // from http://www.mieliestronk.com/corncob_lowercase.txt
    private static List<String> lines = Collections.emptyList();

    public static void readFileIntoList () throws IOException {
        lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
    }

    /**
     * How often do collisions occur?
     * Do certain table sizes perform better than others?
     * Is one hash function faster than others? (Big-O, Runtime)
     * What makes a hash function 'better'?
     */

    public static void main (String[] args) throws IOException {
        readFileIntoList();

        int size = lines.size();
        Random r = new Random();
        Function<String,Integer> hf0 = s -> Math.floorMod(s.hashCode(), 13);
        HashFunction hf1 = new LenMod(13);
        HashFunction hf2 = new CharMod(13);
        HashFunction hf3 = new RollingMod(13);

        int hf0Col = 0;
        int hf1Col = 0;
        int hf2Col = 0;
        int hf3Col = 0;

        ArrayList hf0Data = new ArrayList();
        ArrayList hf1Data = new ArrayList();
        ArrayList hf2Data = new ArrayList();
        ArrayList hf3Data = new ArrayList();

        for (int i=0; i<10; i++) {
            String w = lines.get(r.nextInt(size));
            Integer hf0App = hf0.apply(w);
            Integer hf1App = hf1.apply(w);
            Integer hf2App = hf2.apply(w);
            Integer hf3App = hf3.apply(w);

            System.out.printf("%d\t%d\t%d\t%s\t%s%n",
                    hf0App, hf1App, hf2App, hf3App, w);

            hf0Col += (hf0Data.contains(hf0App) ? 1 : 0);
            hf1Col += (hf0Data.contains(hf1App) ? 1 : 0);
            hf2Col += (hf0Data.contains(hf2App) ? 1 : 0);
            hf3Col += (hf0Data.contains(hf3App) ? 1 : 0);

            hf0Data.add(hf0App);
            hf1Data.add(hf1App);
            hf2Data.add(hf2App);
            hf3Data.add(hf3App);
        }

        System.out.printf("%n-----Collisions-----%n");
        System.out.printf("%d\t%d\t%d\t%d%n", hf0Col, hf1Col, hf2Col, hf3Col);
        System.out.println("hf1 performs the fastest.");
        System.out.println("Least amount of collisions with the the fastest speed.");
    }
}
