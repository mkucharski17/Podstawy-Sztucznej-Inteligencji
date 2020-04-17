import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DecoderTest {
    private Decoder decoder;

    @Before
    public void setUp() {
        decoder = new Decoder("src/main/resources/Inputs/input_1.txt");
    }

    @Test
    public void toPhenotype() {
        assertThat(new ArrayList<>(Arrays.asList(
                new Item(1, 2),
                new Item(2, 3),
                new Item(4, 2)
        )
        ),equalTo (decoder.toPhenotype(new boolean[]{true, true, true, false, false, false})));

        assertThat(new ArrayList<>(Collections.singletonList(
                new Item(4, 2)
        )
        ), equalTo (decoder.toPhenotype(new boolean[]{false, false, true, false, false, false})));

        assertThat(new ArrayList<>(Arrays.asList(
                new Item(4, 2),
                new Item(8, 2)
        )
        ), equalTo (decoder.toPhenotype(new boolean[]{false, false, true, false, false, true})));

        assertThat(new ArrayList<>(), equalTo (decoder.toPhenotype(new boolean[]{false, false, false, false, false, false})));

        assertThat(new ArrayList<>(Arrays.asList(
                new Item(2, 3),
                new Item(4, 2),
                new Item(2, 7),
                new Item(8, 2)
        )
        ),equalTo ( decoder.toPhenotype(new boolean[]{false, true, true, true, false, true})));

        assertThat(new ArrayList<>(Arrays.asList(
                new Item(1, 2),
                new Item(2, 3),
                new Item(4, 2),
                new Item(2, 7),
                new Item(1, 4),
                new Item(8, 2)
        )
        ), equalTo (decoder.toPhenotype(new boolean[]{true, true, true, true, true, true})));
    }


    @Test
    public void toGenotype() {
        assertArrayEquals(new boolean[]{true, true, true, true, true, false}
                , decoder.toGenotype(new ArrayList<>(Arrays.asList(
                        new Item(1, 2),
                        new Item(2, 3),
                        new Item(4, 2),
                        new Item(2, 7),
                        new Item(1, 4),
                        new Item(8, 4)
                ))));

        assertArrayEquals(new boolean[]{false, false, false, true, true, false}
                , decoder.toGenotype(new ArrayList<>(Arrays.asList(
                        new Item(2, 7),
                        new Item(1, 4),
                        new Item(8, 4)
                ))));

        assertArrayEquals(new boolean[]{true, false, true, false, true, false}
                , decoder.toGenotype(new ArrayList<>(Arrays.asList(
                        new Item(1, 2),
                        new Item(4, 2),
                        new Item(1, 4)
                        ))));

        assertArrayEquals(new boolean[]{false, false, false, false, false, false}
                , decoder.toGenotype(new ArrayList<>()));

        assertArrayEquals(new boolean[]{false, false, false, false, false, false}
                , decoder.toGenotype(new ArrayList<>(Collections.singletonList(new Item(8, 4)
                ))));
    }
}