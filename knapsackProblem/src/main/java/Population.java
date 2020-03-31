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
        for(int i = 0 ; i < Environment.targetPopulationSize/2 ; i++)
            cross(getTwoRandomSpecimens());
    }

    private void cross(int [] parentsIndexes) {
        specimens.add(uniformCrossover(specimens.get(parentsIndexes[0]), specimens.get(parentsIndexes[1])));
        // lub singlePointCrossover(specimens.get(parentsIndexes[0]), specimens.get(parentsIndexes[1])))
    }

    private int [] getTwoRandomSpecimens(){
        int motherIndex = new Random().nextInt(specimens.size());
        int fatherIndex = new Random().nextInt(specimens.size());

        while (motherIndex != fatherIndex)
            fatherIndex = new Random().nextInt(specimens.size());

        return new int[]{motherIndex,fatherIndex};

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

    public void conductMutation(){
        for (boolean[] specimen : specimens) {
            if (isTimeToMutate()) {
                int mutatedGene = new Random().nextInt(specimen.length);
                specimen = mutate(specimen,mutatedGene);
            }
        }
    }

    private boolean isTimeToMutate() {
        return new Random().nextInt(10) == 0;
    }



    public boolean[] mutate(boolean [] specimen, int mutatedGeneIndex ) {
        specimen[mutatedGeneIndex] = !specimen[mutatedGeneIndex];

        return specimen;
    }
}
