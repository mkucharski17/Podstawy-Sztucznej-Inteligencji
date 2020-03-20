import java.util.ArrayList;


public class Environment {
    private ArrayList<boolean[] > population;
    private GeneticAlgorithm geneticAlgorithm;

    public Environment( int capacity, String fileName) {
        population = new ArrayList<>();
        generatePopulation();
        geneticAlgorithm = new GeneticAlgorithm(capacity, new Decoder(fileName));
    }

    private void generatePopulation(){

    }

    public ArrayList<Item> findBestMatch(){
        while(!geneticAlgorithm.isSatisfied()){
            population = geneticAlgorithm.makeNewGeneration(population);
        }
        return new ArrayList<Item>();
    }



}
