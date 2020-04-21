import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Environment for genetic algorithm.
 * It handles thing like generating start population and checking stop condition.
 */

public class Environment {
    /**
     * Target for population size. Bigger population size ensure better result, but it extends execution time.
     */
    static int targetPopulationSize;
    /**
     * GeneticAlgorithm object handling all genetic operations.
     */
    private GeneticAlgorithm geneticAlgorithm;
    /**
     * Decoder handling Item list and genotype - phenotype operations.
     */
    private Decoder decoder;

    /**
     * Environment constructor.
     *
     * @param capacity             Capacity of backpack.
     * @param targetPopulationSize Target for population size. Bigger population size ensure better result, but it extends execution time.
     * @param selectionType        Type selection which should be used.
     * @param fileName             File name from where we should read input values.
     */
    public Environment(int capacity, int targetPopulationSize, char selectionType, String fileName) {
        Environment.targetPopulationSize = targetPopulationSize;
        decoder = new Decoder(fileName);
        FitObject fitObject = new FitObject(decoder, capacity);
        PopulationSelector populationSelector = new PopulationSelector(fitObject);

        Population population = new Population(selectionType, generatePopulation(decoder.getGensNumber(), fitObject),
                populationSelector);
        geneticAlgorithm = new GeneticAlgorithm(population);
    }


    /**
     * Generating start population.
     * Every start specimen fulfills condition weight is less or equal capacity.
     *
     * @param gensNumber Number of gens for each specimen.
     * @param fitObject  FitObject to check genotype weight.
     * @return Start population as ArrayList of genotypes.
     */
    private ArrayList<boolean[]> generatePopulation(int gensNumber, FitObject fitObject) {
        ArrayList<boolean[]> specimens = new ArrayList<>();
        Random r = new Random();
        do {
            boolean[] newSpecimen = new boolean[gensNumber];
            for (int j = 0; j < gensNumber; j++)
                newSpecimen[j] = r.nextBoolean();
            if (fitObject.getFitValue(newSpecimen) > 0)
                specimens.add(newSpecimen);
        } while (specimens.size() < targetPopulationSize);
        return specimens;
    }

    /**
     * Short method which contains genetic algorithm calls, which in the end gives as result of algorithm.
     *
     * @return Phenotype of best found specimen.
     */
    public ArrayList<Item> findBestFit() {
        int i = 0;
        while (!geneticAlgorithm.isSatisfiedLastNSame()) {
            geneticAlgorithm.makeNewGeneration();
        }
        System.out.println("liczba iteracji = " + i);
        return decoder.toPhenotype(geneticAlgorithm.getBestFit());
    }
}
