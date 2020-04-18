import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class responsible for making selection of population.
 */

public class PopulationSelector {
    /**
     * FitObject handling calculating values of genotypes.
     */
    private FitObject fitObject;

    /**
     * PopulationSelector constructor.
     *
     * @param fitObject Object used to calculate values, weights of genotypes/items.
     */
    public PopulationSelector(FitObject fitObject) {
        this.fitObject = fitObject;
    }

    /**
     * Selection BestN.
     * This selection algorithm select best(with highest value) n specimens.
     *
     * @param population Start population which need to be selected.
     * @return List of specimens after selection.
     */
    public ArrayList<boolean[]> selectBestN(Population population) {
        ArrayList<boolean[]> newSpecimens = new ArrayList<>();
        int[] fitPoints = fitObject.calculateFitPoints(population);
        int[] sortedFitPoints = Arrays.stream(fitPoints).sorted().toArray();
        newSpecimens.add(population.getBestSpecimen());
        for (int i = fitPoints.length - 1, j = 1; j < Environment.targetPopulationSize; j++, i--) {
            int indexOfNextBestSpecimen = Ints.indexOf(fitPoints, sortedFitPoints[i]);
            newSpecimens.add(population.getSpecimens().get(indexOfNextBestSpecimen));
        }
        return newSpecimens;
    }

    /**
     * Getting ready params for roulette selection.
     * This selection algorithm randomly save specimens. Better(with higher value) specimens have higher chance for being chosen.
     *
     * @param population Start population which need to be selected.
     * @return List of specimens after selection.
     */
    public ArrayList<boolean[]> makeSelectionRoulette(Population population) {
        int[] fitPoints = fitObject.calculateFitPoints(population);
        int amountOfFitPoints = calculateAmountOfFitPoints(fitPoints);
        double[] chooseRanges = calculateChooseRanges(fitPoints, amountOfFitPoints);
        return selectNewPopulationRoulette(chooseRanges, population.getSpecimens(), population);

    }

    /**
     * Summary of fit points.
     *
     * @param fitPoints Table of values representing fit points.
     * @return Summary value of fit points.
     */
    private int calculateAmountOfFitPoints(int[] fitPoints) {
        int sum = 0;

        for (int fitPoint : fitPoints) sum += fitPoint;

        return sum;
    }

    /**
     * Calculating ranges for each next item(represented by fitPoints).
     *
     * @param fitPoints         Table of values representing fit points.
     * @param amountOfFitPoints Summary value of fit points.
     * @return Table of ranges(which size represent probability of being chosen by selection)
     */
    private double[] calculateChooseRanges(int[] fitPoints, int amountOfFitPoints) {
        double[] chooseRange = new double[fitPoints.length];

        for (int i = 0; i < fitPoints.length; i++) {
            if (i == 0) {
                chooseRange[i] = (double) fitPoints[i] / amountOfFitPoints;
            } else {
                chooseRange[i] = chooseRange[i - 1] + (double) fitPoints[i] / amountOfFitPoints;
            }
        }
        return chooseRange;
    }

    /**
     * Making new population be Roulette selection.
     *
     * @param chooseRanges Ranges representing probability.
     * @param specimens    List of specimens.
     * @param population   Actual population.
     * @return List of specimens after selection.
     */
    private ArrayList<boolean[]> selectNewPopulationRoulette(double[] chooseRanges, ArrayList<boolean[]> specimens, Population population) {
        double spot;
        ArrayList<boolean[]> newSpecimens = new ArrayList<>();
        newSpecimens.add(population.getBestSpecimen());
        for (int i = 1; i < specimens.size(); i++) {
            spot = new Random().nextDouble() * 100;
            for (int j = 0; j < specimens.size(); j++) {
                //looking for range in which is our random spot
                if (j == specimens.size() - 1) {
                    //our spot is in last range
                    newSpecimens.add(specimens.get(i));
                } else if (j == 0) {
                    //checking first range
                    if (spot <= chooseRanges[j]) {
                        newSpecimens.add(specimens.get(i));
                    }
                } else if (spot <= chooseRanges[j] && spot > chooseRanges[j - 1]) { // checking if spot is between two next values
                    newSpecimens.add(specimens.get(i));
                }
            }
        }
        return newSpecimens;
    }

}
