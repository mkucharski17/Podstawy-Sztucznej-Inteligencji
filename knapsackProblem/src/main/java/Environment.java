import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Environment {
    private GeneticAlgorithm geneticAlgorithm;

    public Environment(int capacity, String fileName) {
        Decoder decoder = new Decoder(fileName);
        Population population = new Population(generatePopulation(decoder.getGensNumber()));
        geneticAlgorithm = new GeneticAlgorithm(capacity, decoder, population);
    }

    //TODO dowiedziec sie jakiej wielkosci powinna byc poczatkowa populacja, tutaj 15
    private ArrayList<boolean[]> generatePopulation(int gensNumber) {
        ArrayList<boolean[]> specimens = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
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
            geneticAlgorithm.makeNewGeneration();
        }
        System.out.println("poza petlą");
        return geneticAlgorithm.getBestPhenotype();
    }
}
