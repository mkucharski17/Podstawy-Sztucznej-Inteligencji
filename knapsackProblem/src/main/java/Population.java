import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
public class Population {
    private ArrayList<boolean[]> specimens;


    public Population(ArrayList<boolean[]> specimens) {

        this.specimens = specimens;
    }

    public void reproduct(){
        for(int i = 0 ; i < specimens.size()/2 ; i++)
            cross();
    }

    public void cross() {
        int motherIndex = new Random().nextInt(specimens.size());
        int fatherIndex = new Random().nextInt(specimens.size());

        while (motherIndex != fatherIndex)
            fatherIndex = new Random().nextInt(specimens.size());

        specimens.add(uniformCrossover(specimens.get(motherIndex), specimens.get(fatherIndex)));
        // lub singlePointCrossover((specimens.get(motherIndex), specimens.get(fatherIndex)))
    }

    private static boolean[] singlePointCrossover(boolean[] mother, boolean[] father) {
        boolean[] child = new boolean[mother.length];
        int divisionPoint = new Random().nextInt((mother.length));

        System.arraycopy(mother, 0, child, 0, divisionPoint);
        System.arraycopy(father, divisionPoint + 1, child, divisionPoint + 1, father.length - divisionPoint - 1);

        return child;
    }

    private boolean[] uniformCrossover(boolean[] mother, boolean[] father) {
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

    public void mutate() {
        for (boolean[] specimen : specimens) {
            if (isTimeToMutate()) {
                int mutatedGene = new Random().nextInt(specimen.length);
                specimen[mutatedGene] = specimen[mutatedGene];
            }
        }
    }

    private boolean isTimeToMutate() {
        return new Random().nextInt(10) == 0;
    }

}
