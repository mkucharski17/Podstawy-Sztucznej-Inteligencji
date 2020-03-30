import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Environment {
    //TODO dowiedziec sie jakiej wielkosci powinna byc poczatkowa populacja, tutaj 10
    static int targetPopulationSize = 10;
    private GeneticAlgorithm geneticAlgorithm;

    public Environment(int capacity, String fileName) {
        Decoder decoder = new Decoder(fileName);
        Population population = new Population(generatePopulation(decoder.getGensNumber()));
        geneticAlgorithm = new GeneticAlgorithm(capacity, decoder, population);
    }


    private ArrayList<boolean[]> generatePopulation(int gensNumber) {
        ArrayList<boolean[]> specimens = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < targetPopulationSize; i++) {
            boolean[] newSpecimen = new boolean[gensNumber];
            for (int j = 0; j < gensNumber; j++)
                newSpecimen[j] = r.nextBoolean();
            specimens.add(newSpecimen);
        }
        return specimens;
    }

    public ArrayList<Item> findBestFit() {
        int i = 0 ;
        while (!geneticAlgorithm.isSatisfied()) {
            System.out.println("iteracja nr " + i++);
            geneticAlgorithm.makeNewGeneration();
        }
        return geneticAlgorithm.getBestPhenotype();
    }
}
