import com.google.common.primitives.Ints;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Object making changes mutation and crossover.
 */
public class PopulationChanger {

    /**
     * Crossover made with single dividing point.
     * Gens before dividing point are taken from mother genotype, gens after dividing point are taken form father genotype.
     * @param mother         Mother genotype.
     * @param father         Father genotype.
     * @param divisionPoint  Point dividing father and mother genotypes.
     * @return               Child genotype.
     */
    public static boolean[] singlePointCrossover(boolean[] mother, boolean[] father, int divisionPoint) {
        boolean[] child = new boolean[mother.length];

        System.arraycopy(mother, 0, child, 0, divisionPoint);
        System.arraycopy(father, divisionPoint + 1, child, divisionPoint + 1, father.length - divisionPoint - 1);

        return child;
    }

    /**
     * Uniform crossover.
     * Next gens are randomly taken from father or mother.
     * @param mother         Mother genotype.
     * @param father         Father genotype.
     * @return               Child genotype.
     */
    public static boolean[] uniformCrossover(boolean[] mother, boolean[] father) {
        boolean[] child = new boolean[mother.length];
        for (int i = 0; i < child.length; i++) {
            boolean geneOrigin = new Random().nextBoolean();
            if (geneOrigin)
                child[i] = father[i];
            else
                child[i] = mother[i];
        }
        return child;
    }


    /**
     * Mutating gen o given index.
     * @param specimen          Genotype to mutate.
     * @param mutatedGeneIndex  Index of gen to muted.
     * @return                  Mutated genotype.
     */
    public static boolean[] mutate(boolean[] specimen, int mutatedGeneIndex) {
        specimen[mutatedGeneIndex] = !specimen[mutatedGeneIndex];
        return specimen;
    }
}
