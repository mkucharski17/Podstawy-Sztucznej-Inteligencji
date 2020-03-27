import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FitObjectTest {

    private FitObject FitObject ;

    @Before
    public void setUp(){
        Decoder decoder = new Decoder("src/main/resources/Inputs/input.txt");
        FitObject = new FitObject(decoder,10);
    }

    @Test
    public void getFitValueTest() {
        assertEquals(7, FitObject.getFitValue(new boolean[]{true, true, true, false, false, false}));
        assertEquals(2, FitObject.getFitValue(new boolean[]{false, false, true, false, false, false}));
        assertEquals(14, FitObject.getFitValue(new boolean[]{true, true, true, true, false, false}));
        assertEquals(10, FitObject.getFitValue(new boolean[]{true, true, true, true, true, true}));
        assertEquals(0, FitObject.getFitValue(new boolean[]{false, false, false, false, false, false}));
    }
}