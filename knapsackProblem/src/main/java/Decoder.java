import java.util.ArrayList;
import java.util.Arrays;

public class Decoder {
    private ArrayList<Item> items;

    public Decoder(ArrayList<Item> items) {
        this.items = items;
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
