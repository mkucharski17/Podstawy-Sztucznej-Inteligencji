import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GeneticAlgorithm {

    private int iterationNumber = 100;
    private Population population;
    private FitObject FitObject;

    GeneticAlgorithm(int capacity, Decoder decoder) {
        FitObject = new FitObject(decoder, capacity);
    }

    public Population makeNewGeneration(Population population) {
        this.population = population;

        //TODO tutaj algorytm wybierania nowego pokolenia
        // ( wybieranie rodziców do krzyżowania -> krzyżowanie -> mutacja -> zwracamy nowe pokolenie)
        // mutacje proponuję zrobić  z prawdopodobieństwem 0.1


        return this.population;
    }

    private void makeSelection() {
        int[] fitPoints = calculateFitPoints();
        int amountOfFitPoints = calculateAmountOfFitPoints(fitPoints);
        double[] chooseRanges = calculateChooseRanges(fitPoints, amountOfFitPoints);
        this.population = selectNewPopulation(chooseRanges);
    }

    //every genotype get it's own number of points
    private int[] calculateFitPoints() {
        int[] fitPoints = new int[population.getSpecimens().size()];

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            fitPoints[i] = FitObject.getFitValue(population.getSpecimens().get(i));
            if (fitPoints[i] > FitObject.getFitValue(population.getBestGenotype())) {
                FitObject.setBestPhenotype(population.getSpecimens().get(i));
            }
        }
        return fitPoints;
    }

    //summary value of fitPoints
    private int calculateAmountOfFitPoints(int[] fitPoints) {
        int sum = 0;

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            sum += fitPoints[i];
        }
        return sum;
    }

    //calculating ranges to roulette method
    private double[] calculateChooseRanges(int[] fitPoints, int amountOfFitPoints) {
        double[] chooseRange = new double[population.getSpecimens().size()];

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            if (i == 0) {
                chooseRange[i] = (double) fitPoints[i] / amountOfFitPoints;
            } else {
                chooseRange[i] = chooseRange[i - 1] + (double) fitPoints[i] / amountOfFitPoints;
            }
        }
        return chooseRange;
    }

    //randomize new population by roulette method
    private Population selectNewPopulation(double[] chooseRanges) {
        Random random = new Random();
        double spot;
        ArrayList<boolean[]> newSpecimens = new ArrayList<>();

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            spot = random.nextDouble() * 100;
            for (int j = 0; j < population.getSpecimens().size(); j++) {
                //looking for range in which is our random spot
                if (j == population.getSpecimens().size() - 1) {
                    //our spot is in last range
                    newSpecimens.add(population.getSpecimens().get(i));
                } else if (j == 0) {
                    //checking first range
                    if (spot <= chooseRanges[j]) {
                        newSpecimens.add(population.getSpecimens().get(i));
                    }
                } else if (spot <= chooseRanges[j] && spot > chooseRanges[j - 1]) { // checking if spot is between two next values
                    newSpecimens.add(population.getSpecimens().get(i));
                }
            }
        }
        return new Population(newSpecimens);
    }

    public boolean isSatisfied() {
        if (iterationNumber == 0) {
            return true;
        } else {
            iterationNumber--;
            return false;
        }
    }

    public ArrayList<Item> getBestPhenotype() {
        return FitObject.getBestPhenotype();
    }

}
