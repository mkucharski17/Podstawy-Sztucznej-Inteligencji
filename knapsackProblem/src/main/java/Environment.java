import java.util.ArrayList;
import java.util.Random;


public class Environment {
    private ArrayList<boolean[] > population;
    private GeneticAlgorithm geneticAlgorithm;

    public Environment( int capacity, String fileName) {
        population = new ArrayList<boolean[] >();
        Decoder decoder = new Decoder(fileName);
        generatePopulation(decoder.getGensNumber());
        geneticAlgorithm = new GeneticAlgorithm(capacity, decoder);
    }

    //TODO dowiedziec sie jakiej wielkosci powinna byc poczatkowa populacja, tutaj 15
    private void generatePopulation(int gensNumber){
        Random r = new Random();
        for(int i=0;i<15;i++){
            boolean[] temp = new boolean[gensNumber];
            for(int j=0;j<gensNumber;j++)
                temp[j] = r.nextBoolean();
            population.add(temp);
        }
    }

    public ArrayList<boolean[]> findBestPopulation(){
        while(!geneticAlgorithm.isSatisfied()){
            population = geneticAlgorithm.makeNewGeneration(population);
        }
        return population;
    }

    public boolean[] findBestMatch(){
        return geneticAlgorithm.getBestFitGenotype();
    }



}
