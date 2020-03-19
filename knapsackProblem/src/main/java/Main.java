

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("Wrong number of arguments!");
            return;
        } else {
            Environment environment = new Environment(Integer.parseInt(args[0]),args[1]);
            ArrayList answear = environment.findBestMatch();
        }
    }
}
