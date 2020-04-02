

import com.google.common.primitives.Ints;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments!");
        } else {
            Environment environment = new Environment(Integer.parseInt(args[0]), args[1]);
            ArrayList<Item> answear = environment.findBestFit();
            System.out.println("rozmiar odpowiedzi = " + answear.size());
            for(Item item : answear)
                System.out.println(item.getWeight() + " " + item.getValue());
        }
    }
}
