import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class DecoderTest {
    private Decoder decoder;

    @Before
    public void setUp() throws Exception {
        decoder = new Decoder("src/main/resources/Inputs/input.txt");
    }

    @Test
    public void toPhenotype() {
        assertEquals(new ArrayList<>(Arrays.asList(
                new Item(1, 2),
                new Item(2, 3),
                new Item(4, 2)
        )
        ), decoder.toPhenotype(new boolean[]{true, true, true, false, false, false}));
        assertEquals(new ArrayList<>(Collections.singletonList(
                new Item(4, 2)
        )
        ), decoder.toPhenotype(new boolean[]{false, false, true, false, false, false}));
        assertEquals(new ArrayList<>(Arrays.asList(
                new Item(4, 2),
                new Item(8, 4)
        )
        ), decoder.toPhenotype(new boolean[]{false, false, true, false, false, true}));
    }

    @Test
    public void toGenotype() {
    }
}