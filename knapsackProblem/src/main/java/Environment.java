import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Environment {
    //TODO dowiedziec sie jakiej wielkosci powinna byc poczatkowa populacja, tutaj 10
    static int targetPopulationSize = 100 ;
    private GeneticAlgorithm geneticAlgorithm;
    private Decoder decoder;

    public Environment(int capacity, String fileName) {
        decoder = new Decoder(fileName);
        FitObject fitObject = new FitObject(decoder, capacity);
        PopulationSelector populationSelector= new PopulationSelector(fitObject);

        Population population = new Population(generatePopulation(decoder.getGensNumber(),fitObject),
                populationSelector);
        geneticAlgorithm = new GeneticAlgorithm(population);
    }


    private ArrayList<boolean[]> generatePopulation(int gensNumber, FitObject fitObject) {
        ArrayList<boolean[]> specimens = new ArrayList<>();
        Random r = new Random();
        do{
            boolean[] newSpecimen = new boolean[gensNumber];
            for (int j = 0; j < gensNumber; j++)
                newSpecimen[j] = r.nextBoolean();
            if(fitObject.getFitValue(newSpecimen) > 0)
                specimens.add(newSpecimen);
        }while(specimens.size()<targetPopulationSize);
        return specimens;
    }

    public ArrayList<Item> findBestFit() {
        int i = 0 ;
        while (!geneticAlgorithm.isSatisfiedLastNSame()) {
            System.out.println("iteracja nr " + i++);
            geneticAlgorithm.makeNewGeneration();
        }
        return decoder.toPhenotype(geneticAlgorithm.getBestFit());
    }
}
