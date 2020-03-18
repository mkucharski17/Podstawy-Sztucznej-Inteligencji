import lombok.Setter;

import java.util.ArrayList;

@Setter
public class GeneticAlgorithm {
    private ArrayList<boolean[] > population;
    private MatchObject matchObject;

    GeneticAlgorithm(int capacity, Decoder decoder){
        matchObject = new MatchObject(decoder,capacity);
    }

    public  ArrayList<boolean[] > makeNewGeneration( ArrayList<boolean[] > population){
        this.population = population;
        makeSelection();
        cross();
        mutate();
        return this.population;
    }

    private void makeSelection(){
        //TODO
    }


    private void cross(){
        //TODO
    }

    private void mutate(){
        //TODO
    }

    public boolean isSatisfied(){
        //TODO
        return  true;
    }
}
