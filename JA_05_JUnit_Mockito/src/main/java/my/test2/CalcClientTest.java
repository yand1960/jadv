package my.test2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CalcClientTest {

    @Test
    public void testShowResults() {
        Calcable calc = Mockito.mock(Calcable.class);
        CalcClient client = new CalcClient(calc);
        Mockito.when(calc.plus(1,2)).thenReturn(3);
        client.showResults(1,2);
        client.showResults(11,12);
    }
}
