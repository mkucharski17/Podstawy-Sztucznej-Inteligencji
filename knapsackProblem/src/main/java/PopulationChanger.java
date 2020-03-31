import com.google.common.primitives.Ints;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PopulationChanger {


    public static boolean[] singlePointCrossover(boolean[] mother, boolean[] father) {
        boolean[] child = new boolean[mother.length];
        int divisionPoint = new Random().nextInt((mother.length));

        System.arraycopy(mother, 0, child, 0, divisionPoint);
        System.arraycopy(father, divisionPoint + 1, child, divisionPoint + 1, father.length - divisionPoint - 1);

        return child;
    }

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


    public static boolean[] mutate(boolean[] specimen, int mutatedGeneIndex) {
        specimen[mutatedGeneIndex] = !specimen[mutatedGeneIndex];
        return specimen;
    }
}
