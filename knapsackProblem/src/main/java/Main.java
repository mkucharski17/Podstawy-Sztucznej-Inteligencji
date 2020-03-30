

import com.google.common.primitives.Ints;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.err.println("Wrong number of arguments!");
//        } else {
//    }

            Environment environment = new Environment(3, "src/main/resources/Inputs/input.txt");
            ArrayList<Item> answear = environment.findBestFit();
            for(Item item : answear)
                System.out.println(item.getWeight() + " " + item.getValue());
    }
}
