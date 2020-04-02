import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.*;

public class BruteAlgorithm{
    private int capacity;
    private ArrayList<Item> items;

    private Node[] capabilities;

    private class Node{
        private int value;
        private boolean isInCapacity;
        Node(int value, boolean isInCapacity){ this.value = value; this.isInCapacity = isInCapacity; }
    }

    BruteAlgorithm(int capacity, String fileName){
        this.capacity = capacity;
        readValuesFromFile(fileName);
        capabilities = new Node[(int)pow(2,items.size())];
    }
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

    public ArrayList<Item> runAlgorithm(){
        calculateNodes();
        String bestPhenotype = findBestFit();
        ArrayList<Item> output = new ArrayList<Item>();
        for(int j = 0;j<bestPhenotype.length();j++){
            if(bestPhenotype.charAt(j) == '1'){
                output.add(items.get(bestPhenotype.length()-j-1));
            }
        }
        return output;
    }

    private void calculateNodes(){
        for(int i=0;i<capabilities.length;i++){
            if(i == 0){
                capabilities[i] = new Node(0,true);
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
                    capabilities[i] = new Node(value,true);
                else
                    capabilities[i] = new Node(value,false);
            }
        }
    }

    private String toBinaryString(int a, int length){
        if(length > 0)
        {
            return String.format("%" + length + "s", Integer.toBinaryString(a)).replaceAll(" ", "0");
        }
        return null;
    }

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