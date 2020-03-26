import java.util.ArrayList;
import java.util.Random;


public class Environment {
    private Population population;
    private GeneticAlgorithm geneticAlgorithm;

    public Environment(int capacity, String fileName) {
        Decoder decoder = new Decoder(fileName);
        population = new Population(generatePopulation(decoder.getGensNumber()));
        geneticAlgorithm = new GeneticAlgorithm(capacity, decoder);
    }

    //TODO dowiedziec sie jakiej wielkosci powinna byc poczatkowa populacja, tutaj 15
    private ArrayList<boolean[]> generatePopulation(int gensNumber) {
        ArrayList<boolean[]> specimens = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            boolean[] newSpecimen = new boolean[gensNumber];
            for (int j = 0; j < gensNumber; j++)
                newSpecimen[j] = r.nextBoolean();
            specimens.add(newSpecimen);
        }
        return specimens;
    }

    public ArrayList<Item> findBestMatch() {
        while (!geneticAlgorithm.isSatisfied()) {
            population = geneticAlgorithm.makeNewGeneration(population);
        }
        return geneticAlgorithm.getBestPhenotype();
    }
}
