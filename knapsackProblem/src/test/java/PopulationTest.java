import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PopulationTest {
    private Population population;

    @Before
    public void setUp() {
        population = new Population(new ArrayList<>(Arrays.asList(
                new boolean[]{true, true, true, false, true, false},
                new boolean[]{true, false, true, false, false, true},
                new boolean[]{false, true, false, true, false, true},
                new boolean[]{false, false, true, false, false, false},
                new boolean[]{true, true, false, false, true, false}
        )));
    }

    @Test
    public void populationSizeAfterReproducing() {
        int populationSizeBeforeReproduct = population.getSpecimens().size();
        population.reproduct();
        assertEquals(populationSizeBeforeReproduct +  Environment.targetPopulationSize/2,population.getSpecimens().size());
    }


    @Test
    public void mutationTest() {
        assertArrayEquals(new boolean[]{false, true, false, false, true, false},
                population.mutate(new boolean[]{true, true, false, false, true, false},0));
        assertArrayEquals(new boolean[]{false, true, false, true, true, false},
                population.mutate(new boolean[]{false, true, false, false, true, false},3));
        assertArrayEquals(new boolean[]{false, true, false, false, true, false},
                population.mutate(new boolean[]{false, true, false, false, true, true},5));
        assertArrayEquals(new boolean[]{false, true, false, false, true, false,false, true, true, false},
                population.mutate(new boolean[]{false, true, false, false, true, false,false, false, true, false},7));
        assertArrayEquals(new boolean[]{false, true, false, false, true, false,false, false, false, false},
                population.mutate(new boolean[]{false, true, false, false, true, false,false, false, true, false},8));
    }
}