import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Class which contains methods allowing calculate fit points and values for population or item.
 */

public class FitObject {
    /**
     * Decoder handling Item list and genotype - phenotype operations.
     */
    private Decoder decoder;
    /**
     * Capacity of backpack.
     */
    private int capacity;


    /**
     * FitObject constructor
     *
     * @param decoder  Decoder object which contains all items in our instance of problem.
     * @param capacity Capacity of backpack.
     */
    public FitObject(Decoder decoder, int capacity) {
        this.decoder = decoder;
        this.capacity = capacity;
    }

    /**
     * Calculating fit points of every genotype in given population.
     *
     * @param population Population which fit points need to be calculate.
     * @return Table of fit points in the same order as in given population.
     */
    public int[] calculateFitPoints(Population population) {
        int[] fitPoints = new int[population.getSpecimens().size()];

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            fitPoints[i] = getFitValue(population.getSpecimens().get(i));
            if (fitPoints[i] > getFitValue(population.getBestSpecimen())) {
                population.setBestSpecimen(population.getSpecimens().get(i));
            }
        }
        return fitPoints;
    }

    /**
     * Calculating fit value for given genotype.
     * Fit value is equal 0 when:
     * 1. Genotype don't have any items.
     * 2. Genotype capacity is greater than capacity.
     *
     * @param genotype Genotype to calculate fit points value.
     * @return Fit value.
     */
    public int getFitValue(boolean[] genotype) {
        ArrayList<Item> phenotype = decoder.toPhenotype(genotype);
        int value = getTotalValue(phenotype);
        if (!isWeightProperly(phenotype))
            return 0;
        else
            return value;
    }

    /**
     * Getting summary value of items in given phenotype.
     *
     * @param phenotype Phenotype to sum value.
     * @return Summary value of items.
     */
    private int getTotalValue(ArrayList<Item> phenotype) {
        return phenotype.stream().mapToInt(Item::getValue).sum();
    }

    /**
     * Checking if given phenotype weight is less or equal capacity.
     *
     * @param phenotype Phenotype to check.
     * @return True if weight is less or equal capacity.
     */
    private boolean isWeightProperly(ArrayList<Item> phenotype) {
        return phenotype.stream().mapToInt(Item::getWeight).sum() <= capacity;
    }
}
