import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FitObjectTest {

    private FitObject fitObject ;

    @Before
    public void setUp(){
        Decoder decoder = new Decoder("src/main/resources/Inputs/input_1.txt");
        fitObject = new FitObject(decoder,10);
    }

    @Test
    public void getFitValueTest() {
        assertEquals(7, fitObject.getFitValue(new boolean[]{true, true, true, false, false, false}));
        assertEquals(2, fitObject.getFitValue(new boolean[]{false, false, true, false, false, false}));
        assertEquals(14, fitObject.getFitValue(new boolean[]{true, true, true, true, false, false}));
        assertEquals(0, fitObject.getFitValue(new boolean[]{true, true, true, true, true, true}));
        assertEquals(0, fitObject.getFitValue(new boolean[]{false, false, false, false, false, false}));
    }

    @Test
    public void calculateFitPointsTest() {
        Population population = new Population(new ArrayList<>(Arrays.asList(
                new boolean[]{false, false, false, false, false, false},
                new boolean[]{false, true, false, false, false, true},
                new boolean[]{false, true, false, true, false, true},
                new boolean[]{true, true, false, true, false, false},
                new boolean[]{false, false, false, true, false, false},
                new boolean[]{true, false, true, true, false, false}
                )));
        assertArrayEquals(new int[]{0,5,0,12,7,11},fitObject.calculateFitPoints(population));

        population = new Population(new ArrayList<>(Arrays.asList(
                new boolean[]{true, true, false, true, false, false},
                new boolean[]{false, false, false, false, false, true},
                new boolean[]{false, false, false, true, false, false},
                new boolean[]{true, true, false, true, false, false},
                new boolean[]{false, false, true, false, true, false},
                new boolean[]{false, false, true, true, false, false}
        )));
        assertArrayEquals(new int[]{12,2,7,12,6,9},fitObject.calculateFitPoints(population));


        population = new Population(new ArrayList<>(Arrays.asList(
                new boolean[]{true, false, false, false, false, false},
                new boolean[]{false, true, false, true, false, true},
                new boolean[]{false, true, false, true, false, true},
                new boolean[]{true, true, false, false, false, false},
                new boolean[]{false, false, false, true, false, false},
                new boolean[]{false, false, true, true, false, false}
        )));
        assertArrayEquals(new int[]{2,0,0,5,7,9},fitObject.calculateFitPoints(population));
    }
}