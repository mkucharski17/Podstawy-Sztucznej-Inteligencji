import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Getter
public class Population {
    @Setter
    private boolean[] bestGenotype;
    private ArrayList<boolean[]> specimens;


    public Population(ArrayList<boolean[]> specimens) {
        this.specimens = specimens;
    }

    private static boolean[] singlePointCrossover(boolean[] mother, boolean[] father) {
        boolean[] child = new boolean[mother.length];
        int divisionPoint = new Random().nextInt((mother.length));

        System.arraycopy(mother, 0, child, 0, divisionPoint);
        System.arraycopy(father, divisionPoint + 1, child, divisionPoint + 1, father.length - divisionPoint - 1);

        return child;
    }

    private static boolean[] uniformCrossover(boolean[] mother, boolean[] father) {
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

    private static boolean[] mutate(boolean[] genotype) {
        int mutatedGene = new Random().nextInt((genotype.length));
        genotype[mutatedGene] = !genotype[mutatedGene];
        return genotype;
    }

}
