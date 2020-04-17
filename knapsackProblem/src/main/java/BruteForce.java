import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program resolving backpack problem using brute force algorithm.
 */

public class BruteForce {

    /**
     * Main method.
     * @param args Input arguments: capacity(int greater than 0), input filename(.txt)
     */
    public static void main(String[] args){
        if(checkArguments(args)){
            long start = System.currentTimeMillis();
            BruteAlgorithm bruteAlgorithm = new BruteAlgorithm(Integer.parseInt(args[0]), args[1]);
            ArrayList<Item> answer = bruteAlgorithm.runAlgorithm();
            long time = System.currentTimeMillis() - start;
            System.out.println("Rozmiar odpowiedzi = " + answer.size());
            int summaryValue = 0, summaryWeight = 0;
            for(Item item : answer){
                System.out.println(item.getWeight() + " " + item.getValue());
                summaryValue += item.getValue();
                summaryWeight += item.getWeight();
            }
            System.out.println("Waga rozwiazania: " + summaryWeight + "\nWartosc rozwiazania: " + summaryValue);
            System.out.println("Czas dzialania: " + time);
        }
    }

    /**
     * Check all input arguments if they are in correct form.
     * That method secures algorithm from getting incorrect start values.
     * @param args Table of arguments to check correctness.
     * @return True if all arguments are correct, algorithm can be safely run.
     */
    private static boolean checkArguments(String[] args){
        if(args.length != 2){
            System.err.println("Wrong number of arguments!\nThere should be 2 arguments:\n\t1. Capacity as integer greater than 0" +
                    "\n\t2. Input file name(.txt)");
            return false;
        } else if(Integer.parseInt(args[0]) <= 0){
            System.err.println("Wrong capacity!\nCapacity should be greater than 0.");
            return false;
        }  else if(args[1].length() <= 4){
            System.err.println("File name is to short!\nFile name should have at least 5 characters(e.g. f.txt)");
            return false;
        } else if(!args[1].substring(args[1].length()-4).equals(".txt")){
            System.err.println("Wrong file type!\nProgram works with .txt files.");
            return false;
        }
        return true;
    }
}
