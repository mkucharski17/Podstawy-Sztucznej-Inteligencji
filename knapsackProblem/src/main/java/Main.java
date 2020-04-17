import com.google.common.primitives.Ints;

import java.util.ArrayList;

/**
 * Main class of algorithm resolving backpack problem.
 * Project for Podstawy Sztucznej Inteligencji classes.
 *
 * @author Stanisław Czobot
 * @author Michał Kucharski
 * @version 17/4/2020
 */
public class Main {

    /**
     * Main method.
     * @param args Input arguments: capacity(int greater than 0), population size(int greater than 0), selection type('R' Roulette selection, 'B' BestN selection), input filename(.txt)
     */
    public static void main(String[] args) {
        if (checkArguments(args)) {
            long start = System.currentTimeMillis();
            Environment environment = new Environment(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2].charAt(0), args[3]);
            ArrayList<Item> answer = environment.findBestFit();
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
        if(args.length != 4){
            System.err.println("Wrong number of arguments!\nThere should be 4 arguments:\n\t1. Capacity as integer greater than 0" +
            "\n\t2. Population size as integer greater than 0\n\t3. Selection type([R] - Roulette selection or [B] - BestN selection)"+
                    "\n\t4. Input file name(.txt)");
            return false;
        } else if(Integer.parseInt(args[0]) <= 0){
            System.err.println("Wrong capacity!\nCapacity should be greater than 0.");
            return false;
        } else if(Integer.parseInt(args[1]) <= 0){
            System.err.println("Wrong population size!\nPopulation size should be greater than 0.");
            return false;
        } else if(args[2].charAt(0) != Population.ROULETTE_SELECTION && args[2].charAt(0) != Population.BESTN_SELECTION){
            System.err.println("Wrong selection type!\nUse:\n\tR - for Roulette selection\n\tB - for BestN selection");
            return false;
        } else if(args[3].length() <= 4){
            System.err.println("File name is to short!\nFile name should have at least 5 characters(e.g. f.txt)");
            return false;
        } else if(!args[3].substring(args[3].length()-4).equals(".txt")){
            System.err.println("Wrong file type!\nProgram works with .txt files.");
            return false;
        }
        return true;
    }
}
