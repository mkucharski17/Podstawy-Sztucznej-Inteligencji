import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Population of specimens.
 */

@Getter
public class Population {

    /**
     * Static representing Roulette selection mode.
     */
    public static char ROULETTE_SELECTION = 'R';
    /**
     * Static representing BestN selection mode.
     */
    public static char BESTN_SELECTION = 'B';

    /**
     * Best found specimen.
     */
    @Setter
    private boolean[] bestSpecimen;
    /**
     * List of specimens in population.
     */
    private ArrayList<boolean[]> specimens;
    /**
     * PopulationSelector handling selection.
     */
    private PopulationSelector populationSelector;
    /**
     * Char containing information of chosen selection type.
     */
    private char selectionType;

    /**
     * Population constructor for tests.
     *
     * @param specimens ArrayList of specimens.
     */
    public Population(ArrayList<boolean[]> specimens) {
        this.specimens = specimens;
        this.bestSpecimen = new boolean[Environment.targetPopulationSize];
    }

    /**
     * Population constructor.
     *
     * @param selectionType      Type of using selection.
     * @param specimens          ArrayList of specimens.
     * @param populationSelector Object of population selector used to make selection.
     */
    public Population(char selectionType, ArrayList<boolean[]> specimens, PopulationSelector populationSelector) {
        this.selectionType = selectionType;
        this.specimens = specimens;
        this.populationSelector = populationSelector;
        this.bestSpecimen = new boolean[Environment.targetPopulationSize];
    }

    /**
     * Performing the selection depending on the given mode(selectionType).
     */
    public void makeSelection() {
        if (selectionType == BESTN_SELECTION)
            specimens = populationSelector.selectBestN(this);
        else if (selectionType == ROULETTE_SELECTION)
            specimens = populationSelector.makeSelectionRoulette(this);
    }

    /**
     * Performing reproduction on current population.
     */
    public void makeReproduction() {
        for (int i = 0; i < Environment.targetPopulationSize / 2; i++) {
            int[] parentsIndexes = getTwoRandomSpecimens();
            //na potrzeby SinglePointCrossover int divisionPoint = new Random().nextInt((bestSpecimen.length));
            specimens.add(PopulationChanger.uniformCrossover(specimens.get(parentsIndexes[0]),
                    specimens.get(parentsIndexes[1])));
        }
    }

    /**
     * Performing mutation on current population.
     */
    public void makeMutation() {
        for (int i = 0; i < specimens.size() - 1; i++) {
            if (isTimeToMutate()) {
                int mutatedGene = new Random().nextInt(specimens.get(i).length);
                if (!Arrays.equals(specimens.get(i), bestSpecimen))
                    specimens.set(i, PopulationChanger.mutate(specimens.get(i), mutatedGene));
            }
        }
    }

    /**
     * Method to generate random boolean with 10% for true;
     *
     * @return True in 10% random cases.
     */
    private boolean isTimeToMutate() {
        return new Random().nextInt(10) == 0;
    }


    /**
     * Generating two random specimens indexes, used for population reproduction.
     * First index represent mother index, second father index.
     *
     * @return Table of two indexes for random specimens.
     */
    private int[] getTwoRandomSpecimens() {
        int motherIndex = new Random().nextInt(specimens.size());
        int fatherIndex = new Random().nextInt(specimens.size());

        while (motherIndex == fatherIndex)
            fatherIndex = new Random().nextInt(specimens.size());

        return new int[]{motherIndex, fatherIndex};
    }
}
