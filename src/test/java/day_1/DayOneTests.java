package day_1;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class DayOneTests {
    @Test
    public void twoNumberResultShouldReturnTheCorrectAnswer() throws FileNotFoundException {
        assertThat(DayOneApplication.getTwoNumberAnswer()).isEqualTo(927684);
    }

    @Test
    public void threeNumberResultShouldReturnTheCorrectAnswer() throws FileNotFoundException {
        assertThat(DayOneApplication.getThreeNumberAnswer()).isEqualTo(292093004);
    }
}
