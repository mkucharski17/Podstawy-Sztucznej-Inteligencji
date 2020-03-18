import java.util.ArrayList;


public class Environment {
    private ArrayList<boolean[] > population;
    private GeneticAlgorithm geneticAlgorithm;

    public Environment( ArrayList<Item> items, int capacity) {
        population = new ArrayList<boolean[] >();
        generatePopulation();
        geneticAlgorithm = new GeneticAlgorithm(capacity, new Decoder(items));
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
