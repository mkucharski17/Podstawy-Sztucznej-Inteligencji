import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class PopulationChangerTest {

    @Test
    public void singlePointCrossoverTest() {
        assertThat(5,equalTo(PopulationChanger.singlePointCrossover(
                new boolean[]{true,true,true,true,true},
                new boolean[]{false,false,false,false,false},3).length));
        assertThat(new boolean[]{false,false,false,false,false},equalTo(PopulationChanger.singlePointCrossover(
                new boolean[]{true,true,true,true,true},
                new boolean[]{false,false,false,false,false},0)));
        assertThat(new boolean[]{true,true,false,false,false},equalTo(PopulationChanger.singlePointCrossover(
                new boolean[]{true,true,true,true,true},
                new boolean[]{false,false,false,false,false},2)));
        assertThat(new boolean[]{false,false,true,false,false},equalTo(PopulationChanger.singlePointCrossover(
                new boolean[]{false,true,false,true,true},
                new boolean[]{false,false,true,false,false},1)));
    }

    @Test
    public void uniformCrossover() {
        assertThat(3,equalTo(PopulationChanger.uniformCrossover(new boolean[]{true,true,true}, new boolean[]{false,false,false}).length));
        assertThat(new boolean[]{true,true,false},
                equalTo(PopulationChanger.uniformCrossover(new boolean[]{true,true,false}, new boolean[]{true,true,false})));
        assertThat(new boolean[]{true,false,false}, Matchers.not(equalTo(
                PopulationChanger.uniformCrossover(new boolean[]{true,true,false}, new boolean[]{true,true,false}))));
        assertThat(new boolean[]{true,false,true}, Matchers.not(equalTo(
                PopulationChanger.uniformCrossover(new boolean[]{false,true,false}, new boolean[]{false,true,false}))));
    }

    @Test
    public void mutate() {
        assertThat(6,equalTo(PopulationChanger.mutate(new boolean[]{true,true,true,false,false,false}, 0).length));
        assertThat(new boolean[]{false,true,true,false,false,false},equalTo(PopulationChanger.mutate(
                new boolean[]{true,true,true,false,false,false}, 0)));
        assertThat(new boolean[]{true,true,true,false,true,false},equalTo(PopulationChanger.mutate(
                new boolean[]{true,true,true,false,false,false}, 4)));
        assertThat(new boolean[]{true,true,true,false,false,false},equalTo(PopulationChanger.mutate(
                new boolean[]{true,true,false,false,false,false}, 2)));
        assertThat(new boolean[]{true,true,true,false,false,false},equalTo(PopulationChanger.mutate(
                new boolean[]{true,true,true,false,false,true}, 5)));
    }
}