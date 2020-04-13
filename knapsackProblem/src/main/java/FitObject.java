import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


public class FitObject {
    private Decoder decoder;
    private int capacity;


    public FitObject(Decoder decoder, int capacity) {
        this.decoder = decoder;
        this.capacity = capacity;
    }

    public int[] calculateFitPoints(Population population) {
        int[] fitPoints = new int[population.getSpecimens().size()];

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            fitPoints[i] = getFitValue(population.getSpecimens().get(i));
            if (fitPoints[i] > getFitValue(population.getBestSpecimen())){
                System.out.println("Last best:\t"+getFitValue(population.getBestSpecimen())+"\n"+"New best:\t"+fitPoints[i]);
                population.setBestSpecimen(population.getSpecimens().get(i));
            }
        }
        return fitPoints;
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
        return phenotype.stream().mapToInt(Item::getWeight).sum() <= capacity;
    }
}
