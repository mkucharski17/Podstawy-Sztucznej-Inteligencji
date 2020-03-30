import com.google.common.primitives.Ints;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.IntStream;


public class GeneticAlgorithm {
    private int iterationNumber = 200;
    private Population population;
    private FitObject fitObject;

    GeneticAlgorithm(int capacity, Decoder decoder, Population generatedPopulation) {
        this.population = generatedPopulation;
        fitObject = new FitObject(decoder, capacity);
    }

    public void  makeNewGeneration() {
        population.reproduct();
        population.mutate();
    }


    private void makeSelectionRoulette() {
        int[] fitPoints = calculateFitPoints();
        int amountOfFitPoints = calculateAmountOfFitPoints(fitPoints);
        double[] chooseRanges = calculateChooseRanges(fitPoints, amountOfFitPoints);
        this.population = selectNewPopulationRoulette(chooseRanges);
    }


    //every genotype get it's own number of points
    private int[] calculateFitPoints() {
        int[] fitPoints = new int[population.getSpecimens().size()];

        for (int i = 0; i < population.getSpecimens().size(); i++) {
            fitPoints[i] = fitObject.getFitValue(population.getSpecimens().get(i));

            if (fitPoints[i] > fitObject.getFitValue(fitObject.getBestGenotype()))
                fitObject.setBestPhenotype(population.getSpecimens().get(i));
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

    private void makeSelectionBestN(){
        System.out.println(population.getSpecimens().size());
        ArrayList<boolean[]> newSpecimens = new ArrayList<>();
        int[] fitPoints = calculateFitPoints();
        int[] sortedFitPoints = Arrays.stream(fitPoints).sorted().toArray();

        for(int i = fitPoints.length - 1 ,j = 0  ; j < Environment.targetPopulationSize  ; j++, i--){
            int indexOfNextBestSpecimen =  Ints.indexOf(fitPoints,sortedFitPoints[i]);
            newSpecimens.add(population.getSpecimens().get(indexOfNextBestSpecimen));
        }
        population = new Population(newSpecimens);
        System.out.println(population.getSpecimens().size());
    }

    //randomize new population by roulette method
    private Population selectNewPopulationRoulette(double[] chooseRanges) {
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
        if(population.getSpecimens().size() > Environment.targetPopulationSize)
        //makeSelectionRoulette();
        makeSelectionBestN();
        if (iterationNumber == 0) {
            return true;
        } else {
            iterationNumber--;
            return false;
        }
    }

    public ArrayList<Item> getBestPhenotype() {
        return fitObject.getBestPhenotype();
    }
}
