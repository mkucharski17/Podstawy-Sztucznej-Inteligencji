import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GeneticAlgorithm {

    @Getter
    private static final int iterationNumber = 100;
    private Population population;
    private MatchObject matchObject;
    private int iteration;

    GeneticAlgorithm(int capacity, Decoder decoder) {
        matchObject = new MatchObject(decoder, capacity);
        iteration = 0;
    }

    public Population makeNewGeneration(Population population) {
        this.population = population;
        makeSelection();

        return population;
    }

    private void makeSelection() {
        int[] fitPoints = calculateFitPoints();
        int amountOfFitPoints = calculateAmountOfFitPoints(fitPoints);
        double[] choseRanges = calculateChoseRanges(fitPoints, amountOfFitPoints);
        this.population = selectNewPopulation(choseRanges);
    }

    //every genotype get it's own number of points
    private int[] calculateFitPoints() {
        int[] fitPoints = new int[population.getSpecimens().size()];

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            fitPoints[i] = matchObject.getMatchValue(population.getSpecimens().get(i));
            if (fitPoints[i] > matchObject.getMatchValue(population.getBestGenotype())) {
                matchObject.setBestPhenotype(population.getSpecimens().get(i));
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
    private double[] calculateChoseRanges(int[] fitPoints, int amountOfFitPoints) {
        double[] choseRange = new double[population.getSpecimens().size()];

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            if (i == 0) {
                choseRange[i] = (double) fitPoints[i] / amountOfFitPoints;
            } else {
                choseRange[i] = choseRange[i - 1] + (double) fitPoints[i] / amountOfFitPoints;
            }
        }
        return choseRange;
    }

    //randomize new population by roulette method
    private Population selectNewPopulation(double[] choseRanges) {
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
                    if (spot <= choseRanges[j]) {
                        newSpecimens.add(population.getSpecimens().get(i));
                    }
                } else if (spot <= choseRanges[j] && spot > choseRanges[j - 1]) { // checking if spot is between two next values
                    newSpecimens.add(population.getSpecimens().get(i));
                }
            }
        }
        return new Population(newSpecimens);
    }

    public boolean isSatisfied() {
        if (iteration == iterationNumber) {
            return true;
        } else {
            iteration++;
            return false;
        }
    }

    public ArrayList<Item> getBestPhenotype(){
        return matchObject.getBestPhenotype();
    }

}
