import com.google.common.primitives.Ints;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;


public class GeneticAlgorithm {
    private int iterationNumber = 50;
    private Population population;

    private int satisfiedCounter = 100;
    private boolean[] lastBest;


    GeneticAlgorithm(Population generatedPopulation) {
        this.population = generatedPopulation;
    }

    public void  makeNewGeneration() {
        population.makeReproduction();
        population.makeMutation();
    }

    public boolean isSatisfied() {
        if(population.getSpecimens().size() > Environment.targetPopulationSize)
        population.makeSelection();
        if (iterationNumber == 0) {
            return true;
        } else {
            iterationNumber--;
            return false;
        }
    }

    public boolean isSatisfiedLastNSame() {
        if(population.getSpecimens().size() > Environment.targetPopulationSize)
            population.makeSelection();
        if (satisfiedCounter == 0) {
            return true;
        } else {
            if(Arrays.equals(lastBest,getBestFit())){
                satisfiedCounter--;
            }else{
                satisfiedCounter=100;
                lastBest=getBestFit();
            }
            iterationNumber--;
            return false;
        }
    }

    public boolean[] getBestFit(){
        return population.getBestSpecimen();
    }
}
