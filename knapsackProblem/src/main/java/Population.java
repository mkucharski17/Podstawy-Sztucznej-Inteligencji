import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
public class Population {

    @Setter
    private boolean[] bestSpecimen;
    private ArrayList<boolean[]> specimens;
    private PopulationSelector populationSelector;

    public Population(ArrayList<boolean[]> specimens) {
        this.specimens = specimens;
        this.bestSpecimen = new boolean[Environment.targetPopulationSize];
    }

    public Population(ArrayList<boolean[]> specimens, PopulationSelector populationSelector) {
        this.specimens = specimens;
        this.populationSelector = populationSelector;
        this.bestSpecimen = new boolean[Environment.targetPopulationSize];
    }

    public void makeSelection(){
        specimens = populationSelector.selectBestN(this);
        //specimens = populationSelector.makeSelectionRoulette(this);
    }

    public void makeReproduction() {
        for (int i = 0; i < Environment.targetPopulationSize / 2; i++) {
            int[] parentsIndexes = getTwoRandomSpecimens();
            //na potrzeby SinglePointCrossover int divisionPoint = new Random().nextInt((bestSpecimen.length));
            specimens.add(PopulationChanger.uniformCrossover(specimens.get(parentsIndexes[0]),
                    specimens.get(parentsIndexes[1])));
        }
    }

    public void makeMutation() {
        for (int i = 0; i < specimens.size() - 1; i++) {
            if (isTimeToMutate()) {
                int mutatedGene = new Random().nextInt(specimens.get(i).length);
                specimens.set(i, PopulationChanger.mutate(specimens.get(i), mutatedGene));
            }
        }
    }
    private boolean isTimeToMutate() {
        return new Random().nextInt(10) == 0;
    }


    private int[] getTwoRandomSpecimens() {
        int motherIndex = new Random().nextInt(specimens.size());
        int fatherIndex = new Random().nextInt(specimens.size());

        while (motherIndex != fatherIndex)
            fatherIndex = new Random().nextInt(specimens.size());

        return new int[]{motherIndex, fatherIndex};
    }
}
