import com.google.common.primitives.Ints;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

/**
 * General structure of the genetic algorithm.
 */

public class GeneticAlgorithm {
    /**
     * Iteration number for simply stop condition.
     */
    private int iterationNumber = 50;

    /**
     * Population of specimens to work on.
     */
    private Population population;

    /**
     * Static representing maximum repeats the same best specimen in stop condition.
     */
    private static int SATISFIED_COUNTER_VALUE = 50;
    /**
     * Current value of repeats the same best specimen.
     */
    private int satisfiedCounter = 50;
    /**
     * Best genotype in last iteration.
     */
    private boolean[] lastBest;


    /**
     * GeneticAlgorithm constructor.
     *
     * @param generatedPopulation Start population for algorithm.
     */
    GeneticAlgorithm(Population generatedPopulation) {
        this.population = generatedPopulation;
    }

    /**
     * Making new generation on population by reproduction and mutation.
     */
    public void makeNewGeneration() {
        population.makeReproduction();
        population.makeMutation();
    }

    /**
     * Simply stop condition based on the number of iterations.
     *
     * @return True if stop condition is confirmed.
     */
    public boolean isSatisfied() {
        if (population.getSpecimens().size() > Environment.targetPopulationSize)
            population.makeSelection();
        if (iterationNumber == 0) {
            return true;
        } else {
            iterationNumber--;
            return false;
        }
    }

    /**
     * Stop condition is confirmed when population best specimen is the same for last 50 times;
     *
     * @return True if stop condition is confirmed.
     */
    public boolean isSatisfiedLastNSame() {
        if (population.getSpecimens().size() > Environment.targetPopulationSize)
            population.makeSelection();
        if (satisfiedCounter == 0) {
            return true;
        } else {
            if (Arrays.equals(lastBest, getBestFit())) {
                satisfiedCounter--;
            } else {
                satisfiedCounter = SATISFIED_COUNTER_VALUE;
                lastBest = getBestFit();
            }
            return false;
        }
    }

    /**
     * Return best already found speciment.
     *
     * @return Best already found genotype.
     */
    public boolean[] getBestFit() {
        return population.getBestSpecimen();
    }
}
