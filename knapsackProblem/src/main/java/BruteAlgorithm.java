import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.*;

/**
 * Brute force algorithm resolving backpack problem.
 */

public class BruteAlgorithm{
    /**
     * Capacity of backpack.
     */
    private int capacity;
    /**
     * List of items.
     */
    private ArrayList<Item> items;

    /**
     * Params of all possible gens capabilities.
     */
    private CapabilityParams[] capabilities;

    /**
     * Inside class representing params of one capability in our problem.
     */
    private class CapabilityParams {
        private int value;
        private boolean isInCapacity;
        CapabilityParams(int value, boolean isInCapacity){ this.value = value; this.isInCapacity = isInCapacity; }
    }

    /**
     * Brute force algorithm constructor.
     * @param capacity Capacity of backpack.
     * @param fileName File to read item values.
     */
    BruteAlgorithm(int capacity, String fileName){
        this.capacity = capacity;
        readValuesFromFile(fileName);
        capabilities = new CapabilityParams[(int)pow(2,items.size())];
    }

    /**
     * Method handling file operations(reading file and saving values).
     * @param fileName File name from which decoder should read values.
     */
    private void readValuesFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            items = new ArrayList<>();
            while (scanner.hasNextLine()) {
                int temp = scanner.nextInt();
                items.add(new Item(temp, scanner.nextInt()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * That method start algorithm.
     * @return Best phenotype found by algorithm.
     */
    public ArrayList<Item> runAlgorithm(){
        calculateCapabilityParams();
        String bestPhenotypeAsString = findBestFit();
        ArrayList<Item> bestPhenotype = new ArrayList<Item>();
        for(int j = 0;j<bestPhenotypeAsString.length();j++){
            if(bestPhenotypeAsString.charAt(j) == '1'){
                bestPhenotype.add(items.get(bestPhenotypeAsString.length()-j-1));
            }
        }
        return bestPhenotype;
    }

    /**
     * Calculate values of every possible genotype.
     */
    private void calculateCapabilityParams(){
        for(int i=0;i<capabilities.length;i++){
            if(i == 0){
                capabilities[i] = new CapabilityParams(0,true);
            }else{
                String combinationInBinary = toBinaryString(i,items.size());
                int value=0, weight=0;
                for(int j = 0;j<combinationInBinary.length();j++){
                    if(combinationInBinary.charAt(j) == '1'){
                        value += items.get(combinationInBinary.length()-j-1).getValue();
                        weight += items.get(combinationInBinary.length()-j-1).getWeight();
                    }
                }
                if(weight <= capacity)
                    capabilities[i] = new CapabilityParams(value,true);
                else
                    capabilities[i] = new CapabilityParams(value,false);
            }
        }
    }

    /**
     * Convert int value into String(which represent binary representation of given int and is given length long).
     * Example: toBinaryString(2,4) return "0010"
     * @param integerValue Value to convert.
     * @param length       Length of output String.
     * @return             String, which represent binary representation of given int and is given length long.
     */
    private String toBinaryString(int integerValue, int length){
        if(length > 0)
        {
            return String.format("%" + length + "s", Integer.toBinaryString(integerValue)).replaceAll(" ", "0");
        }
        return null;
    }

    /**
     * Finding best phenotype when all values are already calculated.
     * @return Best phenotype as String.
     */
    private String findBestFit(){
        int bestFit = 0;
        int bestFitIndex = 0;
        for(int i=0; i<capabilities.length; i++){
            if(capabilities[i].isInCapacity && capabilities[i].value >= bestFit){
                bestFit = capabilities[i].value;
                bestFitIndex=i;
            }
        }
        return toBinaryString(bestFitIndex,items.size());
    }
}