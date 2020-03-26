import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


public class MatchObject {
    @Getter
    private ArrayList<Item> bestPhenotype;
    private Decoder decoder;
    private int capacity;


    public MatchObject(Decoder decoder, int capacity) {
        this.decoder = decoder;
        this.capacity = capacity;
    }

    public void setBestPhenotype(boolean[] genotype) {
        bestPhenotype = decoder.toPhenotype(genotype);
    }

    public int getMatchValue(boolean[] genotype) {
        ArrayList<Item> phenotype = decoder.toPhenotype(genotype);
        int value = getTotalValue(phenotype);
        if (!isWeightProperly(phenotype))
            return capacity;
        else
            return value;
    }

    private int getTotalValue(ArrayList<Item> phenotype) {
        int totalValue = 0;
        for (Item item : phenotype) {
            totalValue += item.getValue();
        }
        return totalValue;
    }

    private boolean isWeightProperly(ArrayList<Item> phenotype) {
        int totalWeight = 0;
        for (Item item : phenotype) {
            totalWeight += item.getWeight();
            if (totalWeight > capacity)
                return false;
        }
        return true;
    }
}
