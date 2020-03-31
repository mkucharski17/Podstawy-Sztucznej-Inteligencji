import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PopulationSelector {
    private FitObject fitObject;

    public PopulationSelector(FitObject fitObject) {
        this.fitObject = fitObject;
    }

    public ArrayList<boolean[]> selectBestN(Population population) {
        ArrayList<boolean[]> newSpecimens = new ArrayList<>();
        int[] fitPoints = fitObject.calculateFitPoints(population);
        int[] sortedFitPoints = Arrays.stream(fitPoints).sorted().toArray();

        for (int i = fitPoints.length - 1, j = 0; j < Environment.targetPopulationSize; j++, i--) {
            int indexOfNextBestSpecimen = Ints.indexOf(fitPoints, sortedFitPoints[i]);
            newSpecimens.add(population.getSpecimens().get(indexOfNextBestSpecimen));
        }
        return newSpecimens;
    }

    private ArrayList<boolean[]> makeSelectionRoulette(Population population) {
        int[] fitPoints = fitObject.calculateFitPoints(population);
        int amountOfFitPoints = calculateAmountOfFitPoints(fitPoints);
        double[] chooseRanges = calculateChooseRanges(fitPoints, amountOfFitPoints);
        
        return selectNewPopulationRoulette(chooseRanges, population.getSpecimens());
    }

    //summary value of fitPoints
    private int calculateAmountOfFitPoints(int[] fitPoints) {
        int sum = 0;

        for (int fitPoint : fitPoints) sum += fitPoint;

        return sum;
    }

    //calculating ranges to roulette method
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

    //randomize new population by roulette method
    private ArrayList<boolean[]> selectNewPopulationRoulette(double[] chooseRanges, ArrayList<boolean[]> specimens) {
        double spot;
        ArrayList<boolean[]> newSpecimens = new ArrayList<>();

        for (int i = 0; i < specimens.size(); i++) {
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
