

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("Wrong number of arguments!");
        } else {
            Environment environment = new Environment(Integer.parseInt(args[0]),args[1]);
            ArrayList<Item> answear = environment.findBestMatch();
        }
    }
}
