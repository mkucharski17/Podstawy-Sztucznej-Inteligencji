import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


public class FitObject {
    @Getter
    private ArrayList<Item> bestPhenotype;
    private Decoder decoder;
    private int capacity;


    public FitObject(Decoder decoder, int capacity) {
        this.decoder = decoder;
        this.capacity = capacity;
        bestPhenotype = new ArrayList<>();
    }

    public void setBestPhenotype(boolean[] genotype) {
        bestPhenotype = decoder.toPhenotype(genotype);
    }

    public int getFitValue(boolean[] genotype) {
        ArrayList<Item> phenotype = decoder.toPhenotype(genotype);
        int value = getTotalValue(phenotype);
        if (!isWeightProperly(phenotype))
            return 0;
        else
            return value;
    }

    private int getTotalValue(ArrayList<Item> phenotype) {
        return phenotype.stream().mapToInt(Item::getValue).sum();
    }

    private boolean isWeightProperly(ArrayList<Item> phenotype) {
        return phenotype.stream().mapToInt(Item::getValue).sum() <= capacity;
    }
}
