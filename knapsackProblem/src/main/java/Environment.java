import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Environment {
    //TODO dowiedziec sie jakiej wielkosci powinna byc poczatkowa populacja, tutaj 10
    static int targetPopulationSize = 20 ;
    private GeneticAlgorithm geneticAlgorithm;
    private Decoder decoder;

    public Environment(int capacity, String fileName) {
        decoder = new Decoder(fileName);
        FitObject fitObject = new FitObject(decoder, capacity);
        PopulationSelector populationSelector= new PopulationSelector(fitObject);

        Population population = new Population(generatePopulation(decoder.getGensNumber()),
                populationSelector);
        geneticAlgorithm = new GeneticAlgorithm(population);
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
        return decoder.toPhenotype(geneticAlgorithm.getBestFit());
    }
}
