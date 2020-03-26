import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GeneticAlgorithm {
    private static final int iterationNumber = 100;

    private ArrayList<boolean[]> population;
    private MatchObject matchObject;

    private int bestFitValue;
    private boolean[] bestFitGenotype;

    private int iteration;

    GeneticAlgorithm(int capacity, Decoder decoder) {
        matchObject = new MatchObject(decoder, capacity);
        iteration = 0;
        bestFitValue = 0;
    }

    public ArrayList<boolean[]> makeNewGeneration(ArrayList<boolean[]> population) {
        this.population = population;
        makeSelection();
        return this.population;
    }

    private void makeSelection() {
        int[] fitPoints = calculateFitPoints();
        int amountOfFitPoints = calculateAmountOfFitPoints(fitPoints);
        double[] choseRanges = calculateChoseRanges(fitPoints,amountOfFitPoints);
        this.population = selectNewPopulation(choseRanges);
    }

    //every genotype get it's own number of points
    private int[] calculateFitPoints(){
        int [] fitPoints = new int[population.size()];
        for(int i = 0; i<population.size(); i++){
            fitPoints[i] = matchObject.getMatchValue(population.get(i));
            if(fitPoints[i] > bestFitValue){
                bestFitValue = fitPoints[i];
                bestFitGenotype = population.get(i);
            }
        }
        return fitPoints;
    }

    //summary value of fitPoints
    private int calculateAmountOfFitPoints(int[] fitPoints){
        int sum = 0;
        for(int i = 0; i<population.size(); i++){
            sum += fitPoints[i];
        }
        return sum;
    }

    //calculating ranges to roulette method
    private double[] calculateChoseRanges(int[] fitPoints, int amountOfFitPoints){
        double [] choseRange = new double[population.size()];
        for(int i=0; i<population.size(); i++){
            if(i == 0){
                choseRange[i] = (double)fitPoints[i] / amountOfFitPoints;
            } else {
                choseRange[i] = choseRange[i-1] + (double)fitPoints[i] / amountOfFitPoints;
            }
        }
        return choseRange;
    }

    //randomize new population by roulette method
    private ArrayList<boolean[]> selectNewPopulation(double[] choseRanges){
        Random random = new Random();
        double spot;
        ArrayList<boolean[]> newPopulation = new ArrayList<boolean[]>();
        for(int i=0; i<population.size(); i++){
            //generate random double 0 - 100
            spot = random.nextDouble() * 100;
            for(int j = 0; j<population.size(); j++){
                //looking for range in which is our random spot
                if(j == population.size() - 1){
                    //our spot is in last range
                    newPopulation.add(population.get(j));
                } else if (j == 0){
                    //checking first range
                    if(spot <= choseRanges[j]){
                        newPopulation.add(population.get(j));
                    }
                } else if (spot <= choseRanges[j] && spot > choseRanges[j-1]) { // checking if spot is betwen two next values
                    newPopulation.add(population.get(j));
                }
            }
        }
        return newPopulation;
    }

    private static boolean[] singlePointCrossover(boolean[] mother, boolean[] father) {
        boolean[] child = new boolean[mother.length];
        int divisionPoint = new Random().nextInt((mother.length));

        System.arraycopy(mother, 0, child, 0, divisionPoint);
        System.arraycopy(father, divisionPoint + 1, child, divisionPoint + 1, father.length - divisionPoint - 1);

        return child;
    }

    public static boolean[] uniformCrossover(boolean[] mother, boolean[] father) {
        boolean[] child = new boolean[mother.length];
        for (int i = 0 ; i < child.length ; i++){
            boolean geneOrigin = new Random().nextBoolean();

            if(geneOrigin)
                child[i] = father[i];
            else
                child[i] = mother[i];
        }

        return child;
    }



    private static boolean[] mutate(boolean[] genotype) {
        int mutatedGene = new Random().nextInt((genotype.length));
        genotype[mutatedGene] = !genotype[mutatedGene];
        return genotype;
    }

    public boolean isSatisfied() {
        if(iteration == iterationNumber){
            return true;
        } else {
            iteration++;
         return false;
        }
    }

    public boolean[] getBestFitGenotype(){
        return bestFitGenotype;
    }
}
