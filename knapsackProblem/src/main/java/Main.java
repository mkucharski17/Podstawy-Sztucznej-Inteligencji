

import com.google.common.primitives.Ints;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments!");
        } else {
            long start = System.currentTimeMillis();
            Environment environment = new Environment(Integer.parseInt(args[0]), args[1]);
            ArrayList<Item> answear = environment.findBestFit();
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
