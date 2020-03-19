import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MatchObjectTest {

    private MatchObject matchObject ;

    @Before
    public void setUp(){
        Decoder decoder = new Decoder("src/main/resources/Inputs/input.txt");
        matchObject = new MatchObject(decoder,10);
    }

    @Test
    public void getMatchValueTest() {
        assertEquals(7, matchObject.getMatchValue(new boolean[]{true, true, true, false, false, false}));
        assertEquals(2, matchObject.getMatchValue(new boolean[]{false, false, true, false, false, false}));
        assertEquals(14, matchObject.getMatchValue(new boolean[]{true, true, true, true, false, false}));
        assertEquals(10, matchObject.getMatchValue(new boolean[]{true, true, true, true, true, true}));
        assertEquals(0, matchObject.getMatchValue(new boolean[]{false, false, false, false, false, false}));
    }
}