import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {

    public static void main(String[] args){
        if (args.length != 2) {
            System.err.println("Wrong number of arguments!");
        } else {
            BruteAlgorithm bruteAlgorithm = new BruteAlgorithm(Integer.parseInt(args[0]), args[1]);
            ArrayList<Item> answear = bruteAlgorithm.runAlgorithm();
            System.out.println("rozmiar odpowiedzi = " + answear.size());
            for(Item item : answear)
                System.out.println(item.getWeight() + " " + item.getValue());
        }
    }
}
