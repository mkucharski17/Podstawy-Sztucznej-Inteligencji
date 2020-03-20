import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GeneticAlgorithm {
    private ArrayList<boolean[]> population;
    private MatchObject matchObject;

    GeneticAlgorithm(int capacity, Decoder decoder) {
        matchObject = new MatchObject(decoder, capacity);
    }

    public ArrayList<boolean[]> makeNewGeneration(ArrayList<boolean[]> population) {
        this.population = population;
        makeSelection();
        return this.population;
    }

    private void makeSelection() {
        //TODO
    }

    private static boolean[] cross(boolean[] mother, boolean[] father) {
        boolean[] child = new boolean[mother.length];
        int divisionPoint = new Random().nextInt((mother.length));
        System.out.println(divisionPoint);

        System.arraycopy(mother, 0, child, 0, divisionPoint);
        System.arraycopy(father, divisionPoint + 1, child, divisionPoint + 1, father.length - divisionPoint - 1);

        return child;
    }

    private static boolean[] mutate(boolean[] genotype) {
        int mutatedGene = new Random().nextInt((genotype.length));
        genotype[mutatedGene] = !genotype[mutatedGene];
        return genotype;
    }

    public boolean isSatisfied() {
        //TODO
        return true;
    }
}
