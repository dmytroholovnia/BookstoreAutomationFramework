import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SomeTest {

    @Test
    public void test() {
        System.out.println("Test run");
        Assertions.assertThat(2).isEqualTo(2);
    }

    @Test
    public void failTest() {
        Assertions.assertThat(2*2).isEqualTo(5);
    }

}
