import com.google.common.primitives.Ints;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.IntStream;


public class GeneticAlgorithm {
    private int iterationNumber = 200;
    private Population population;


    GeneticAlgorithm(Population generatedPopulation) {
        this.population = generatedPopulation;
    }

    public void  makeNewGeneration() {
        population.makeReproduction();
        population.makeMutation();
    }

    public boolean isSatisfied() {
        if(population.getSpecimens().size() > Environment.targetPopulationSize)
        //makeSelectionRoulette();
        population.makeSelection();
        if (iterationNumber == 0) {
            return true;
        } else {
            iterationNumber--;
            return false;
        }
    }

    public boolean[] getBestFit(){
        return population.getBestSpecimen();
    }
}
