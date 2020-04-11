import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {

    public static void main(String[] args){
        if (args.length != 2) {
            System.err.println("Wrong number of arguments!");
        } else {
            long start = System.currentTimeMillis();
            BruteAlgorithm bruteAlgorithm = new BruteAlgorithm(Integer.parseInt(args[0]), args[1]);
            ArrayList<Item> answear = bruteAlgorithm.runAlgorithm();
            long time = System.currentTimeMillis() - start;
            System.out.println("Rozmiar odpowiedzi = " + answear.size());
            int summaryValue = 0, summaryWeight = 0;
            for(Item item : answear){
                System.out.println(item.getWeight() + " " + item.getValue());
                summaryValue += item.getValue();
                summaryWeight += item.getWeight();
            }
            System.out.println("Waga rozwiazania: " + summaryWeight + "\nWartosc rozwiazania: " + summaryValue);
            System.out.println("Czas dzialania: " + time);
        }
    }
}
