import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Decoder {
    private ArrayList<Item> items;

    public Decoder(String fileName) {
        readValuesFromFile(fileName);
    }

    private boolean readValuesFromFile(String fileName){
        try {
            Scanner scanner = new Scanner(new File(fileName));
            items = new ArrayList<Item>();
            while(scanner.hasNextLine()){
                int temp = scanner.nextInt();
                items.add(new Item(temp, scanner.nextInt()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        for(Item item : items){
            System.out.println(item.getWeight() + "\t" + item.getValue());
        }
        return true;
    }

    public ArrayList<Item> toPhenotype(boolean[] genotype){
        ArrayList<Item>  phenotype = new ArrayList<>();
        for (int i = 0 ; i < genotype.length ; i++){
            if(genotype[i]){
                int weight = items.get(i).getWeight();
                int value = items.get(i).getValue();
                phenotype.add(new Item(weight,value));
            }
        }
        return phenotype;
    }

    public boolean[]  toGenotype(){

        return new boolean[]{true};
    }
}
