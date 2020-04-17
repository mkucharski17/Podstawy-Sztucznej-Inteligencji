import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Decoder reads items from file and safe in ArrayList.
 * It contains methods to operate at items.
 */

public class Decoder {
    /**
     * List of items.
     */
    private ArrayList<Item> items;

    /**
     * Decoder constructor.
     * @param fileName File name from which decoder should read values.
     */
    public Decoder(String fileName) {
        readValuesFromFile(fileName);
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
     * Transforming genotype represented by boolean table to phenotype represented by ArrayList of Items.
     * @param genotype Genotype to transform.
     * @return         Phenotype of given genotype.
     */
    public ArrayList<Item> toPhenotype(boolean[] genotype) {
        ArrayList<Item> phenotype = new ArrayList<>();
        for (int i = 0; i < genotype.length; i++) {
            if (genotype[i]) {
                int weight = items.get(i).getWeight();
                int value = items.get(i).getValue();
                phenotype.add(new Item(weight, value));
            }
        }
        return phenotype;
    }

    /**
     * Transforming phenotype represented by ArrayList of Items to genotype represented by boolean table.
     * @param phenotype Phenotype to transform.
     * @return          Genotype of given phenotype.
     */
    public boolean[] toGenotype(ArrayList<Item> phenotype) {
        boolean[] genotype = new boolean[items.size()];
        for (int i = 0; i < items.size(); i++)
            genotype[i] = phenotype.contains(items.get(i));
        return genotype;
    }


    /**
     * @return Size of item list.
     */
    public int getGensNumber() {
        return items.size();
    }
}
